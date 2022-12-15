package com.isep.rpg;

import java.util.List;

public class Boss extends Enemy{

    public Boss() {
        // Le dragon possède 5 points de vie et inflige 3 points de dégats
        super( "Boss",300, 30);
        this.sign = "\uD83D\uDC79";
        this.maxHp = 300;
    }

    @Override
    public void doAction(List<Combatant> list, List<Combatant> heros, int ixHero) {

    }
    @Override
    public void chooseReward() {

    }
}
