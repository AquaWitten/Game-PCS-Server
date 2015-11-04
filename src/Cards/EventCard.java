package Cards;

public class EventCard implements PlayerCard{


    String type;
    String name;
    String description;

    public EventCard(String type, String name) {

        this.type = type;
        this.name = name;
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


    @Override
    public String GetTypeOfCard() {
        return type;
    }

    @Override
    public String GetNameOfCard() {
        return name;
    }

    @Override
    public String GetColorOfCard() {
        return null;
    }
}
