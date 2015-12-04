package example;


import Cards.CityCard;

import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable {

    BufferedReader input;
    //PrintWriter output;
    ObjectOutputStream objectOut;

    Socket sock;

    LobbyStatus lobbyStatus;

    String clientCommand;
    String[]data;

    Player clientPlayer;
    int playerID;
    int cardToBeDrawn;

    /**
     * Assigns arguments to global class variables
     * @param sock socket used to exchange data with client
     * @param clientsPlayer local variable of the Player class
     * @param lobbyStatus status of the lobby
     */
    ClientConnection(Socket sock, Player clientsPlayer,  LobbyStatus lobbyStatus)
    {
        this.sock = sock;
        this.clientPlayer = clientsPlayer;
        this.lobbyStatus = lobbyStatus;

        GameServer.gameBoard.players.add(clientsPlayer);
        lobbyStatus.setPlayerRole(clientPlayer.getID(), clientPlayer.getRoleID());
        playerID = this.clientPlayer.getID();
        cardToBeDrawn = 1;
    }

    /**
     * "main" method of the Thread that all communication with the client goes through
     * Will read input from server and store it in the clientCommand, then split and stored in data
     */
    @Override
    public void run()
    {
        try
        {
            objectOut = new ObjectOutputStream(sock.getOutputStream());
            input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        } catch (IOException e) {e.printStackTrace();}

        try
        {
            inLobby();
        } catch (IOException e) {
            e.printStackTrace();
        }

        inGame();
    }

    /**
     * During the lobby phase the server will respond to different commands compared to the inGame fase.
     * Will based on commands from client perform actions and change variables in the lobbyState object
     */
    public void inLobby() throws IOException {

        String getPlayerID = "GET_PLAYER_ID", getPlayerStatus = "GET_PLAYER_STATUS", setPlayerStatus = "SET_PLAYER_STATUS", getPlayerRole = "GET_PLAYER_ROLE", setAnimationTrue = "SET_ANIMATION_TRUE";

        while(!lobbyStatus.isAnimation()) {

            try
            {
                clientCommand = input.readLine();
                data = clientCommand.split("@");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("failed to read message from client ID: "+clientPlayer.getID());
            }
            System.out.println("Client ID: "+playerID+" says: "+clientCommand);

            //SEND PLAYER ID TO CLIENT
            if (data[0].equals(getPlayerID)) {
                //System.out.println("Sending player ID to player: " + playerID);
                objectOut.writeObject("GET_PLAYER_ID@" + playerID);
                objectOut.flush();
            }

            //SEND PLAYER STATUS
            else if (data[0].equals(getPlayerStatus)) {
                if (data[1].equals("0"))
                    objectOut.writeObject("GET_PLAYER_STATUS@" + lobbyStatus.getPlayerStatus("p1") + "@0");

                else if (data[1].equals("1"))
                    objectOut.writeObject("GET_PLAYER_STATUS@" + lobbyStatus.getPlayerStatus("p2") + "@1");

                else if (data[1].equals("2"))
                    objectOut.writeObject("GET_PLAYER_STATUS@" + lobbyStatus.getPlayerStatus("p3") + "@2");

                else if (data[1].equals("3"))
                    objectOut.writeObject("GET_PLAYER_STATUS@" + lobbyStatus.getPlayerStatus("p4") + "@3");

                objectOut.flush();
            }

            //Set the status of the player
            else if (data[0].equals(setPlayerStatus)) {
                if (data[1].equals("true")) {
                    lobbyStatus.changePlayerStatus(data[2] + "_True");
                    System.out.println("Player " + data[2] + ": is ready");
                } else if (data[1].equals("false")) {
                    lobbyStatus.changePlayerStatus(data[2] + "_False");
                    System.out.println("Player " + data[2] + ": is not ready");
                }
            }

            //Send player role
            else if (data[0].equals(getPlayerRole)) {
                //GET_PLAYER_ROLE @ playerID @ roleID
                objectOut.writeObject("GET_PLAYER_ROLE@" + data[1] + "@" + lobbyStatus.getPlayerRole(Integer.valueOf(data[1])));
                objectOut.flush();
            }

            //set the animation to true
            else if (data[0].equals(setAnimationTrue)) {
                lobbyStatus.setAnimation();

                for (int i = 0; i < GameServer.connectionArray.size(); i++) {
                    Socket tmpSocket = GameServer.connectionArray.get(i);

                    //send to all client that the animation is true
                    ObjectOutputStream tmpOut = new ObjectOutputStream(tmpSocket.getOutputStream());
                    tmpOut.writeObject("GET_ANIMATION_STATUS@true");
                    tmpOut.flush();
                    System.out.println("Animation true sent to player: " + i);
                }
                //used to determine if all players have received the animation boolean
                lobbyStatus.aniSend = true;
            }

            //If client sends null, it has disconnected. Socket and all streams are closed
            else if (data == null) {
                try {
                    System.out.println("Socket with player ID "+clientPlayer.getID()+" is closed in the in the lobby state");
                    disconnectSocket();
                    objectOut.close();
                    input.close();
                    sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("could not close output, input or socket after client returning null");
                }
            }
        }
    }


    /**
     * Will continue running as long as client is connected to the server
     * Will, based on values of data, perform different actions and make changes to the gameboard
     */
    public void inGame()
    {

        String moveToNeighbor = "MOVE_NEIGHBOR", moveToCityCard = "MOVE_TO_CITYCARD", moveFromCityCard = "MOVE_FROM_CITYCARD", moveBetweenStations = "MOVE_BETWEEN_RESEARCH", moveWithoutCard = "MOVE";
        String buildStation = "BUILD", treatDisease = "TREAT_DISEASE", createCure = "CREATE_CURE", drawCard = "DRAW_CARD", discardCard = "DISCARD_CARD";

        while(sock.isConnected())
        {

            //while its the players turn, wait for commands from the client
            while(clientPlayer.getIsTurn())
            {
                //Reads command from client
                try {
                    clientCommand = input.readLine();
                    data = clientCommand.split("@");
                } catch (IOException e) {
                    System.out.println("failed to read message from client ID: "+clientPlayer.getID());
                    e.printStackTrace();
                }
                System.out.println("Client ID: "+playerID+" says: "+clientCommand);


                //Player moves to neighbor city
                if (data[0].equals(moveToNeighbor)) {
                    City tmpCity;
                    tmpCity = GameBoard.gameBoard.getCity(data[1]);

                    clientPlayer.moveToNeighbor(tmpCity);
                    sendMessageToOtherClients(GameBoard.gameBoard.setMessageContent());
                }

                //Because the server wants to handle validation of movement, this will be used when a card is not needed to move
                else if(data[0].equals(moveWithoutCard))
                {
                    City tmpCity = GameBoard.gameBoard.getCity(data[1].toLowerCase());
                    clientPlayer.setCurrentCity(tmpCity);
                    sendMessageToOtherClients(GameBoard.gameBoard.setMessageContent());
                }

                //Player wants to move to a city using that city's card
                else if (data[0].equals(moveToCityCard)) {
                    City tmpCity;
                    int tmpIndex;

                    tmpCity = GameBoard.gameBoard.getCity(data[1]);
                    tmpIndex = clientPlayer.getCardOnHandIndex(data[1]);

                    clientPlayer.moveToCardOnHand(tmpCity, tmpIndex);
                    sendMessageToOtherClients(GameBoard.gameBoard.setMessageContent());

                }

                /** never used **/
                //Will move to any city using currentCity's card
                else if (data[0].equals(moveFromCityCard)) {
                    City tmpCity;
                    int tmpIndex;

                    tmpCity = GameBoard.gameBoard.getCity(data[1]);
                    tmpIndex = clientPlayer.getCardOnHandIndex(data[1]);

                    clientPlayer.moveUsingCurrentCityCard(tmpCity, tmpIndex);
                    sendMessageToOtherClients(GameBoard.gameBoard.setMessageContent());

                }

                //Will move between two cities with research stations
                else if (data[0].equals(moveBetweenStations)) {
                    City tmpCity;
                    tmpCity = GameBoard.gameBoard.getCity(data[1]);

                    clientPlayer.moveBetweenResearchStations(tmpCity);
                    sendMessageToOtherClients(GameBoard.gameBoard.setMessageContent());

                }

                //Will build research station in currentCity using currentCity's card
                else if (data[0].equals(buildStation)) {
                    int tmpIndex;
                    tmpIndex = clientPlayer.getCardOnHandIndex(clientPlayer.getCurrentCityName());

                    clientPlayer.buildResearchStation(tmpIndex);
                    sendMessageToOtherClients(GameBoard.gameBoard.setMessageContent());

                }

                //Will remove a cube of a color
                else if (data[0].equals(treatDisease)) {
                    clientPlayer.removeCube(data[1].toLowerCase());
                    sendMessageToOtherClients(GameBoard.gameBoard.setMessageContent());

                }

                //will create cure using the city cards in data.
                else if (data[0].equals(createCure)) {
                    if (data[1].toLowerCase().equals("red")) {
                        CityCard[] tmpCards = new CityCard[5];

                        for (int i = 0; i < tmpCards.length; i++) {
                            tmpCards[i] = clientPlayer.getCityCardFromHand(data[i + 2]);
                        }

                        clientPlayer.CreateCure(GameBoard.gameBoard.getRedCureMarker(), tmpCards);
                    } else if (data[1].toLowerCase().equals("yellow")) {
                        CityCard[] tmpCards = new CityCard[5];

                        for (int i = 0; i < tmpCards.length; i++) {
                            tmpCards[i] = clientPlayer.getCityCardFromHand(data[i + 2]);
                        }

                        clientPlayer.CreateCure(GameBoard.gameBoard.getYellowCureMarker(), tmpCards);
                    } else if (data[1].toLowerCase().equals("blue")) {
                        CityCard[] tmpCards = new CityCard[5];

                        for (int i = 0; i < tmpCards.length; i++) {
                            tmpCards[i] = clientPlayer.getCityCardFromHand(data[i + 2]);
                        }

                        clientPlayer.CreateCure(GameBoard.gameBoard.getBlueCureMarker(), tmpCards);
                    } else if (data[1].toLowerCase().equals("black")) {
                        CityCard[] tmpCards = new CityCard[5];

                        for (int i = 0; i < tmpCards.length; i++) {
                            tmpCards[i] = clientPlayer.getCityCardFromHand(data[i + 2]);
                        }

                        clientPlayer.CreateCure(GameBoard.gameBoard.getBlackCureMarker(), tmpCards);
                    }
                    sendMessageToOtherClients(GameBoard.gameBoard.setMessageContent());
                }

                /**
                 * Draws a card from playerdeck and sends the name back to client
                 * command structure "CARD_DRAWN@[name of city]
                 */
                else if(data[0].equals(drawCard))
                {
                    if(cardToBeDrawn == 1){
                        clientPlayer.drawCard();
                        cardToBeDrawn++;
                    }
                    else if(cardToBeDrawn == 2){
                        clientPlayer.drawCard();
                        cardToBeDrawn = 1;
                        doInfection();
                        clientPlayer.setTurnIsDone();
                    }

                    sendMessageToOtherClients(GameBoard.gameBoard.setMessageContent());
                }

                //Will discard card from hand
                else if(data[0].equals(discardCard))
                {
                    int tmpIndex = clientPlayer.getCardOnHandIndex(data[1]);
                    clientPlayer.discardCard(tmpIndex);

                    sendMessageToOtherClients(GameBoard.gameBoard.setMessageContent());
                }

                //if null is relieved the client has disconnected. socket and all streams are closed
                else if (data == null) {
                    try {
                        System.out.println("Socket with player ID "+clientPlayer.getID()+" is closed in the in the Game state");
                        objectOut.close();
                        input.close();
                        sock.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("could not close output, input or socket after client returning null");
                    }
                }
            }
        }
    }

    /**
     * infect as many cities as the infectionRate is equal to in the infectionMarker object
     */
    public void doInfection()
    {
        for(int i=0; i< GameBoard.gameBoard.infectionMarker.getInfectionRate(); i++)
            GameBoard.gameBoard.drawInfectionCard(1);
    }

    /**
     * Sends the Message class object to all the client who's turn it is not.
     * This is done very time the active player performs an action
     * @param messageToClients instance of the Message class that is send to clients
     */
    public void sendMessageToOtherClients(Message messageToClients)
    {
        for(int i=0; i< GameServer.connectionArray.size(); i++)
        {
            try
            {
                objectOut.writeObject(GameBoard.gameBoard.setMessageContent());
                objectOut.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Could not send Message Class to client ID "+clientPlayer.getID());
            }
        }
    }

    /**
     * checks if the socket is connected, if it is not, removes the socket from the array of sockets
     * and sets the clientConnected boolean to false which causes the while loop to break
     */
    public void disconnectSocket()
    {
        for(int i = 0; i < GameServer.connectionArray.size(); i++)
        {
            if(GameServer.connectionArray.get(i) == sock)
            {
                GameServer.connectionArray.remove(i);
            }
        }
    }
}
