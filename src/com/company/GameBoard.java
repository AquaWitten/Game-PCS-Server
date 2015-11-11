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
    int researchStationLeft;
    int blueCubesLeft;
    int yellowCubesLeft;
    int blackCubesLeft;
    int redCubesLeft;
    boolean gameWon;
    boolean gameLost;


    GameBoard(){



    }

    //Check win condition and set gameWon to true
    public void CheckWin(CureMarker[] cures){

        if(cures[0].GetHasCure() && cures[1].GetHasCure() && cures[2].GetHasCure() && cures[3].GetHasCure()){
            System.out.println("Game is won! Congratulations");
            this.gameWon = true;
        }

    }

    //check lose condition and set gameLost to true
    public void CheckLose(OutbreakMarker outbreaks, InfectionMarker infections, int playerCardsLeft,
                          int blueCubesLeft, int yellowCubesLeft, int blackCubesLeft, int redCubesLeft ){

        //Check lose condition with cubes
        if(blueCubesLeft == 0 || yellowCubesLeft == 0 || blackCubesLeft == 0 || redCubesLeft == 0){
            System.out.println("Game is lost! You ran out of disease cubes");
            this.gameLost = true;
        }

        //Check lose condition with outbreakMarker
        if(outbreaks.getOutbreakCounter() == 8){
            System.out.println("Game is lost! There have been too many outbreaks");
            this.gameLost = true;
        }

        //Check lose condition with infectionMarker
        if(infections.GetInfectionRate() == 10){
            System.out.println("Game is lost! The infection rate of the disease is too high");
            this.gameLost = true;
        }

        //Check lose condition with player deck
        if(playerCardsLeft == 0){
            System.out.println("Game is lost! There are no more cards in the player deck");
            this.gameLost = true;
        }
    }

    //Activate upon the draw of an epedemic card
    public void ActivateEpedemicCard(){

    }

    //Activate upon the draw of an infection card
    public void ActivateInfectionCard(InfectionCard infectionCard, int numberOfCubes){

    }
}
