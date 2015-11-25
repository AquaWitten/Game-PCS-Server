package Cards;


import com.company.GameBoard;

public class InfectionCard {

    String name;
    String color;

    public InfectionCard(String name, String color){
        this.name = name;
        this.color = color;

    }

    public void drawInfectionCard(int amount){
        for(int i = 0; i < GameBoard.gameBoard.allCities.size(); i++){
            GameBoard.gameBoard.allCities.get(i).resetRecentOutbreak();
        }

        //DO INFECTION
        String target = GameBoard.gameBoard.infectionDeck.get(0).getName();
        for(int i = 0; i < GameBoard.gameBoard.allCities.size(); i++){
            if(target == GameBoard.gameBoard.allCities.get(i).getName()){
                GameBoard.gameBoard.allCities.get(i).addCube(GameBoard.gameBoard.infectionDeck.get(0).getColor(), amount);
                i = GameBoard.gameBoard.allCities.size();
            }
        }

        //Move top card in the deck to the discard pile
        GameBoard.gameBoard.infectionDiscard.add(GameBoard.gameBoard.infectionDeck.get(0));
        GameBoard.gameBoard.infectionDeck.remove(0);

    }
    

    public String getColor() {
        return color;
    }

    //    public void Infect(City cityToInfect){
//
//
//    }


}
