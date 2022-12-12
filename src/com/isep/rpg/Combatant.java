package com.isep.rpg;


import java.util.List;

public abstract class Combatant {
    protected int damagePoints;
    private int healthPoint ;
    private String name;

    String sign;

    public Combatant(String name, int healthPoint){
        this.name = name;
        this.healthPoint = healthPoint;
    }

    public String getName(){
        return sign + name;
    }

    public int getHealthPoint(){
        return healthPoint;
    }

    //public abstract void fight(Combatant combatant);

    public void lose(int hp){
        healthPoint -= hp;
    }
    public void gain(int hp){
        healthPoint += hp;
    }
    public int getDamagePoints() {
        return this.damagePoints;
    }


    public void fight(Combatant combatant) {
        combatant.lose(getDamagePoints() );
    }


    public void useFood() {
        gain(3 );
    }
    public void healerHeal() {
        gain(5);

    }
    public boolean isAlive(List<Combatant> enemies, int index) {
        return enemies.get(index).getHealthPoint() <= 0;


    }
    public abstract void doAction(List<Combatant> enemies,List<Combatant> heros, int ixHero);
}
