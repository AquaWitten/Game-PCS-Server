package Cards;

public class EpidemicCard implements PlayerCard{

    String name;
    String description;

    public EpidemicCard(String name){

        this.name = name;
        description = "Increase, Infect, Intensify";
    }


    @Override
    public String getNameOfCard() {
        return name;
    }

    @Override
    public String getColorOfCard() {
        return null;
    }
}
