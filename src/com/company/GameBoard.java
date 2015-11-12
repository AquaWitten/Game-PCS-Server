package com.company;

import Cards.InfectionCard;
import Cards.PlayerCard;
import Markers.CureMarker;
import Markers.InfectionMarker;
import Markers.OutbreakMarker;

import java.util.ArrayList;

public class GameBoard {

    //Create all relevant variables
    Player[] players;
    int difficulty;
    public ArrayList<PlayerCard> playerDeck;
    public ArrayList<PlayerCard> playerDiscardPile;
    public ArrayList<InfectionCard> infectionDeck;
    public ArrayList<InfectionCard> infectionDiscardPile;
    OutbreakMarker outbreakMarker;
    InfectionMarker infectionMarker;
    int researchStationsLeft = 6;
    int blueCubesLeft = 24;
    int yellowCubesLeft = 24;
    int blackCubesLeft = 24;
    int redCubesLeft = 24;
    boolean gameWon;
    boolean gameLost;

    //Creates a static instance and makes it possible to refer to the variable through: GameBoard.gameBoard
    public static GameBoard gameBoard;


    GameBoard(){

        GameBoard.gameBoard = this;

    }

    //Activate upon the draw of an epedemic card
    public void ActivateEpedemicCard(){

    }

    //Activate upon the draw of an infection card
    public void ActivateInfectionCard(InfectionCard infectionCard, int numberOfCubes){

    }


    public void checkWin(CureMarker[] cures){

        //Check win condition
        if(cures[0].getHasCure() && cures[1].getHasCure() && cures[2].getHasCure() && cures[3].getHasCure()){
            System.out.println("Game is won! Congratulations");
            gameWon = true;
        }

    }

    public void checkLose(int blueCubesLeft, int yellowCubesLeft, int blackCubesLeft, int redCubesLeft ) {

        //Check lose condition with cubes
        if (blueCubesLeft == 0 || yellowCubesLeft == 0 || blackCubesLeft == 0 || redCubesLeft == 0) {
            System.out.println("Game is lost! You ran out of disease cubes");
            this.gameLost = true;
        }
    }

    public void checkLose(OutbreakMarker outbreaks) {

        //Check lose condition with outbreakMarker
        if (outbreaks.getOutbreakCounter() == 8) {
            System.out.println("Game is lost! There have been too many outbreaks");
            gameLost = true;
        }
    }

    public void checkLose(InfectionMarker infections) {

        //Check lose condition with infectionMarker
        if (infections.GetInfectionRate() == 10) {
            System.out.println("Game is lost! The infection rate of the disease is too high");
            gameLost = true;
        }
    }

    public void checkLose(int playerCardsLeft){

        //Check lose condition with player deck
        if(playerCardsLeft == 0){
            System.out.println("Game is lost! There are no more cards in the player deck");
            gameLost = true;
        }
    }


}
