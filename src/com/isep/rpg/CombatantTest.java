package com.isep.rpg;

import org.junit.jupiter.api.Test;

public class CombatantTest {
    @Test
    void fightTest(){
        Warrior w = new Warrior("Ron", 5);
        Dragon d = new Dragon("adza", 5);
        d.fight(w);

    }
}
