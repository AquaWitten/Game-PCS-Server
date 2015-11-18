package Cards;

public class CityCard implements PlayerCard{

    String name;
    String color;

    public CityCard(String name, String color) {

        this.name = name;
        this.color = color;
    }


    @Override
    public String GetNameOfCard() {
        return name.toLowerCase();
    }

    @Override
    public String GetColorOfCard(){ return color.toLowerCase();}
}
