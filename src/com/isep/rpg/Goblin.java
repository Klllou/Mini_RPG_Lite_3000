package com.isep.rpg;

import java.util.List;

public class Goblin extends Enemy{
    public Goblin() {
        // Le Goblin possède 3 points de vie et inflige 0 points de dégats
        super( "Goblin",10, 0);
        this.sign = "\uD83D\uDC7A";
    }

    @Override
    public void doAction(List<Combatant> list, List<Combatant> heros, int ixHero) {

    }
}
