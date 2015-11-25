package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection implements Runnable {

    String clientCommand;
    BufferedReader input;
    PrintWriter output;
    Socket sock;
    LobbyStatus lobbyStatus;
    Player clientPlayer;
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
        GameBoard.gameBoard.players.add(clientsPlayer);
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

        //SEND PLAYER ID TO CLIENT
        output.println(""+clientPlayer.getID()+"");
        output.flush();

        while(clientConnected){

            isConnected();
            try {
                clientCommand = input.readLine();
                System.out.println("Client ID: "+clientPlayer.getID()+" says: "+clientCommand);

            } catch (IOException e) {
                System.out.println("failed to read message from client ID: "+clientPlayer.getID());
            }

            //Set the status of the player in LobbyStatus based on command from client
            if(clientCommand.equals("READY"))
            {
                lobbyStatus.changePlayerStatus(clientPlayer.getID()+"_true");
            }

            else if(clientCommand.equals("UNREADY"))
            {
                lobbyStatus.changePlayerStatus(clientPlayer.getID()+"_false");
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
