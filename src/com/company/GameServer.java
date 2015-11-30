package com.company;

import Cards.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
    static ArrayList<String> currentUsers;

    static GameBoard gameBoard;
    static PrintWriter output;

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
                //sendLobbyChanges();
                //lobbyStatus.setAllReady();
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

    public static void sendLobbyChanges()
    {
        if(lobbyStatus.changeInStatus)
        {
                //FOR LOOP THAT RUNS THROUGH ALL SOCKETS AND SENDS THE READY STATUS OF ALL PLAYERS
            for(int i = 0; i < connectionArray.size(); i++)
            {
                Socket tempSock = connectionArray.get(i);
                try {
                    PrintWriter tempOut = new PrintWriter(tempSock.getOutputStream());
                    tempOut.println("Player 1 is: "+lobbyStatus.getPlayerStatus("p1")+" Role ID: "+ lobbyStatus.p1Role);
                    tempOut.flush();
                    tempOut.println("Player 2 is: "+lobbyStatus.getPlayerStatus("p2")+" Role ID: "+ lobbyStatus.p2Role);
                    tempOut.flush();
                    tempOut.println("Player 3 is: "+lobbyStatus.getPlayerStatus("p3")+" Role ID: "+ lobbyStatus.p3Role);
                    tempOut.flush();
                    tempOut.println("Player 4 is: "+lobbyStatus.getPlayerStatus("p4")+" Role ID: "+ lobbyStatus.p4Role);
                    tempOut.flush();
                } catch (IOException e) {
                    System.out.println("Could not create Printerwriter for sending player lobby status");
                }
            }
            lobbyStatus.changeInStatus = false;
        }
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

        while (!GameBoard.gameBoard.isGameLost() && !GameBoard.gameBoard.isGameWon()) {
            //player with the ID = playerWithIDsTurn has isTurn set to true
            GameBoard.gameBoard.players.get(GameBoard.gameBoard.getPlayerWithIDsTurn()).setIsTurn(true);

            //if the player has used all his moves set his turn to false and increase playerWithIDsTurn by 1
            if (GameBoard.gameBoard.players.get(GameBoard.gameBoard.getPlayerWithIDsTurn()).getActionsLeft() <= 0) {
                GameBoard.gameBoard.players.get(GameBoard.gameBoard.getPlayerWithIDsTurn()).setIsTurn(false);
                GameBoard.gameBoard.increasePlayerWithIDsTurn();
            }
        }
    }
}
