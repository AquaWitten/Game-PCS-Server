package com.company;

import Cards.*;
import Markers.*;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {

    //Global variables
    int port;
    static ArrayList<Socket> connectionArray;
    static ArrayList<String> currentUsers;
//--------------------------------------------------------------

    public static void main(String[] args) {
    OutbreakMarker out = new OutbreakMarker();

        for(int i=0; i < 10; i++)
            out.IncreaseOutbreakMarker();
    }

    public void Receive(){

    }

    public void Send(){

    }
}
