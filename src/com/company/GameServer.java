package com.company;

import Cards.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

public class GameServer {

    //Global variables
    static ArrayList<RoleCard> roles;

    static LobbyStatus lobbyStatus;

    static int port;
    static int playerIDs;

    static ArrayList<Socket> connectionArray;

    static GameBoard gameBoard;


    //--------------------------------------------------------------

    GameServer()
    {

    }

    //--------------------------------------------------------------

    public static void main(String[] args)
    {
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        connectionArray = new ArrayList<>();
        port = 2555;

        gameBoard = new GameBoard();
        lobbyStatus = new LobbyStatus();
        instantiateRoleCards();
        InLobby();
        GameBoard.gameBoard.setupPhase();
        gameRunning();

    }

    public static void InLobby()
    {
        boolean messageSend = false;

        playerIDs = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            //as long as all players are not ready (true) stay in loop
            //while (!lobbyStatus.animation) {
            while (!lobbyStatus.ani1 || !lobbyStatus.ani2 || !lobbyStatus.ani3 || !lobbyStatus.ani4) {
                //as long as there is less than 4 players connected stay in loop
                while (connectionArray.size() <= 3) {
                    Socket newPlayerSocket = serverSocket.accept();
                    connectionArray.add(newPlayerSocket);
                    System.out.println("Client has connected to server");

                    int randomIndex = new Random().nextInt(roles.size());
                    RoleCard tempRole = roles.get(randomIndex);
                    roles.remove(randomIndex);

                    Player tempPlayer = new Player(tempRole,playerIDs, gameBoard.getCity("atlanta"));

                    GameBoard.gameBoard.players.add(tempPlayer);
                    ClientConnection clientConnect = new ClientConnection(newPlayerSocket, tempPlayer, lobbyStatus);

                    Thread newClient = new Thread(clientConnect);
                    newClient.start();
                    playerIDs++;
                }
                if(!messageSend){
                    messageSend = true;
                    System.out.println("all have connected");
                }

                Thread.sleep(1000);
            }
            System.out.println("out of lobby");
        }
        catch (IOException e) {
            System.out.println("Failed to make server socket");
        } catch (InterruptedException e) {
            System.out.println("failed to sleep while waiting for all players to press ready");
        }

        System.out.println("all players have connected and pressed start game");
    }

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

    public static void gameRunning() {
        //player with ID 0 is first player
        GameBoard.gameBoard.playerWithIDsTurn = 0;
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
}
