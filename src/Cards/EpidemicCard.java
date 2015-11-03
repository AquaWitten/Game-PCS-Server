package Cards;

import Markers.InfectionMarker;

import java.util.ArrayList;

public class EpidemicCard {

    String name;
    String description;

    public EpidemicCard(String name){
        this.name = name;
        description = "Increase, Infect, Intensify";
    }

    public void Epidemic(InfectionMarker infectionMarker, ArrayList<InfectionCard> infectionDeck){

        ArrayList<InfectionCard> tempInfectionDeck = infectionDeck;

        //Increase
        infectionMarker.IncreaseInfectionRate();

        //Infect
        getLastInfectionCard(tempInfectionDeck);

    }

    public InfectionCard getLastInfectionCard(ArrayList<InfectionCard> tempDeck){

        InfectionCard lastCard = tempDeck.get(tempDeck.size()-1);

        return lastCard;
    }
}
