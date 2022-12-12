package com.isep.rpg;

public class Armor extends Item {

    public Armor(String name, int armorPoints) {
        super(name);
        this.armorPoints = armorPoints;
    }

    public int getArmorPoints() {
        return armorPoints;
    }

    // Une arme inflige des points de dégats
    private int armorPoints;
}