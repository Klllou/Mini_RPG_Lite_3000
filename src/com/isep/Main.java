package com.isep;
import com.isep.rpg.Dragon;
import com.isep.rpg.Hero;
import com.isep.rpg.Warrior;
import com.isep.rpg.Game;
import com.isep.utils.InputParser;
import com.isep.utils.ConsoleParser;

public class Main {
    public static void main(String[] args) {
        InputParser ip = new ConsoleParser();
        Game game = new Game(ip);
        game.start();

    }


    private static void showStatus(Warrior w, Dragon d) {
        System.out.println(w.getName()+":"+ w.getHealthPoint()+"\n"+ d.getName()+ ":" + d.getHealthPoint());
    }
}

