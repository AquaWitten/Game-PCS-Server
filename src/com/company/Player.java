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
        for()

        if(targetCity == )
    }
}
