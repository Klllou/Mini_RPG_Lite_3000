
package com.isep.rpg;


public abstract class Consumable extends Item {
    int healthToHeal;
    int manaToRestaure;
    public Consumable(String name) {
        super(name);
    }
}
