package com.company;


import Cards.InfectionCard;
import Cards.PlayerCard;
import Markers.CureMarker;
import Markers.InfectionMarker;
import Markers.OutbreakMarker;

public class GameBoard {

    //Create all relevant variables
    Player[] players;
    int difficulty;
    PlayerCard[] playerDeck;
    InfectionCard[] infectionDeck;
    int researchStationsLeft = 6;
    static int blueCubesLeft = 24;
    static int yellowCubesLeft = 24;
    static int blackCubesLeft = 24;
    static int redCubesLeft = 24;
    static boolean gameWon;
    static boolean gameLost;


    GameBoard(){



    }

    //Check win condition and set gameWon to true
    public static void checkWin(CureMarker[] cures){

        if(cures[0].getHasCure() && cures[1].getHasCure() && cures[2].getHasCure() && cures[3].getHasCure()){
            System.out.println("Game is won! Congratulations");
            gameWon = true;
        }

    }


    public static void checkLose(int blueCubesLeft, int yellowCubesLeft, int blackCubesLeft, int redCubesLeft ) {

        //Check lose condition with cubes
        if (blueCubesLeft == 0 || yellowCubesLeft == 0 || blackCubesLeft == 0 || redCubesLeft == 0) {
            System.out.println("Game is lost! You ran out of disease cubes");
            gameLost = true;
        }
    }

    public static void checkLose(OutbreakMarker outbreaks) {

        //Check lose condition with outbreakMarker
        if (outbreaks.getOutbreakCounter() == 8) {
            System.out.println("Game is lost! There have been too many outbreaks");
            gameLost = true;
        }
    }

    public static void checkLose(InfectionMarker infections) {

        //Check lose condition with infectionMarker
        if (infections.GetInfectionRate() == 10) {
            System.out.println("Game is lost! The infection rate of the disease is too high");
            gameLost = true;
        }
    }

    public static void checkLose(int playerCardsLeft){

        //Check lose condition with player deck
        if(playerCardsLeft == 0){
            System.out.println("Game is lost! There are no more cards in the player deck");
            gameLost = true;
        }
    }

    //Activate upon the draw of an epedemic card
    public void ActivateEpedemicCard(){

    }

    //Activate upon the draw of an infection card
    public void ActivateInfectionCard(InfectionCard infectionCard, int numberOfCubes){

    }
}
