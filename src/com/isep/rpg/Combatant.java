package com.isep.rpg;


public abstract class Combatant {
    protected int damagePoints;
    private int healthpoint ;
    private String name;
    String sign;

    public Combatant(String name, int healthpoint){
        this.name = name;
        this.healthpoint = healthpoint;
    }

    public String getName(){
        return sign + name;
    }

    public int getHealthPoint(){
        return healthpoint;
    }

    //public abstract void fight(Combatant combatant);

    public void lose(int hp){
        healthpoint -= hp;
    }
    public void gain(int hp){
        healthpoint += hp;
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


    public abstract void doAction(Combatant combatant);
}
