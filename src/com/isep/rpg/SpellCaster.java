package com.isep.rpg;

import java.util.ArrayList;
import java.util.List;

public abstract class SpellCaster extends Hero {
    int mana = 100;
    List<Potion> potions = new ArrayList<>();
    public SpellCaster(String n, int h) {
        super(n, h);
        for (int i =0; i<5;i++) {
            potions.add(new Potion());
        }
    }

    public void usePotion(){
        if (!potions.isEmpty()){
            this.mana += 20;
            potions.remove(0);
        }
    }

}
