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

    public void Outbreak(){
        //Use arraylist to AddCube() to neighboor cities in a for() loop

    }

    public void AddCube(int NumberOfCubesAdded){
        currentCubes += NumberOfCubesAdded;
        System.out.println("Cubes added to: " + this.name);

    }

}
