package com.isep.rpg;


import java.util.List;

public abstract class Combatant {
    protected int damagePoints;
    private int healthPoint ;
    private String name;

    String sign;
    int maxHp;

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
        this.healthPoint -= hp;
    }
    public void gain(int hp){
        this.healthPoint += hp;
    }
    public int getDamagePoints() {
        return this.damagePoints;
    }


    public void fight(Combatant combatant) {
        combatant.lose(getDamagePoints() );
    }


    public void useFood() {
        if ((this.getHealthPoint()+40) >= this.maxHp) {
            this.healthPoint = this.maxHp;
        } else {
            gain(40 );
        }
    }
    public void healerHeal() {
        if ((this.getHealthPoint()+30) >= this.maxHp) {
            this.healthPoint = this.maxHp;
        } else {
            gain(30);
        }
    }

    public boolean isAlive(List<Combatant> enemies, int index) {
        return enemies.get(index).getHealthPoint() <= 0;
    }
    public abstract void doAction(List<Combatant> enemies,List<Combatant> heros, int ixHero);
    public abstract void chooseReward();

    protected void usePotion() {

    }
}
