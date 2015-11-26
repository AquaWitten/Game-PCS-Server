package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection implements Runnable {

    BufferedReader input;
    PrintWriter output;
    Socket sock;

    String clientCommand;
    LobbyStatus lobbyStatus;
    Player clientPlayer;
    int playerID;
    boolean clientConnected;

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
        clientConnected = true;
        GameServer.gameBoard.players.add(clientsPlayer);
        lobbyStatus.setPlayerRole(clientPlayer.getID(), clientPlayer.getRoleID());
        playerID = this.clientPlayer.getID();
    }

    /**
     * "main" method that all communication with the client goes through
     */
    @Override
    public void run() {
        try {
            output = new PrintWriter(sock.getOutputStream());
            input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[]data;
        String getPlayerID = "GET_PLAYER_ID", getPlayerStatus = "GET_PLAYER_STATUS", setPlayerStatus ="SET_PLAYER_STATUS", getPlayerRole = "GET_PLAYER_ROLE", setAnimationTrue = "SET_ANIMATION_TRUE";




        while(clientConnected){

            isConnected();
            try {
                clientCommand = input.readLine();
                System.out.println("Client ID: "+clientPlayer.getID()+" says: "+clientCommand);

            } catch (IOException e)
            {
                System.out.println("failed to read message from client ID: "+clientPlayer.getID());
                clientConnected = false;
            }
            data = clientCommand.split("@");

            //
            //Determining the command from client
            //

            //SEND PLAYER ID TO CLIENT
            if(data[0].equals(getPlayerID))
            {
                System.out.println("Sending player ID to player: "+playerID);
                output.println("GET_PLAYER_ID@"+playerID);
                output.flush();
            }
            //SEND PLAYER STATUS
            else if(data[0].equals(getPlayerStatus))
            {
                if(data[1].equals("0"))
                    output.println("GET_PLAYER_STATUS@"+lobbyStatus.getPlayerStatus("p1")+"@0");

                else if(data[1].equals("1"))
                    output.println("GET_PLAYER_STATUS@"+lobbyStatus.getPlayerStatus("p2")+"@1");

                else if(data[1].equals("2"))
                    output.println("GET_PLAYER_STATUS@"+lobbyStatus.getPlayerStatus("p3")+"@2");

                else if(data[1].equals("3"))
                    output.println("GET_PLAYER_STATUS@"+lobbyStatus.getPlayerStatus("p4")+"@3");

                output.flush();
            }
            //Set the status of the player
            else if(data[0].equals(setPlayerStatus))
            {
                if(data[1].equals("true"))
                {
                    lobbyStatus.changePlayerStatus(data[2]+"_True");
                    System.out.println("Player "+data[2]+": is ready");
                }

                else if(data[1].equals("false"))
                {
                    lobbyStatus.changePlayerStatus(data[2]+"_False");
                    System.out.println("Player "+data[2]+": is not ready");
                }
            }
            //Send player role
            else if(data[0].equals(getPlayerRole))
            {
                //GET_PLAYER_ROLE @ playerID @ roleID
                output.println("GET_PLAYER_ROLE@"+data[1]+"@"+lobbyStatus.getPlayerRole(Integer.valueOf(data[1])));
                output.flush();
            }

            else if(data[0].equals(setAnimationTrue))
            {
                lobbyStatus.setAnimation();
                output.println("GET_ANIMATION_STATUS@true");
                output.flush();
            }
        }
    }

    /**
     * checks if the socket is connected, if it is not, removes the socket from the array of sockets
     * and sets the clientConnected boolean to false which causes the while loop to break
     */
    public void isConnected()
    {
        if(!sock.isConnected())
        {
            for(int i = 0; i < GameServer.connectionArray.size(); i++)
            {
                if(GameServer.connectionArray.get(i) == sock)
                {
                    GameServer.connectionArray.remove(i);
                    clientConnected = false;
                }
            }
        }
    }

    public void inLobby()
    {

    }
}
