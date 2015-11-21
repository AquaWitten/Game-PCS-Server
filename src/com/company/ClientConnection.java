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

    ClientConnection(Socket sock, Player clientsPlayer,  LobbyStatus lobbyStatus)
    {
        this.sock = sock;
        this.clientPlayer = clientsPlayer;
        this.lobbyStatus = lobbyStatus;
        clientConnected = true;
    }

    @Override
    public void run() {
        try {
            output = new PrintWriter(sock.getOutputStream());
            input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(clientConnected){

            isConnected();
            try {
                clientCommand = input.readLine();
                System.out.println("Client ID: "+playerID+" says: "+clientCommand);

            } catch (IOException e) {
                System.out.println("failed to read message from client ID: "+playerID);
            }

            //Set the status of the player
            if(clientCommand.equals("READY"))
            {
                lobbyStatus.changePlayerStatus(playerID+"_true");
            }

            else if(clientCommand.equals("UNREADY"))
            {
                lobbyStatus.changePlayerStatus(playerID+"_false");
            }

        }
    }

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
