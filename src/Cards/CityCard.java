package Cards;

import com.company.Player;

public class CityCard implements PlayerCard{

    //create variables
    String name;
    String color;

    public CityCard(String name, String color) {

        //assign correct values to this instance of card, contains name and color of associated city
        this.name = name;
        this.color = color;
    }

    //getters
    @Override
    public String getNameOfCard() {
        return name.toLowerCase();
    }

    @Override
    public String getColorOfCard(){ return color.toLowerCase();}
}
