package com.company;

public class PlayerCard extends Card{

    String name;
    int area;
    int population;
    String color;

    PlayerCard(String name, int area, int population, String color) {
        super(name);

        this.name = name;
        this.area = area;
        this.population = population;
        this.color = color;
    }
}
