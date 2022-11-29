package com.isep.rpg;

public class Boss extends Enemy{

    public Boss() {
        // Le dragon possède 5 points de vie et inflige 3 points de dégats
        super( "\uD83D\uDC7ABoss",10, 1);
    }

    @Override
    public void doAction(Combatant badOne) {

    }
}
