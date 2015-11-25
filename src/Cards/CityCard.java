package Cards;

import com.company.Player;

public class CityCard implements PlayerCard{

    String name;
    String color;

    public CityCard(String name, String color) {

        this.name = name;
        this.color = color;
    }

    @Override
    public String getNameOfCard() {
        return name.toLowerCase();
    }

    @Override
    public String getColorOfCard(){ return color.toLowerCase();}
}
