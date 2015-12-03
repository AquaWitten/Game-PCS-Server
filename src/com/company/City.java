package com.company;

import java.util.ArrayList;


public class City {


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


    City(String name, String color, ArrayList<String> neighborCities){

        this.name = name;
        this.color = color;
        this.neighborCities = new ArrayList<>(neighborCities);
    }

    /**
     * Will create an outbreak on the city based on the type/color of the cube
     * @param color Used to decide which type/color of cube to place on neighbor cities
     */
    public void outbreak(String color) {
        System.out.println("There is a " + color + " outbreak in " + this.name);
        GameBoard.gameBoard.outbreakMarker.increaseOutbreakMarker();

        switch (color) {
            case "blue":
                blueRecentOutbreak = true;

                for (int i = 0; i < neighborCities.size(); i++) {
                    for (int j = 0; j < GameBoard.gameBoard.allCities.size(); j++) {
                        if (GameBoard.gameBoard.allCities.get(j).getName().equals(neighborCities.get(i))) {
                            GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                            break;
                        }
                    }
                }
                break;

            case "yellow":
                yellowRecentOutbreak = true;

                for (int i = 0; i < neighborCities.size(); i++) {

                    for (int j = 0; j < GameBoard.gameBoard.allCities.size(); j++) {
                        if (GameBoard.gameBoard.allCities.get(j).getName().equals(neighborCities.get(i))) {
                            GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                            break;
                        }
                    }
                }
                break;

            case "black":
                blackRecentOutbreak = true;

                for (int i = 0; i < neighborCities.size(); i++) {

                    for (int j = 0; j < GameBoard.gameBoard.allCities.size(); j++) {
                        if (GameBoard.gameBoard.allCities.get(j).getName().equals(neighborCities.get(i))) {
                            GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                            break;
                        }
                    }
                }
                break;

            case "red":
                redRecentOutbreak = true;

                for (int i = 0; i < neighborCities.size(); i++) {

                    for (int j = 0; j < GameBoard.gameBoard.allCities.size(); j++) {
                        if (GameBoard.gameBoard.allCities.get(j).getName().equals(neighborCities.get(i))) {
                            GameBoard.gameBoard.allCities.get(j).addCube(color, 1);
                            break;
                        }
                    }
                }
                break;
        }
    }

    /**
     * Removes a cube from the city. If there is a cure, removes all cubes
     * When cubes are removed will check if the disease is exterminated (Cure is made and all cubes are removed from all cities)
     * @param color used to determine the type/color of the cube(s) that is being removed
     */
    public void removeCube(String color) {

        switch (color) {
            case "blue":
                if (GameBoard.gameBoard.blueCureMarker.getHasCure()) {
                    GameBoard.gameBoard.blueCubesLeft += blueCubes;
                    blueCubes = 0;
                    if (GameBoard.gameBoard.blueCubesLeft == 24 && GameBoard.gameBoard.blueCureMarker.getHasCure()) {
                        GameBoard.gameBoard.blueCureMarker.SetIsExterminated();
                    }
                } else {
                    GameBoard.gameBoard.blueCubesLeft += 1;
                    blueCubes -= 1;
                }
                break;
            case "yellow":
                if (GameBoard.gameBoard.yellowCureMarker.getHasCure()) {
                    GameBoard.gameBoard.yellowCubesLeft += yellowCubes;
                    yellowCubes = 0;
                    if (GameBoard.gameBoard.yellowCubesLeft == 24 && GameBoard.gameBoard.yellowCureMarker.getHasCure()) {
                        GameBoard.gameBoard.yellowCureMarker.SetIsExterminated();
                    }
                } else {
                    GameBoard.gameBoard.yellowCubesLeft += 1;
                    yellowCubes -= 1;
                }
                break;
            case "black":
                if (GameBoard.gameBoard.blackCureMarker.getHasCure()) {
                    GameBoard.gameBoard.blackCubesLeft += blackCubes;
                    blackCubes = 0;
                    if (GameBoard.gameBoard.blackCubesLeft == 24 && GameBoard.gameBoard.blackCureMarker.getHasCure()) {
                        GameBoard.gameBoard.blackCureMarker.SetIsExterminated();
                    }
                } else {
                    GameBoard.gameBoard.blackCubesLeft += 1;
                    blackCubes -= 1;
                }
                break;
            case "red":
                if (GameBoard.gameBoard.redCureMarker.getHasCure()) {
                    GameBoard.gameBoard.redCubesLeft += redCubes;
                    redCubes = 0;
                    if (GameBoard.gameBoard.redCubesLeft == 24 && GameBoard.gameBoard.redCureMarker.getHasCure()) {
                        GameBoard.gameBoard.redCureMarker.SetIsExterminated();
                    }
                } else {
                    GameBoard.gameBoard.redCubesLeft += 1;
                    redCubes -= 1;
                }
                break;
        }

    }

    /**
     * Adds cube(s) to a city when infecting during end of players turn, during outbreak, game setup fase or epidemic card
     * will call outbreak if too many cubes of color are on the city
     * @param color used to determine type/color of cube(s) to be added to the city
     * @param numberOfCubesAdded used during epidemic fase and setup fase to add more cubes to a city
     */
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

    /**
     * Inorder to avoid outbreak loop, City class has a RecentOutbreak boolean for each color
     * which is set to true after being target of an outbreak
     * This method resets these booleans
     */
    public void resetRecentOutbreak(){
        blueRecentOutbreak = false;
        yellowRecentOutbreak = false;
        blackRecentOutbreak = false;
        redRecentOutbreak = false;
    }

    /**
     * Places a research station and withdraws one from the spare research stations in the gameboard
     */
    public void placeResearchStation() {
        GameBoard.gameBoard.researchStationsLeft -= 1;
        this.researchStation = true;
    }

    /**-------------- Getters ---------------- **/

    public String getName() {
        return name.toLowerCase();
    }

    public boolean getResearchStation()
    {
        return researchStation;
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
    
}
