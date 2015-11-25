package Cards;

import com.company.GameBoard;

import java.util.Collections;

public class EpidemicCard implements PlayerCard{

    String name;
    String description;

    public EpidemicCard(){

        this.name = "epidemic";
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
