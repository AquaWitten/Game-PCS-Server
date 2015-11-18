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
/*
        if(role.getName().toLowerCase() == "contingency planner")
            extraHand = new PlayerCard[1];
        else
            extraHand = null;*/
    }

    /**
     * @param targetCity
     */
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
                    discardCard(paymentCard);
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
                discardCard(paymentCard);
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


    public void CreateCure(CureMarker cureMarker, CityCard[] payCards){

        if(isTurn == true) {
            if (currentCity.researchStation) {
                int numbOfPayCards = 5;
                ArrayList<PlayerCard> tempCards = new ArrayList<>();

                for (int i = 0; i < cardHand.size(); i++)
                {
                    for(int j = 0; j < payCards.length; j++)
                    {
                        if (cardHand.get(i).GetNameOfCard().equals( payCards[j].GetNameOfCard() ))
                            tempCards.add(cardHand.get(i));
                    }
                }

                if (tempCards.size() >= numbOfPayCards)
                {
                    cureMarker.SetHasCure();
                    actionsLeft--;

                    for(int i=0; i < cardHand.size(); i++) {
                        for(int j=0; j < tempCards.size(); j++) {
                            if (cardHand.get(i).GetNameOfCard().equals( payCards[j].GetNameOfCard() ))
                                discardCard(i);
                        }
                    }
                }
                else
                    System.out.println("Player: "+ username+", you do not the right cards to make a cure");
            }
        }
    }

    public void discardCard(int i)
    {
        GameBoard.gameBoard.playerDiscard.add(cardHand.get(i));
        cardHand.remove(i);
    }

    public void buildResearchStation(int paymentCard){
        if(isTurn){
            if(currentCity.researchStation == false)
            {
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

//    public void buildResearchStationRole() {
//        if (role.getName().toLowerCase() == "operations expert") {
//            if (isTurn) {
//                if (currentCity.researchStation == false)
//                {
//                    currentCity.placeResearchStation(true);
//                    actionsLeft--;
//                }
//                else
//                    System.out.println("city has a research station");
//            }
//            else
//                System.out.println("Not your turn " + username);
//        }
//    }

    public void removeCube(String color)
    {
        if(isTurn)
            if(actionsLeft > 0)
            {
                currentCity.removeCube(color);
                actionsLeft--;
            }
    }

    public void drawCard(){
        cardHand.add(GameBoard.gameBoard.playerDeck.get(0));
        GameBoard.gameBoard.playerDeck.remove(0);
    }
}
