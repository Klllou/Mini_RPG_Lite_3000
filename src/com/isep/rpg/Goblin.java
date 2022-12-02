package com.isep.rpg;

public class Goblin extends Enemy{
    public Goblin() {
        // Le Goblin possède 3 points de vie et inflige 0 points de dégats
        super( "\uD83D\uDC7AGoblin",10, 0);
    }

    @Override
    public void doAction(Combatant badOne) {

    }
}
