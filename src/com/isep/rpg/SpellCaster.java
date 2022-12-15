package com.isep.rpg;

import java.util.ArrayList;
import java.util.List;

public abstract class SpellCaster extends Hero {
    int mana = 100;
    int maxMana;

    List<Potion> potions = new ArrayList<>();
    public SpellCaster(String n, int h) {
        super(n, h);
        for (int i =0; i<5;i++) {
            potions.add(new Potion());
        }
        this.maxMana = 100;
    }

    public int getMana(){
        return this.mana;
    }

    public void usePotion(){
        if (!potions.isEmpty()){
            if ((this.getMana()+20) > this.maxMana) {
                this.mana = this.maxHp;
            } else {
                this.mana += potions.get(0).getManaToRegenerate();
            }
            potions.remove(0);
        }
    }

}
