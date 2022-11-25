package com.isep.rpg;


public abstract class Combatant {
    protected int damagePoints;
    public Combatant(String n, int h){
        name = n;
        healthpoint = h;
    }

    public String getName(){
        return name;
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

    public void heal() {
        gain(2 );
    }
    private int healthpoint ;
    private String name;

    public abstract void doAction(Combatant badOne);
}
