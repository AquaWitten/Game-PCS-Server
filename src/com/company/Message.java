package com.company;


import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable{

    ArrayList<String> player1 = new ArrayList<String>();
    ArrayList<String> player2 = new ArrayList<String>();
    ArrayList<String> player3 = new ArrayList<String>();
    ArrayList<String> player4 = new ArrayList<String>();

    String[][] cities = new String[6][48];

    ArrayList<String> playerDeck = new ArrayList<String>();
    ArrayList<String> playerDiscard = new ArrayList<String>();
    ArrayList<String> infectionDeck = new ArrayList<String>();
    ArrayList<String> infectionDiscard = new ArrayList<String>();

    int blueCubesLeft;
    int redCubesLeft;
    int yellowCubesLeft;
    int blackCubesLeft;

    int researchStationsLeft;

    int outbreakMarker;
    int infectionMarker;

    boolean redCure;
    boolean blueCure;
    boolean yellowCure;
    boolean blackCure;

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

    public int getResearchStationsLeft(){return researchStationsLeft;}

    public int getOutbreakMarker() {
        return outbreakMarker;
    }

    public int getInfectionMarker() {
        return infectionMarker;
    }

    public boolean isRedCure() {return redCure;}

    public boolean isBlueCure() {return blueCure;}

    public boolean isYellowCure() {return yellowCure;}

    public boolean isBlackCure() {return blackCure;}

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameLost() {
        return gameLost;
    }
}
