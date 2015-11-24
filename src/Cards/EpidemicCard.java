package Cards;

import com.company.GameBoard;

public class EpidemicCard implements PlayerCard{

    String name;
    String description;

    public EpidemicCard(String name){

        this.name = name;
        description = "Increase, Infect, Intensify";
    }

    @Override
    public void drawPlayerCard(){
        //Increase
        GameBoard.gameBoard.infectionMarker.IncreaseInfectionRate();

        //Infect
        int lastCardNumber = GameBoard.gameBoard.infectionDeck.size();
        String targetName = GameBoard.gameBoard.infectionDeck.get(lastCardNumber).getName();
        for(int i = 0; i < GameBoard.gameBoard.allCities.size(); i++){
            if(targetName == GameBoard.gameBoard.allCities.get(i).getName()){
                GameBoard.gameBoard.allCities.get(i).addCube(GameBoard.gameBoard.infectionDeck.get(lastCardNumber).getColor(), 3);
                i = GameBoard.gameBoard.allCities.size();
            }
        }
        GameBoard.gameBoard.infectionDiscard.add(GameBoard.gameBoard.infectionDeck.get(lastCardNumber));
        GameBoard.gameBoard.infectionDeck.remove(lastCardNumber);

        //Intensify
        

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
