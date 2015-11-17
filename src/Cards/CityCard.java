package Cards;

public class CityCard implements PlayerCard{

    String type;
    String name;
    String color;

    public CityCard(String type, String info, String color) {

        this.type = type;
        this.name = info;
        this.color = color;
    }

    @Override
    public String GetTypeOfCard() {
        return type.toLowerCase();
    }

    @Override
    public String GetNameOfCard() {
        return name.toLowerCase();
    }

    @Override
    public String GetColorOfCard(){ return color.toLowerCase();}
}
