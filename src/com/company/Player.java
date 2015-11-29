package com.company;


import Cards.*;
import Markers.CureMarker;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    RoleCard role;
    //Color color;
    int ID;
    City currentCity;
    ArrayList<PlayerCard> cardHand;
    int actionsLeft;
    Boolean isTurn;

    //Only useful if Role "Contingency Planner" is active
    //PlayerCard[] extraHand;

    Player(RoleCard role, int ID, City startCity){

        this.role = role;
        this.ID = ID;
        this.currentCity = startCity;

        cardHand = new ArrayList<>();
        actionsLeft=0;
        isTurn=false;

        //Only useful if Role "Contingency Planner" is active
/*        if(role.getName().toLowerCase() == "contingency planner")
            extraHand = new PlayerCard[1];
        else
            extraHand = null;*/
    }

    /**
     * If its the players turn
     * Checks if a city is neighbor city and moves the player there
     * @param targetCity city the player wants to move to
     */
    public void moveToNeighbor(City targetCity) {
        if (isTurn) {
            for (int i = 0; i < currentCity.getNeighborCities().size(); i++) {

                if (targetCity.getName().toLowerCase().equals( currentCity.neighborCities.get(i).toLowerCase()) ) {
                    currentCity = targetCity;
                    actionsLeft--;
                    break;
                } else
                    System.out.println("City is not neighbor");
            }
        }
        else
            System.out.println("Not your turn " + ID);
    }

    /**
     * If its the players turn
     * move to target city if player has the target city's cityCard on hand
     * @param targetCity city the player wants to move to
     * @param paymentCard place in array, of the card that the player wishes to use as payment
     */
    public void moveToCardOnHand(City targetCity, int paymentCard){
        if (isTurn) {

                if (targetCity.name.toLowerCase().equals(cardHand.get(paymentCard).getNameOfCard().toLowerCase()) ) {
                    currentCity = targetCity;
                    discardCard(paymentCard);
                    actionsLeft--;
                }

                else
                    System.out.println("Payment card is not matching target city");
            }
        else
            System.out.println("Not your turn " + ID);
    }

    /**
     * If its the players turn
     * if current city is a card on players hand
     * @param targetCity city that the player wants to move to
     * @param paymentCard place in array, of the card that the player wishes to use as payment
     */
    public void moveUsingCurrentCityCard(City targetCity, int paymentCard){
        if (isTurn) {
            if (currentCity.name.toLowerCase().equals(cardHand.get(paymentCard).getNameOfCard().toLowerCase()) ) {
                currentCity = targetCity;
                discardCard(paymentCard);
                actionsLeft--;
            }
            else
                System.out.println("Payment card is not matching current city");
        }
        else
            System.out.println("Not your turn " + ID);
    }

    /**
     * If its the players turn
     * if current city has a research stations and target city has a research station
     * @param targetCity the city that the player wants to move to
     */
    public void moveBetweenResearchStations(City targetCity){
        if (isTurn) {
            if (currentCity.researchStation && targetCity.researchStation) {
                currentCity = targetCity;
                actionsLeft--;
            }
            else
                System.out.println("Payment card is not matching current city");
        }
        else
            System.out.println("Not your turn " + ID);
    }

    /**
     * If its the players turn
     * if current city has a research station
     * checks if payment cards are on the players hand and adds them to a temporary arrayList
     * if temporary arrayList has more or the same as numbOfPayCards, then make cure for selected cureMarker
     * remove temp cards from hand
     * @param cureMarker the selected type of cureMarker that the cure is made for
     * @param payCards array of cards that are used as payment
     */
    public void CreateCure(CureMarker cureMarker, CityCard[] payCards){

        if(isTurn) {
            if (currentCity.researchStation) {
                int numbOfPayCards = 5;
                ArrayList<PlayerCard> tempCards = new ArrayList<>();

                for (int i = 0; i < cardHand.size(); i++)
                {
                    for(int j = 0; j < payCards.length; j++)
                    {
                        if (cardHand.get(i).getNameOfCard().equals( payCards[j].getNameOfCard() ))
                            tempCards.add(cardHand.get(i));
                    }
                }

                if (tempCards.size() >= numbOfPayCards)
                {
                    cureMarker.SetHasCure();
                    actionsLeft--;

                    for(int i=0; i < cardHand.size(); i++) {
                        for(int j=0; j < tempCards.size(); j++) {
                            if (cardHand.get(i).getNameOfCard().equals( payCards[j].getNameOfCard() ))
                                discardCard(i);
                        }
                    }
                }
                else
                    System.out.println("Player: "+ ID+", you do not have the correct cards to make a cure");
            }
        }
    }

    /**
     * Discards card from players hand and adds it to discard pile
     * @param i place in array of the cards that is to be discarded
     */
    public void discardCard(int i)
    {
        GameBoard.gameBoard.playerDiscard.add(cardHand.get(i));
        cardHand.remove(i);
    }

    /**
     * If its the players turn
     * if currentCity does not have a research station
     * if player has cityCard matching the current city, place research station in current city
     * @param paymentCard place in array of card used for payment
     */
    public void buildResearchStation(int paymentCard){
        if(isTurn){
            if(!currentCity.researchStation)
            {
                if (currentCity.getName().equals(cardHand.get(paymentCard).getNameOfCard()) )
                {
                    currentCity.placeResearchStation();
                    actionsLeft--;
                }

                else
                    System.out.println("current city:"+currentCity.getName()+ " does not match paymentcard");
            }
            else
            System.out.println("city has a research station");
        }
        else
            System.out.println("Not your turn " + ID);
    }


    //only useful if Role "Operations Expert" is active
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

    /**
     * If its the players turn
     * if player has actions left, remove cube from current city
     * @param color color of the type of cube the is to be removed
     */
    public void removeCube(String color)
    {
        if(isTurn)
            if(actionsLeft > 0)
            {
                currentCity.removeCube(color);
                actionsLeft--;
            }
    }

    /**
     * Draw a card from the player deck
     * if the card is an Epidemic card, call activeEpidemic method in the gameboard
     * if the card is not an Epidemic card, add it to players hand
     * remove card from player deck
     * Check if there are more cards in player deck
     */
    public void drawCard(){
        if(GameBoard.gameBoard.playerDeck.get(0).getNameOfCard().equals("epidemic")){
            GameBoard.gameBoard.activateEpidemicCard();
            GameBoard.gameBoard.playerDiscard.add(GameBoard.gameBoard.playerDeck.get(0));
            GameBoard.gameBoard.playerDeck.remove(0);
        }
        else if(!GameBoard.gameBoard.playerDeck.isEmpty())
        {
            cardHand.add(GameBoard.gameBoard.playerDeck.get(0));
            GameBoard.gameBoard.playerDeck.remove(0);
        }
        else{
        //Check Lose
        GameBoard.gameBoard.checkLose(GameBoard.gameBoard.playerDeck.size());
        }
    }

    /**
     * get the players ID, number between 1 and 4
     * @return returns the players ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Get the name of the city the player currently stands in
     * @return returns the name of a city
     */
    public String getCurrentCityName() {
        return currentCity.getName().toLowerCase();
    }

    /**
     * get the string value of the isTurn boolean
     * @return returns the string
     */
    public String getIsTurnString() {
        return Boolean.toString(isTurn);
    }

    public int getRoleID()
    {
        return role.getRoleID();
    }

    public void setCurrentCity(City newCity)
    {
        currentCity = newCity;
    }
}
