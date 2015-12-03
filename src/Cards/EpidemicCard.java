package Cards;

public class EpidemicCard implements PlayerCard{


    String name;

    public EpidemicCard(){

        this.name = "epidemic";
    }

    /**
     * Getters
     */
    @Override
    public String getNameOfCard() {
        return name;
    }

    @Override
    public String getColorOfCard() {
        return null;
    }
}
