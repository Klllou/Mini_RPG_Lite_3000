package com.isep;
import com.isep.rpg.Dragon;
import com.isep.rpg.Warrior;

public class Main {
    public static void main(String[] args) {
        Warrior w = new Warrior("Ron", 5);
        Dragon d = new Dragon("adza", 5);
        showStatus(w, d);
        d.fight(w);
        showStatus(w, d);
    }





    private static void showStatus(Warrior w, Dragon d) {
        System.out.println(w.getName()+":"+ w.getHp()+"\n"+ d.getName()+ ":" + d.getHp());
    }
}

