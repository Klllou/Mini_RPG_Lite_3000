package com.isep.rpg;

import java.util.List;

public class Wolf extends Enemy{
    public Wolf() {
        // Le Goblin possède 3 points de vie et inflige 0 points de dégats
        super( "Wolf",30, 10);
        this.sign = "\uD83D\uDC3A";
        this.maxHp = 30;
    }

    @Override
    public void doAction(List<Combatant> list, List<Combatant> heros, int ixHero) {

    }
    @Override
    public void chooseReward() {

    }
}

