package Cards;

import com.company.GameBoard;

import java.util.Collections;

public class EpidemicCard implements PlayerCard{

    //variables created
    String name;

    public EpidemicCard(){

        //when instance is created give correct values to variables
        this.name = "epidemic";
    }

    //getters
    @Override
    public String getNameOfCard() {
        return name;
    }

    @Override
    public String getColorOfCard() {
        return null;
    }
}
