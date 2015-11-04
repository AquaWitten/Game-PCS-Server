package com.company;


import java.util.ArrayList;

public class City {

    String name;
    int currentCubes = 0;
    ArrayList<String> neighborCities;
    String color;
    boolean researchStation;
    ArrayList<String> playersHere;

    City(String name, String color, ArrayList<String> neighborCities){
        this.name = name;
        this.color = color;
        this.neighborCities = new ArrayList<>(neighborCities);


    }

    public void Outbreak(){ //disable continues outbreaks; reset at player turn change
        for(int i = 0; i < neighborCities.size(); i++) {

            for(int j = 0; j < GameServer.allCities.size(); j++){
                if(GameServer.allCities.get(j).getName() == neighborCities.get(i)){
                    GameServer.allCities.get(j).AddCube(1);
                    j = GameServer.allCities.size();
                }

            }

        }
    }

    public void AddCube(int NumberOfCubesAdded){
        currentCubes += NumberOfCubesAdded;
        System.out.println("Cubes added to: " + this.name);

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

    public int getCurrentCubes() {
        return currentCubes;
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
