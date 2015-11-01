package com.company;

public class RoleCard extends Card{

    String name;
    String description;

    RoleCard(String name, String description) {
        super(name);

        this.name = name;
        this.description = description;
    }
}
