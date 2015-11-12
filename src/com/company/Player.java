package com.company;


import Cards.*;
import Markers.CureMarker;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    RoleCard role;
    Color color;
    String username;
    City currentCity;
    ArrayList<PlayerCard> cardHand;
    int actionsLeft;
    Boolean isTurn;

    PlayerCard[] extraHand;

    Player(RoleCard role,Color color, String username, City startCity){

        this.role = role;
        this.color = color;
        this.username = username;
        this.currentCity = startCity;

        cardHand = new ArrayList<>();
        actionsLeft=0;
        isTurn=false;

        if(role.getName().toLowerCase() == "contingency planner")
            extraHand = new PlayerCard[1];
        else
            extraHand = null;
    }

    //move to neighbor city
    public void moveToNeighbor(City targetCity) {
        if (isTurn == true) {
            for (int i = 0; i < currentCity.getNeighborCities().size(); i++) {

                if (targetCity.getName().toLowerCase() == currentCity.neighborCities.get(i).toLowerCase()) {
                    currentCity = targetCity;
                    actionsLeft--;
                    break;
                } else
                    System.out.println("City is not neighbor");
            }
        }
        else
            System.out.println("Not your turn " + username);
    }

    //move to target city if card is on hand
    public void moveToCardOnHand(City targetCity, int paymentCard){
        if (isTurn == true) {

                if (targetCity.name.toLowerCase() == cardHand.get(paymentCard).GetNameOfCard().toLowerCase()) {
                    currentCity = targetCity;
                    DiscardCard(paymentCard);
                    actionsLeft--;
                }

                else
                    System.out.println("Payment card is not matching target city");
            }
        else
            System.out.println("Not your turn " + username);
    }

    //if current city is a card on hand
    public void moveUsingCurrentCityCard(City targetCity, int paymentCard){
        if (isTurn == true) {
            if (currentCity.name.toLowerCase() == cardHand.get(paymentCard).GetNameOfCard().toLowerCase()) {
                currentCity = targetCity;
                DiscardCard(paymentCard);
                actionsLeft--;
            }
            else
                System.out.println("Payment card is not matching current city");
        }
        else
            System.out.println("Not your turn " + username);
    }

    //if current city has a research stations and target city has a research station
    public void moveBetweenResearchStations(City targetCity){
        if (isTurn == true) {
            if (currentCity.researchStation && targetCity.researchStation) {
                currentCity = targetCity;
                actionsLeft--;
            }
            else
                System.out.println("Payment card is not matching current city");
        }
        else
            System.out.println("Not your turn " + username);
    }


    public void CreateCure(String color, int pay1, int pay2, int pay3, int pay4, int pay5){

        if(isTurn == true) {
            int numbOfColor = 0;

            //counts number of cards on player hand of specific color
            for (int i = 0; i < cardHand.size(); i++) {
                if (cardHand.get(i).GetTypeOfCard().toLowerCase() == "citycard") {
                    if (cardHand.get(i).GetColorOfCard().toLowerCase() == "red")
                        numbOfRed++;
                }
            }
            if (numbOfRed >= 4 && role.getName().toLowerCase() == "scientist") {

                cureMarker.SetHasCure();
                int removedCards = 0;
                int cardCounter = 0;

                while (removedCards <= 3) {

                    if (cardHand.get(cardCounter).GetColorOfCard().toLowerCase() == "red") {
                        GameBoard.playerDiscard.add(cardHand.get(cardCounter));
                        cardHand.remove(cardCounter);
                        removedCards++;
                    }
                    cardCounter++;
                }

            } else if (numbOfRed >= 5 && role.getName().toLowerCase() != "scientist") {

                GameBoard.redCureMarker.SetHasCure();
                int removedCards = 0;
                int cardCounter = 0;

                while (removedCards <= 4) {

                    if (cardHand.get(cardCounter).GetColorOfCard().toLowerCase() == "red") {
                        GameBoard.playerDiscard.add(cardHand.get(cardCounter));
                        cardHand.remove(cardCounter);
                        removedCards++;
                    }
                    cardCounter++;
                }

            }
        }
    }

    public void DiscardCard(int i){
        GameBoard.playerDiscard.add(cardHand.get(i));
    }

    public void buildResearchStation(int paymentCard){
        if(isTurn){
            if(currentCity.researchStation == false) {
                if (currentCity.getName() == cardHand.get(paymentCard).GetNameOfCard()) ;

                else
                    System.out.println("current city:"+currentCity.getName()+ " does not match paymentcard");
            }
            else
            System.out.println("city has a research station");
        }
        else
            System.out.println("Not your turn " + username);
    }

    public void buildResearchStationRole() {
        if (role.getName().toLowerCase() == "operations expert") {
            if (isTurn) {
                if (currentCity.researchStation == false) {
                    currentCity.setResearchStation(true);
                } else
                    System.out.println("city has a research station");
            } else
                System.out.println("Not your turn " + username);
        }
    }

    public void removeCube(){

    }

    public void drawCard(){

    }
}
