package com.isep.rpg;


public class Warrior extends Hero {
    public Warrior(String n, int h) {
        super(n, h);
    }

    @Override
    public void fight(Combatant combatant) {
        combatant.lose(1);
    }

}
