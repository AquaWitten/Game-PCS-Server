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

        ArrayList<PlayerCard> cards = new ArrayList<>();

        CityCard city1 = new CityCard("cityCard","sanFran");
        EpidemicCard epi1 = new EpidemicCard("epidemicCard");

        cards.add(city1);
        cards.add(epi1);

        System.out.println(cards.get(0).GetNameOfCard());
        System.out.println(cards.get(0).GetTypeOfCard());

        System.out.println(cards.get(1).GetNameOfCard());
        System.out.println(cards.get(1).GetTypeOfCard());

    }

    public void Receive(){

    }

    public void Send(){

    }
}
