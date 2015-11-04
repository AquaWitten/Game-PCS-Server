package com.company;


import Cards.*;
import java.awt.*;

public class Player {

    RoleCard role;
    Color color;
    String username;
    City currentCity;
    PlayerCard[] cardHand;
    int actionsLeft;
    Boolean isTurn;

    PlayerCard[] extraHand;

    Player(RoleCard role,Color color, String username, City startCity){

        this.role = role;
        this.color = color;
        this.username = username;
        this.currentCity = startCity;

        cardHand = new PlayerCard[7];
        actionsLeft=0;
        isTurn=false;

        if(role.getName().toLowerCase() == "contingency planner")
            extraHand = new PlayerCard[1];

        else
            extraHand = null;
    }

    public void MoveAction(City targetCity){

        //move to neighbor city
        for(int i =0; i < currentCity.neighborCities.size(); i++){

            if(targetCity.getName().toLowerCase() == currentCity.neighborCities.get(i).toLowerCase()) {
                currentCity = targetCity;
                break;
            }
            else
                System.out.println("City is not neighbor");

        }
        //move to city based on the cards in hand
        for(int i = 0; i < cardHand.length; i++) {

            //if the target city is a card in hand
            if (targetCity.name.toLowerCase() == cardHand[i].GetNameOfCard().toLowerCase()){
                currentCity = targetCity;
                break;
            }
            //if current city is a card on hand
            else if(currentCity.name.toLowerCase() == cardHand[i].GetNameOfCard().toLowerCase()){
                currentCity = targetCity;
            }
        }
    }

    public void CreateCure(){

        int numbOfRed=0;
        int numbOfYellow=0;
        int numbOfBlue=0;
        int numbOfBlack=0;

        for(int i = 0; i < cardHand.length; i++){

            if(cardHand[i].GetTypeOfCard().toLowerCase() == "citycard") {

                if (cardHand[i].GetColorOfCard() == "red")
                    numbOfRed++;
            }
        }
    }
}
