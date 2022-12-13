
package com.isep.rpg;


public class Potion extends Consumable {

    private int manaToRegenerate;
    public Potion() {
        super("Potion");
        this.manaToRegenerate = 20;
    }

    public int getManaToRegenerate() {
        return manaToRegenerate;
    }
    public void setManaToRegenerate(){
        this.manaToRegenerate = manaToRegenerate+5;
    }

}
