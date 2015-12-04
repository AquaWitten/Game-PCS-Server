package example;

import Cards.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

public class GameServer {

    static ArrayList<RoleCard> roles;
    static ArrayList<Socket> connectionArray;

    static LobbyStatus lobbyStatus;
    static GameBoard gameBoard;

    static int port;
    static int playerIDs;

    GameServer()
    {

    }

    public static void main(String[] args)
    {
        try {
            //Print IP of the server
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {e.printStackTrace();}

        connectionArray = new ArrayList<>();
        port = 2555;

        gameBoard = new GameBoard();
        lobbyStatus = new LobbyStatus();
        instantiateRoleCards();

        InLobby();
        GameBoard.gameBoard.setupPhase();
        gameRunning();



    }

    /**
     * Clients connect to server and client socket are stored
     * new Thread to handle communication for connected client is made
     * leaves lobby when 4 clients have connected and started the start animation
     */
    public static void InLobby()
    {
        boolean messageSend = false;

        playerIDs = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            //while the animation boolean is not send to all player stay in loop
            while (!lobbyStatus.aniSend)
            {
                //as long as there is less than 4 players connected stay in loop
                while (connectionArray.size() <= 3)
                {
                    Socket newPlayerSocket = serverSocket.accept();
                    connectionArray.add(newPlayerSocket);
                    System.out.println("Client has connected to server");

                    //Get random role card
                    int randomIndex = new Random().nextInt(roles.size());
                    RoleCard tempRole = roles.get(randomIndex);
                    roles.remove(randomIndex);

                    //Create temp player and add to gameBoard
                    Player tempPlayer = new Player(tempRole,playerIDs, gameBoard.getCity("atlanta"));
                    GameBoard.gameBoard.players.add(tempPlayer);

                    //Create new ClientConnection and add tmpPlayer as argument
                    ClientConnection clientConnect = new ClientConnection(newPlayerSocket, tempPlayer, lobbyStatus);
                    Thread newClient = new Thread(clientConnect);
                    newClient.start();

                    playerIDs++;
                }

                //Only prints out one time
                if(!messageSend)
                {
                    messageSend = true;
                    System.out.println("all have connected");
                }

                Thread.sleep(1000);
            }
            System.out.println("out of lobby");
        }
        catch (IOException e) {System.out.println("Failed to make server socket");}
        catch (InterruptedException e) {System.out.println("failed to sleep while waiting for all players to press ready");}

        System.out.println("all players have connected and pressed start game");
    }

    /**
     * While the game is running change the player turn
     * and check win and lose conditions
     */
    public static void gameRunning() {
        //player with ID 0 is first player
        GameBoard.gameBoard.playerWithIDsTurn = 1;
        System.out.println("Game is now running and is in gameRunning loop");
        System.out.println("Start player is player with ID: "+GameBoard.gameBoard.playerWithIDsTurn);

        while (!GameBoard.gameBoard.isGameLost() && !GameBoard.gameBoard.isGameWon()) {
            //player with the ID = playerWithIDsTurn has isTurn set to true
            GameBoard.gameBoard.players.get(GameBoard.gameBoard.getPlayerWithIDsTurn()).setIsTurn(true);

            //if the player has used all his moves set his turn to false and increase playerWithIDsTurn by 1
            if (GameBoard.gameBoard.players.get(GameBoard.gameBoard.getPlayerWithIDsTurn()).getTurnIsDone()) {
                GameBoard.gameBoard.players.get(GameBoard.gameBoard.getPlayerWithIDsTurn()).setIsTurn(false);
                System.out.println("Player "+GameBoard.gameBoard.getPlayerWithIDsTurn()+"'s turn is done");

                GameBoard.gameBoard.increasePlayerWithIDsTurn();
                System.out.println("It is now Player "+GameBoard.gameBoard.getPlayerWithIDsTurn()+"'s turn is now beginning");
            }
        }
        System.out.println("game has ended, conditions are: is the Game won? "+GameBoard.gameBoard.isGameWon()+" and is the game lost? "+GameBoard.gameBoard.isGameLost());
    }

    /**
     * Create roleCards
     * only 4 roles are included in the beta
     */
    public static void instantiateRoleCards()
    {
        roles = new ArrayList<>();
        RoleCard operationsExpert = new RoleCard("operations expert",0);
        roles.add(operationsExpert);
        RoleCard quarantineSpecialist  = new RoleCard("quarantine specialist",1);
        roles.add(quarantineSpecialist);
        RoleCard medic  = new RoleCard("medic",2);
        roles.add(medic);
        RoleCard scientist  = new RoleCard("scientist",3);
        roles.add(scientist);
    }
}
