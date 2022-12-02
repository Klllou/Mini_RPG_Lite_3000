package com.isep.rpg;

public class Wolf extends Enemy{
    public Wolf() {
        // Le Goblin possède 3 points de vie et inflige 0 points de dégats
        super( "\uD83D\uDC3AWolf",10, 0);
    }

    @Override
    public void doAction(Combatant badOne) {

    }
}

