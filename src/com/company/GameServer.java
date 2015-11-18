package com.company;

import Cards.*;
import Markers.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class GameServer {

    //Global variables
    int port;
    static ArrayList<Socket> connectionArray;
    static ArrayList<String> currentUsers;
    static GameBoard test1;

//--------------------------------------------------------------
//UPON DRAW OF NEW INFECTION CARD RESET CITY VARIABLE recentOutbreak TO FALSE
    public static void main(String[] args) {

        test1 = new GameBoard();

        //testing starts here --------------------------------------------
        /*allCities.get(1).redCubes = 3;
        allCities.get(2).redCubes = 2;
        allCities.get(12).redCubes = 2;
        allCities.get(0).outbreak("red");
        System.out.println(allCities.get(1).redCubes);
        System.out.println(allCities.get(12).name + allCities.get(12).redCubes);

        OutbreakMarker out = new OutbreakMarker();
        InfectionMarker inf = new InfectionMarker();
        for(int i=0; i < 10; i++) {
            out.increaseOutbreakMarker();
            inf.IncreaseInfectionRate();
            allCities.get(1).addCube("red", 3);
        }*/
        //testing ends here ---------------------------------------------

        Message message = new Message();
        message.setMessageContent();
        System.out.println(GameBoard.gameBoard.blackCubesLeft);
        System.out.println(message.blackCubesLeft);
    }

    public void Receive(){

    }

    public void Send(){

    }


}
