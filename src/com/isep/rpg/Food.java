
package com.isep.rpg;


public class Food extends Consumable {
    private int hpToHeal;
    public Food(String name) {
        super(name);
        this.hpToHeal = 5;
    }

    public int getHpToHeal() {
        return hpToHeal;
    }
    public void setHpToHeal(){
        this.hpToHeal = hpToHeal+2;
    }
}
