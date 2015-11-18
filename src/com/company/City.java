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

        //Instantiate variable that haven't yet
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
                    if(GameBoard.gameBoard.allCities.get(j).getName() == neighborCities.get(i) && !GameBoard.gameBoard.allCities.get(j).blueRecentOutbreak){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        } else if(color == "yellow"){
            yellowRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName() == neighborCities.get(i) && !GameBoard.gameBoard.allCities.get(j).yellowRecentOutbreak){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        } else if(color == "black"){
            blackRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName() == neighborCities.get(i) && !GameBoard.gameBoard.allCities.get(j).blackRecentOutbreak){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        } else if(color == "red"){
            redRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName() == neighborCities.get(i) && !GameBoard.gameBoard.allCities.get(j).redRecentOutbreak){
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
                blueCubes = 0;
            } else {
                blueCubes -= 1;
            }
        } else if(color == "yellow"){
            if(GameBoard.gameBoard.yellowCureMarker.getHasCure()){
                yellowCubes = 0;
            } else {
                yellowCubes -= 1;
            }
        } else if(color == "black"){
            if(GameBoard.gameBoard.blackCureMarker.getHasCure()){
                blackCubes = 0;
            } else {
                blackCubes -= 1;
            }
        } else if(color == "red"){
            if(GameBoard.gameBoard.redCureMarker.getHasCure()){
                redCubes = 0;
            } else {
                redCubes -= 1;
            }
        }

    }

    //Add cube to city after checking color AND call outbreak when condition is met
    public void addCube(String color, int numberOfCubesAdded){

        if(color == "blue"){
            blueCubes += numberOfCubesAdded;
            GameBoard.gameBoard.blueCubesLeft -= numberOfCubesAdded;
            //Check lose condition after cubes are added
            GameBoard.gameBoard.checkLose();
            if(blueCubes > 3){
                this.outbreak("blue");
                blueCubes = 3;
            }
        } else if(color == "yellow"){
            yellowCubes += numberOfCubesAdded;
            GameBoard.gameBoard.yellowCubesLeft -= numberOfCubesAdded;
            //Check lose condition after cubes are added
            GameBoard.gameBoard.checkLose();
            if(yellowCubes > 3){
                this.outbreak("yellow");
                yellowCubes = 3;
            }
        } else if(color == "black"){
            blackCubes += numberOfCubesAdded;
            GameBoard.gameBoard.blackCubesLeft -= numberOfCubesAdded;
            //Check lose condition after cubes are added
            GameBoard.gameBoard.checkLose();
            if(blackCubes > 3){
                this.outbreak("black");
                blackCubes = 3;
            }
        } else if(color == "red"){
            redCubes += numberOfCubesAdded;
            GameBoard.gameBoard.redCubesLeft -= numberOfCubesAdded;
            //Check lose condition after cubes are added
            GameBoard.gameBoard.checkLose();
            if(redCubes > 3){
                this.outbreak("red");
                redCubes = 3;
            }
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
    public void placeResearchStation(boolean newValue) {
        this.researchStation = newValue;
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
