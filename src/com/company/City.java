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

    public void Outbreak(String color){ //RESET recentOutbreak UPON PLAYER TURN CHANGE
        System.out.println("There is a " + color + " outbreak in " + this.name);
        recentOutbreak = true;
        for(int i = 0; i < neighborCities.size(); i++) {

            for(int j = 0; j < GameServer.allCities.size(); j++){
                if(GameServer.allCities.get(j).getName() == neighborCities.get(i) && !GameServer.allCities.get(j).recentOutbreak){
                    GameServer.allCities.get(j).AddCube(color, 1);
                    j = GameServer.allCities.size();
                }

            }

        }
    }

    public void AddCube(String color, int numberOfCubesAdded){  //Add cube to city after checking color
                                                                //Also do outbreak when condition is met
        if(color == "blue"){
            blueCubes += numberOfCubesAdded;
            if(blueCubes > 3){
                this.Outbreak("blue");
                blueCubes = 3;
            }
        } else if(color == "yellow"){
            yellowCubes += numberOfCubesAdded;
            if(yellowCubes > 3){
                this.Outbreak("yellow");
                yellowCubes = 3;
            }
        } else if(color == "black"){
            blackCubes += numberOfCubesAdded;
            if(blackCubes > 3){
                this.Outbreak("black");
                blackCubes = 3;
            }
        } else if(color == "red"){
            redCubes += numberOfCubesAdded;
            if(redCubes > 3){
                this.Outbreak("red");
                redCubes = 3;
            }
        }

    }

    public void setResearchStation(boolean researchStation) {
        this.researchStation = researchStation;
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
