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
    //ArrayList<String> playersHere;

    City(String name, String color, ArrayList<String> neighborCities){

        //Assign inserted values to this instance of city
        this.name = name;
        this.color = color;
        this.neighborCities = new ArrayList<>(neighborCities);

    }

    //Perform an outbreak on city when called based on color
    public void outbreak(String color){
        System.out.println("There is a " + color + " outbreak in " + this.name);
        GameBoard.gameBoard.outbreakMarker.increaseOutbreakMarker();

        if(color.equals("blue")){
            blueRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName().equals(neighborCities.get(i))){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        } else if(color.equals("yellow")){
            yellowRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName().equals(neighborCities.get(i))){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        } else if(color.equals("black")){
            blackRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName().equals(neighborCities.get(i))){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        } else if(color.equals("red")){
            redRecentOutbreak = true;

            for(int i = 0; i < neighborCities.size(); i++) {

                for(int j = 0; j < GameBoard.gameBoard.allCities.size(); j++){
                    if(GameBoard.gameBoard.allCities.get(j).getName().equals(neighborCities.get(i))){
                        GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                        j = GameBoard.gameBoard.allCities.size();
                    }
                }
            }

        }


    }

    //Remove cube from city checking color and if cure complete
    //If cure has been made and you remove all cubes, set cureMarker to exterminated
    public void removeCube(String color) {

        if (color.equals("blue")) {
            if (GameBoard.gameBoard.blueCureMarker.getHasCure()) {
                GameBoard.gameBoard.blueCubesLeft += blueCubes;
                blueCubes = 0;
                if(GameBoard.gameBoard.blueCubesLeft == 24 && GameBoard.gameBoard.blueCureMarker.getHasCure()){
                    GameBoard.gameBoard.blueCureMarker.SetIsExterminated();
                }
            } else {
                GameBoard.gameBoard.blueCubesLeft += 1;
                blueCubes -= 1;
            }
        } else if(color.equals("yellow")){
            if(GameBoard.gameBoard.yellowCureMarker.getHasCure()){
                GameBoard.gameBoard.yellowCubesLeft += yellowCubes;
                yellowCubes = 0;
                if(GameBoard.gameBoard.yellowCubesLeft == 24 && GameBoard.gameBoard.yellowCureMarker.getHasCure()){
                    GameBoard.gameBoard.yellowCureMarker.SetIsExterminated();
                }
            } else {
                GameBoard.gameBoard.yellowCubesLeft += 1;
                yellowCubes -= 1;
            }
        } else if(color.equals("black")){
            if(GameBoard.gameBoard.blackCureMarker.getHasCure()){
                GameBoard.gameBoard.blackCubesLeft += blackCubes;
                blackCubes = 0;
                if(GameBoard.gameBoard.blackCubesLeft == 24 && GameBoard.gameBoard.blackCureMarker.getHasCure()){
                    GameBoard.gameBoard.blackCureMarker.SetIsExterminated();
                }
            } else {
                GameBoard.gameBoard.blackCubesLeft += 1;
                blackCubes -= 1;
            }
        } else if(color.equals("red")){
            if(GameBoard.gameBoard.redCureMarker.getHasCure()){
                GameBoard.gameBoard.redCubesLeft += redCubes;
                redCubes = 0;
                if(GameBoard.gameBoard.redCubesLeft == 24 && GameBoard.gameBoard.redCureMarker.getHasCure()){
                    GameBoard.gameBoard.redCureMarker.SetIsExterminated();
                }
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

    //resets RecentOutbreak values
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

    //getters and setters
    public String getName() {
        return name.toLowerCase();
    }

    public boolean getResearchStation(){return researchStation;}

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
    
}
