package com.company;


import java.util.ArrayList;

public class City {

    //Create variables
    String name;
    int blueCubes = 0;
    int yellowCubes = 0;
    int blackCubes = 0;
    int redCubes = 0;
    boolean blueRecentOutbreak = false;
    boolean yellowRecentOutbreak = false;
    boolean blackRecentOutbreak = false;
    boolean redRecentOutbreak = false;
    ArrayList<String> neighborCities;
    String color;
    boolean researchStation;
    ArrayList<String> playersHere;

    City(String name, String color, ArrayList<String> neighborCities){

        //Instantiate variable that haven't been instantiated yet
        this.name = name;
        this.color = color;
        this.neighborCities = new ArrayList<>(neighborCities);

    }

    //Perform an outbreak on city when called
    public void outbreak(String color){ //RESET recentOutbreak UPON PLAYER TURN CHANGE
        System.out.println("There is a " + color + " outbreak in " + this.name);
        GameBoard.gameBoard.outbreakMarker.increaseOutbreakMarker();

        if(color == "blue"){
            blueRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName() == neighborCities.get(i)){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        } else if(color == "yellow"){
            yellowRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName() == neighborCities.get(i)){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        } else if(color == "black"){
            blackRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName() == neighborCities.get(i)){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        } else if(color == "red"){
            redRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName() == neighborCities.get(i)){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        }


    }

    //Remove cube from city checking color and if cure is up
    public void removeCube(String color) {

        if (color == "blue") {
            if (GameBoard.gameBoard.blueCureMarker.getHasCure()) {
                GameBoard.gameBoard.blueCubesLeft += blueCubes;
                blueCubes = 0;
            } else {
                GameBoard.gameBoard.blueCubesLeft += 1;
                blueCubes -= 1;
            }
        } else if(color == "yellow"){
            if(GameBoard.gameBoard.yellowCureMarker.getHasCure()){
                GameBoard.gameBoard.yellowCubesLeft += yellowCubes;
                yellowCubes = 0;
            } else {
                GameBoard.gameBoard.yellowCubesLeft += 1;
                yellowCubes -= 1;
            }
        } else if(color == "black"){
            if(GameBoard.gameBoard.blackCureMarker.getHasCure()){
                GameBoard.gameBoard.blackCubesLeft += blackCubes;
                blackCubes = 0;
            } else {
                GameBoard.gameBoard.blackCubesLeft += 1;
                blackCubes -= 1;
            }
        } else if(color == "red"){
            if(GameBoard.gameBoard.redCureMarker.getHasCure()){
                GameBoard.gameBoard.redCubesLeft += redCubes;
                redCubes = 0;
            } else {
                GameBoard.gameBoard.redCubesLeft += 1;
                redCubes -= 1;
            }
        }

    }

    //Add cube to city after checking color AND call outbreak when condition is met
    public void addCube(String color, int numberOfCubesAdded){

        if(color.equals("blue") && !GameBoard.gameBoard.blueCureMarker.getIsExterminated())
        {
            blueCubes += numberOfCubesAdded;
            GameBoard.gameBoard.blueCubesLeft -= numberOfCubesAdded;
            if(blueCubes > 3){
                GameBoard.gameBoard.blueCubesLeft += (blueCubes - 3);
                blueCubes = 3;
                if(!this.blueRecentOutbreak) {
                    this.outbreak("blue");
                }
            }
            //Check lose condition after cubes are added
            GameBoard.gameBoard.checkLose();
        }
        else if(color.equals("yellow") && !GameBoard.gameBoard.yellowCureMarker.getIsExterminated()){
            yellowCubes += numberOfCubesAdded;
            GameBoard.gameBoard.yellowCubesLeft -= numberOfCubesAdded;

            if(yellowCubes > 3){
                GameBoard.gameBoard.yellowCubesLeft += (yellowCubes - 3);
                yellowCubes = 3;
                if(!this.yellowRecentOutbreak) {
                    this.outbreak("yellow");
                }
            }
            //Check lose condition after cubes are added
            GameBoard.gameBoard.checkLose();
        } else if(color.equals("black") && !GameBoard.gameBoard.blackCureMarker.getIsExterminated()){
            blackCubes += numberOfCubesAdded;
            GameBoard.gameBoard.blackCubesLeft -= numberOfCubesAdded;
            if(blackCubes > 3){
                GameBoard.gameBoard.blackCubesLeft += (blackCubes - 3);
                blackCubes = 3;
                if(!this.blackRecentOutbreak) {
                    this.outbreak("black");
                }
            }
            //Check lose condition after cubes are added
            GameBoard.gameBoard.checkLose();
        }
        else if(color.equals("red") && !GameBoard.gameBoard.redCureMarker.getIsExterminated())
        {
            redCubes += numberOfCubesAdded;
            GameBoard.gameBoard.redCubesLeft -= numberOfCubesAdded;
            if(redCubes > 3){
                GameBoard.gameBoard.redCubesLeft += (redCubes - 3);
                redCubes = 3;
                if(!this.redRecentOutbreak) {
                    this.outbreak("red");
                }
            }
            //Check lose condition after cubes are added
            GameBoard.gameBoard.checkLose();
        }
    }

    //Called upon next player turn
    public void resetRecentOutbreak(){
        blueRecentOutbreak = false;
        yellowRecentOutbreak = false;
        blackRecentOutbreak = false;
        redRecentOutbreak = false;
    }

    //Called when placing a research station on city
    public void placeResearchStation() {
        GameBoard.gameBoard.researchStationsLeft -= 1;
        this.researchStation = true;
    }

    public void setPlayersHere(ArrayList<String> playersHere) {
        this.playersHere = playersHere;
    }

    public String getName() {
        return name;
    }

    public int getBlueCubes() {
        return blueCubes;
    }

    public int getYellowCubes() {
        return yellowCubes;
    }

    public int getBlackCubes() {
        return blackCubes;
    }

    public int getRedCubes() {
        return redCubes;
    }

    public ArrayList<String> getNeighborCities() {
        return neighborCities;
    }

    public String getColor() {
        return color;
    }

    public boolean isResearchStation() {
        return researchStation;
    }

    public ArrayList<String> getPlayersHere() {
        return playersHere;
    }
}
