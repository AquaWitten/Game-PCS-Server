package com.company;

public class EventCard extends Card{

    String name;
    String description;
    int eventType;

    EventCard(String name, int eventType) {
        super(name);

        this.name = name;
        this.eventType = eventType;
    }

    public void Event(){

        switch (eventType){

            case 1:
                ResilientPopulation();
                break;
            case 2:
                OneQuietNight();
                break;
            case 3:
                Forecast();
                break;
            case 4:
                Airlift();
                break;

            case 5:
                GovernmentGrant();
                break;

        }
    }

    private void GovernmentGrant() {
        description = "Add 1 Research Station to any City (No City Card needed)";
        //Code here
    }

    private void Airlift() {
        description = "Move any 1 player pawn to any City. Get permission before moving another Player's pawn";
        //Code here
    }

    private void Forecast() {
        description = "Draw, look at, and rearrange the top 6 cards of the Infection Deck. Put them back on top";
        //Code here
    }

    private void OneQuietNight() {
        description = "Skip the next Infect Cities step (Do not flip over any Infection Cards)";
        //Code here
    }

    private void ResilientPopulation() {
        description = "Remove any 1 card in the Infection Discard Pile from the game. You may play this between Infect and Intensify steps of an Epidemic";
        //Code here
    }


}
