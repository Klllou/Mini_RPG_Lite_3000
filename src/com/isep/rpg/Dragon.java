package com.isep.rpg;

public class Dragon extends Enemy{
    public Dragon(String n, int h) {
        super(n, h);
    }
    @Override
    public void fight(Combatant combatant) {
        combatant.lose(1);
    }
}
