package com.company;


import java.util.ArrayList;

public class Message {

    ArrayList<String> player1;
    ArrayList<String> player2;
    ArrayList<String> player3;
    ArrayList<String> player4;

    String[][] cities;

    ArrayList<String> playerDeck;
    ArrayList<String> playerDiscard;
    ArrayList<String> infectionDeck;
    ArrayList<String> infectionDiscard;

    int blueCubesLeft;
    int redCubesLeft;
    int yellowCubesLeft;
    int blackCubesLeft;

    int outbreakMarker;
    int infectionMarker;

    boolean gameWon;
    boolean gameLost;


    Message()
    {

    }

    //------------------------Getters----------------------------------------------

    public ArrayList<String> getPlayer1() {
        return player1;
    }

    public ArrayList<String> getPlayer2() {
        return player2;
    }

    public ArrayList<String> getPlayer3() {
        return player3;
    }

    public ArrayList<String> getPlayer4() {
        return player4;
    }

    public String[][] getCities() {
        return cities;
    }

    public ArrayList<String> getPlayerDeck() {
        return playerDeck;
    }

    public ArrayList<String> getPlayerDiscard() {
        return playerDiscard;
    }

    public ArrayList<String> getInfectionDeck() {
        return infectionDeck;
    }

    public ArrayList<String> getInfectionDiscard() {
        return infectionDiscard;
    }

    public int getBlueCubesLeft() {
        return blueCubesLeft;
    }

    public int getRedCubesLeft() {
        return redCubesLeft;
    }

    public int getYellowCubesLeft() {
        return yellowCubesLeft;
    }

    public int getBlackCubesLeft() {
        return blackCubesLeft;
    }

    public int getOutbreakMarker() {
        return outbreakMarker;
    }

    public int getInfectionMarker() {
        return infectionMarker;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameLost() {
        return gameLost;
    }
}
