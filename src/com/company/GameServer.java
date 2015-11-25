package com.company;

import Cards.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
        connectionArray = new ArrayList<>();
        port = 2555;

        gameBoard = new GameBoard();
        lobbyStatus = new LobbyStatus();
        instantiateRoleCards();
        InLobby();

    }

    public static void InLobby()
    {
        boolean messageSend = false;

        playerIDs = 1;
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            //as long as all players are not ready (true) stay in loop
            while (!lobbyStatus.allReady) {
                //as long as there is less than 4 players connected stay in loop
                while (connectionArray.size() <= 3) {
                    Socket newPlayerSocket = serverSocket.accept();
                    connectionArray.add(newPlayerSocket);
                    System.out.println("Client has connected to server");

                    int randomIndex = new Random().nextInt(roles.size());
                    RoleCard tempRole = roles.get(randomIndex);
                    roles.remove(randomIndex);

                    Player tempPlayer = new Player(tempRole,playerIDs, gameBoard.getCity("atlanta"));

                    ClientConnection clientConnect = new ClientConnection(newPlayerSocket, tempPlayer, lobbyStatus);
                    Thread newClient = new Thread(clientConnect);
                    newClient.start();
                    playerIDs++;
                }
                if(!messageSend){
                    messageSend = true;
                    System.out.println("all have connected");
                }
                sendLobbyChanges();
                lobbyStatus.setAllReady();
                Thread.sleep(1000);
            }
            System.out.println("out of lobby");
        }
        catch (IOException e) {
            System.out.println("Failed to make server socket");
        } catch (InterruptedException e) {
            System.out.println("failed to sleep while waiting for all players to press ready");
        }

        System.out.println("all players have connected and pressed ready");
        //Main game running code here
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
                    tempOut.println("Player 1 is: "+lobbyStatus.getPlayerStatus("p1"));
                    tempOut.flush();
                    tempOut.println("Player 2 is: "+lobbyStatus.getPlayerStatus("p2"));
                    tempOut.flush();
                    tempOut.println("Player 3 is: "+lobbyStatus.getPlayerStatus("p3"));
                    tempOut.flush();
                    tempOut.println("Player 4 is: "+lobbyStatus.getPlayerStatus("p4"));
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
        RoleCard operationsExpert = new RoleCard("operations expert");
        roles.add(operationsExpert);
        RoleCard quarantineSpecialist  = new RoleCard("quarantine specialist");
        roles.add(quarantineSpecialist);
        RoleCard medic  = new RoleCard("medic");
        roles.add(medic);
        RoleCard scientist  = new RoleCard("scientist");
        roles.add(scientist);
    }

    public static void gameRunning()
    {
        //code to check if win or lose by looking in gameboard class
    }
}
