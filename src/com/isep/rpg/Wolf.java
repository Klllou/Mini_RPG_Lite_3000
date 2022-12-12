package com.isep.rpg;

public class Wolf extends Enemy{
    public Wolf() {
        // Le Goblin possède 3 points de vie et inflige 0 points de dégats
        super( "Wolf",10, 0);
        this.sign = "\uD83D\uDC3A";
    }

    @Override
    public void doAction(Combatant badOne) {

    }
}

