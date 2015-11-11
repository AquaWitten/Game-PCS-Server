package com.company;


import java.util.ArrayList;

public class City {

    //Create variables
    String name;
    int blueCubes = 0;
    int yellowCubes = 0;
    int blackCubes = 0;
    int redCubes = 0;
    boolean recentOutbreak = false;
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

    //Perform outbreak on a city when called
    public void Outbreak(String color){ //RESET recentOutbreak UPON PLAYER TURN CHANGE
        System.out.println("There is a " + color + " outbreak in " + this.name);
        recentOutbreak = true;
        GameBoard.outbreakMarker.increaseOutbreakMarker();
        for(int i = 0; i < neighborCities.size(); i++) {

            for(int j = 0; j < GameServer.allCities.size(); j++){
                if(GameServer.allCities.get(j).getName() == neighborCities.get(i) && !GameServer.allCities.get(j).recentOutbreak){
                    GameServer.allCities.get(j).AddCube(color, 1);
                    j = GameServer.allCities.size();
                }

            }

        }
    }

    //Add cube to city after checking color AND call outbreak when condition is met
    public void AddCube(String color, int numberOfCubesAdded){

        if(color == "blue"){
            blueCubes += numberOfCubesAdded;
            GameBoard.blueCubesLeft -= numberOfCubesAdded;
            //Check lose condition after cubes are added
            GameBoard.checkLose(GameBoard.blueCubesLeft, GameBoard.yellowCubesLeft, GameBoard.blackCubesLeft, GameBoard.redCubesLeft);
            if(blueCubes > 3){
                this.Outbreak("blue");
                blueCubes = 3;
            }
        } else if(color == "yellow"){
            yellowCubes += numberOfCubesAdded;
            GameBoard.yellowCubesLeft -= numberOfCubesAdded;
            //Check lose condition after cubes are added
            GameBoard.checkLose(GameBoard.blueCubesLeft, GameBoard.yellowCubesLeft, GameBoard.blackCubesLeft, GameBoard.redCubesLeft);
            if(yellowCubes > 3){
                this.Outbreak("yellow");
                yellowCubes = 3;
            }
        } else if(color == "black"){
            blackCubes += numberOfCubesAdded;
            GameBoard.blackCubesLeft -= numberOfCubesAdded;
            //Check lose condition after cubes are added
            GameBoard.checkLose(GameBoard.blueCubesLeft, GameBoard.yellowCubesLeft, GameBoard.blackCubesLeft, GameBoard.redCubesLeft);
            if(blackCubes > 3){
                this.Outbreak("black");
                blackCubes = 3;
            }
        } else if(color == "red"){
            redCubes += numberOfCubesAdded;
            GameBoard.redCubesLeft -= numberOfCubesAdded;
            //Check lose condition after cubes are added
            GameBoard.checkLose(GameBoard.blueCubesLeft, GameBoard.yellowCubesLeft, GameBoard.blackCubesLeft, GameBoard.redCubesLeft);
            if(redCubes > 3){
                this.Outbreak("red");
                redCubes = 3;
            }
        }

    }

    //Called upon next player turn
    public void resetRecentOutbreak(){
        recentOutbreak = false;
    }

    //Called when placing a research station on city
    public void placeResearchStation() {
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
