package Cards;

public class CityCard implements PlayerCard{

    String type;
    String name;

    public CityCard(String type, String info) {

        this.type = type;
        this.name = info;
    }

    @Override
    public String GetTypeOfCard() {
        return type;
    }

    @Override
    public String GetNameOfCard() {
        return name;
    }
}
