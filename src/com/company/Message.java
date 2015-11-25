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

    //------------------------Setters---------------------------------------------

    /**
     * For player1, player2, player3 and player4
     * array space;
     * 0: String ID of player
     * 1: String describing if it is the players turn
     * 2: String name of the city the player is currently on
     * 3-?: for-loop adds the name of the cards of the players hand
     */
    public void setPlayer1() {
        player1 = new ArrayList<>();

        player1.set(0, Integer.toString(GameBoard.gameBoard.players.get(0).getID()));
        player1.set(1,GameBoard.gameBoard.players.get(0).getIsTurnString());
        player1.set(2,GameBoard.gameBoard.players.get(0).getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players.get(0).cardHand.size(); i++)
        {
            player1.set(3+i,GameBoard.gameBoard.players.get(0).cardHand.get(i).getNameOfCard().toLowerCase());
        }
    }

    public void setPlayer2() {
        player2 = new ArrayList<>();

        player2.set(0,Integer.toString(GameBoard.gameBoard.players.get(1).getID()));
        player2.set(1,GameBoard.gameBoard.players.get(1).getIsTurnString());
        player2.set(2,GameBoard.gameBoard.players.get(1).getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players.get(1).cardHand.size(); i++)
        {
            player2.set(3+i,GameBoard.gameBoard.players.get(1).cardHand.get(i).getNameOfCard().toLowerCase());
        }
    }

    public void setPlayer3() {
        player3 = new ArrayList<>();

        player3.set(0,Integer.toString(GameBoard.gameBoard.players.get(2).getID()));
        player3.set(1,GameBoard.gameBoard.players.get(2).getIsTurnString());
        player3.set(2,GameBoard.gameBoard.players.get(2).getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players.get(2).cardHand.size(); i++)
        {
            player3.set(3+i,GameBoard.gameBoard.players.get(2).cardHand.get(i).getNameOfCard().toLowerCase());
        }
    }

    public void setPlayer4() {
        player4 = new ArrayList<>();

        player4.set(0,Integer.toString(GameBoard.gameBoard.players.get(3).getID()));
        player4.set(1,GameBoard.gameBoard.players.get(3).getIsTurnString());
        player4.set(2,GameBoard.gameBoard.players.get(3).getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players.get(3).cardHand.size(); i++)
        {
            player4.set(3+i,GameBoard.gameBoard.players.get(3).cardHand.get(i).getNameOfCard().toLowerCase());
        }
    }

    /**
     * Each city on the board is added to the string array with, containing important values
     * array space;
     * 0: string name of the city
     * 1: string describing if the city has a research station
     * 2-5: string value of the amount of cubes on the city
     */
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

    /**
     * For loop adds the name of each card left in the playerDeck to the array
     */
    public void setPlayerDeck() {
        playerDeck = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.playerDeck.size(); i++)
        {
            playerDeck.set(i, GameBoard.gameBoard.playerDeck.get(i).getNameOfCard().toLowerCase());
        }
    }

    /**
     * For loop adds the name of each card in the playerDiscard to the array
     */
    public void setPlayerDiscard() {
        playerDiscard = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.playerDiscard.size(); i++)
        {
            playerDiscard.set(i,GameBoard.gameBoard.playerDiscard.get(i).getNameOfCard().toLowerCase());
        }
    }

    /**
     * For loop adds the name of each card left in the infectionDeck to the array
     */
    public void setInfectionDeck() {
        infectionDeck = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.infectionDeck.size(); i++)
            infectionDeck.set(i, GameBoard.gameBoard.infectionDeck.get(i).getName().toLowerCase());
    }

    /**
     * For loop adds the name of each card in the infectionDiscard to the array
     */
    public void setInfectionDiscard() {
        infectionDiscard = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.infectionDiscard.size(); i++)
            infectionDiscard.set(i, GameBoard.gameBoard.infectionDiscard.get(i).getName());
    }

    /**
     * copies the amount of blue cubes on the city
     */
    public void setBlueCubesLeft() {
        blueCubesLeft = GameBoard.gameBoard.blueCubesLeft;
    }

    /**
     * copies the amount of red cubes on the city
     */
    public void setRedCubesLeft() {
        redCubesLeft = GameBoard.gameBoard.redCubesLeft;
    }

    /**
     * copies the amount of yellow cubes on the city
     */
    public void setYellowCubesLeft() {
        yellowCubesLeft = GameBoard.gameBoard.yellowCubesLeft;
    }

    /**
     * copies the amount of black cubes on the city
     */
    public void setBlackCubesLeft() {
        blackCubesLeft = GameBoard.gameBoard.blackCubesLeft;
    }

    /**
     * copies the value of the outbreak marker
     */
    public void setOutbreakMarker() {
        outbreakMarker = GameBoard.gameBoard.outbreakMarker.getOutbreakCounter();
    }

    /**
     * copies the amount of times the infection marker has been moved
     */
    public void setInfectionMarker() {
        infectionMarker = GameBoard.gameBoard.infectionMarker.getInfectionDegree();
    }

    /**
     * copies the gamewon boolean from the gameboard
     */
    public void setGameWon() {
        gameWon = GameBoard.gameBoard.gameWon;
    }

    /**
     * copies the gamelost boolean from the gameboard
     */
    public void setGameLost() {
        gameLost = GameBoard.gameBoard.gameLost;
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
