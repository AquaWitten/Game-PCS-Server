package Cards;

public class EpidemicCard implements PlayerCard{

    String name;
    String description;

    public EpidemicCard(String name){

        this.name = name;
        description = "Increase, Infect, Intensify";
    }

    @Override
    public String GetTypeOfCard() {
        return name;
    }

    @Override
    public String GetNameOfCard() {
        return name;
    }
}
