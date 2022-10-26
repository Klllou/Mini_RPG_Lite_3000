package com.isep.rpg;


public abstract class Combatant {

    public Combatant(String n, int h){
        name = n;
        hp = h;
    }

    public String getName(){
        return name;
    }

    public int getHp(){
        return hp;
    }

    public abstract void fight(Combatant combatant);

    public void lose(int healthPoint){
        hp -= healthPoint;
    }

    private int hp ;
    private String name;
}
