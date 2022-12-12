package com.isep.rpg;

public class Goblin extends Enemy{
    public Goblin() {
        // Le Goblin possède 3 points de vie et inflige 0 points de dégats
        super( "Goblin",10, 0);
        this.sign = "\uD83D\uDC7A";
    }

    @Override
    public void doAction(Combatant badOne) {

    }
}
