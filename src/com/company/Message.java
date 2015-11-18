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

    public void setMessageContent(){
        setPlayer1();
        setPlayer2();
        setPlayer3();
        setPlayer4();

        setCities();
        setPlayerDeck();
        setPlayerDiscard();
        setInfectionDeck();
        setInfectionDiscard();

        setBlackCubesLeft();
        setBlueCubesLeft();
        setYellowCubesLeft();
        setRedCubesLeft();

        setInfectionMarker();
        setOutbreakMarker();
        setGameLost();
        setGameWon();
    }

    public void setPlayer1() {
        player1 = new ArrayList<>();

        player1.set(0,Integer.toString(GameBoard.gameBoard.players[0].getID()));
        player1.set(1,GameBoard.gameBoard.players[0].getIsTurnString());
        player1.set(2,GameBoard.gameBoard.players[0].getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players[0].cardHand.size(); i++)
        {
            player1.set(3+i,GameBoard.gameBoard.players[0].cardHand.get(i).GetNameOfCard().toLowerCase());
        }
    }

    public void setPlayer2() {
        player2 = new ArrayList<>();

        player2.set(0,Integer.toString(GameBoard.gameBoard.players[1].getID()));
        player2.set(1,GameBoard.gameBoard.players[1].getIsTurnString());
        player2.set(2,GameBoard.gameBoard.players[1].getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players[1].cardHand.size(); i++)
        {
            player2.set(3+i,GameBoard.gameBoard.players[1].cardHand.get(i).GetNameOfCard().toLowerCase());
        }
    }

    public void setPlayer3() {
        player3 = new ArrayList<>();

        player3.set(0,Integer.toString(GameBoard.gameBoard.players[2].getID()));
        player3.set(1,GameBoard.gameBoard.players[2].getIsTurnString());
        player3.set(2,GameBoard.gameBoard.players[2].getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players[2].cardHand.size(); i++)
        {
            player3.set(3+i,GameBoard.gameBoard.players[2].cardHand.get(i).GetNameOfCard().toLowerCase());
        }
    }

    public void setPlayer4() {
        player4 = new ArrayList<>();

        player4.set(0,Integer.toString(GameBoard.gameBoard.players[3].getID()));
        player4.set(1,GameBoard.gameBoard.players[3].getIsTurnString());
        player4.set(2,GameBoard.gameBoard.players[3].getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players[3].cardHand.size(); i++)
        {
            player4.set(3+i,GameBoard.gameBoard.players[3].cardHand.get(i).GetNameOfCard().toLowerCase());
        }
    }

    public void setCities() {
        cities = new String[6][48];

        for(int i=0; i < GameBoard.allCities.size(); i++)
        {
            cities[0][i] = GameBoard.allCities.get(i).getName().toLowerCase();
            cities[1][i] = Boolean.toString(GameBoard.allCities.get(i).researchStation);
            cities[2][i] = Integer.toString(GameBoard.allCities.get(i).getBlueCubes());
            cities[3][i] = Integer.toString(GameBoard.allCities.get(i).getYellowCubes());
            cities[4][i] = Integer.toString(GameBoard.allCities.get(i).getBlackCubes());
            cities[5][i] = Integer.toString(GameBoard.allCities.get(i).getRedCubes());
        }
    }

    public void setPlayerDeck() {
        playerDeck = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.playerDeck.size(); i++)
        {
            playerDeck.set(i, GameBoard.gameBoard.playerDeck.get(i).GetNameOfCard().toLowerCase());
        }
    }

    public void setPlayerDiscard() {
        playerDiscard = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.playerDiscard.size(); i++)
        {
            playerDiscard.set(i,GameBoard.gameBoard.playerDiscard.get(i).GetNameOfCard().toLowerCase());
        }
    }

    public void setInfectionDeck() {
        infectionDeck = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.infectionDeck.size(); i++)
            infectionDeck.set(i, GameBoard.gameBoard.infectionDeck.get(i).getName().toLowerCase());
    }

    public void setInfectionDiscard() {
        infectionDiscard = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.infectionDiscard.size(); i++)
            infectionDiscard.set(i, GameBoard.gameBoard.infectionDiscard.get(i).getName());
    }

    public void setBlueCubesLeft() {
        blueCubesLeft = GameBoard.gameBoard.blueCubesLeft;
    }

    public void setRedCubesLeft() {
        redCubesLeft = GameBoard.gameBoard.redCubesLeft;
    }

    public void setYellowCubesLeft() {
        yellowCubesLeft = GameBoard.gameBoard.yellowCubesLeft;
    }

    public void setBlackCubesLeft() {
        blackCubesLeft = GameBoard.gameBoard.blackCubesLeft;
    }

    public void setOutbreakMarker() {
        outbreakMarker = GameBoard.gameBoard.outbreakMarker.getOutbreakCounter();
    }

    public void setInfectionMarker() {
        infectionMarker = GameBoard.gameBoard.infectionMarker.getInfectionDegree();
    }

    public void setGameWon() {
        gameWon = GameBoard.gameBoard.gameWon;
    }

    public void setGameLost() {
        gameLost = GameBoard.gameBoard.gameLost;
    }
}
