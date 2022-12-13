
package com.isep.rpg;

import java.io.*;

import com.isep.utils.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class Game {

    int nbFight;
    private List<Combatant> heros;
    private List<Combatant> enemies;


        // Declaring ANSI_RESET so that we can reset the color
        public static final String ANSI_RESET = "\u001B[0m";

        // Declaring the color
        // Custom declaration
        public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public Game(InputParser inputParser) {

        this.inputParser = inputParser;

        heros = new ArrayList<>();
        enemies = new ArrayList<>();

    }

    private void initializeHeros(){
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("> Choose a class or press enter to continue.");
            System.out.println("     (1) \uD83D\uDDE1Warrior ");
            System.out.println("     (2) ⚕Healer");
            System.out.println("     (3) \uD83E\uDDD9Mage");
            System.out.println("     (4) \uD83C\uDFF9Hunter");
            String herosClass;
            while(true) {
                herosClass = scanner.nextLine();
                if(herosClass.equals("1") || herosClass.equals("warrior") ||herosClass.equals("Warrior") || herosClass.equals("2")|| herosClass.equals("healer") ||herosClass.equals("Healer") ||herosClass.equals("3")|| herosClass.equals("mage") || herosClass.equals("Mage") || herosClass.equals("4") ||herosClass.equals("Hunter") || herosClass.equals("hunter") ||herosClass.equals("")){
                    break;
                } else {
                    System.out.println("Wrong input, please choose again");
                }
            }

            if (herosClass.equals("")) {
                return;
            }
            Hero hero = null;
            String name;
            System.out.println("What's your name ?");
            name = scanner.nextLine();
            switch(herosClass){
                case "Warrior","warrior","1":
                    hero = new Warrior(name);
                    hero.setWeapon("Sword", 5);
                    hero.setArmor("steel Armor", 2);
                    break;
                case "Healer", "healer", "2":
                    hero = new Healer(name);
                    hero.setWeapon("Stick", 2);
                    break;
                case "Mage","mage","3":
                    hero = new Mage(name);
                    hero.setWeapon("MagicWand", 7);
                    break;
                case "Hunter","hunter","4":
                    hero = new Hunter(name);
                    hero.setWeapon("Bow", 6);
                    break;
                default:
                    System.out.println("Wrong input, try again");
                    break;
            }
            if (hero != null) {
                this.heros.add(hero);
            }
        }
    }

    private void initializeEnemies(){
        Random random = new Random();
        for (int i =0; i< heros.size() +nbFight ; i++) {
            int nb;
            nb = random.nextInt(3);
            switch(nb) {
                case 0:
                    Enemy enemy = new Goblin();
                    enemies.add(enemy);
                    break;
                case 1:
                    Enemy enemy2 = new Wolf();
                    enemies.add(enemy2);
                    break;
                case 2:
                    Enemy enemy3 = new Dragon();
                    enemies.add(enemy3);
                    break;
            }
        }
    }

    public void start() {
        System.out.println(ANSI_GREEN + "Hi there Adventurer, you're mission will be, if you accept it... \ni hope you will... it's important you know.. but i'll understand if you prefer to sleep away your problems..\nAnyway..." +
                "\nYou will have to save the \uD83C\uDF0FWorld from the Massive invasion of \uD83D\uDC7EMonsters." +
                "\nTo do so, choose your \uD83E\uDDB8heros (the number of ennemies will vary according to the number of heros..). \n" +
                "Each \uD83E\uDDB8Hero will have 5 \uD83C\uDF72meals with him, spellcaster will also be given 5 ⚗potions each to regenerate their \uD83D\uDCA7mana. " );
        System.out.println("Now that you are more aware of the current situation of the \uD83C\uDF0Fworld, i will let you begin your \uD83D\uDDFAadventure"+ ANSI_RESET);
        System.out.println();
        Random random = new Random();
        int ixHero = 0;
        int ixEnemy = 0;
        initializeHeros();
        for(int i = 0; i<4;i++){
            initializeEnemies();
            ixHero = random.nextInt(heros.size());
            ixEnemy = random.nextInt(enemies.size());
            // Boucle de jeu
            while (true) {

                displayStatus(heros, enemies);

                Combatant goodOne = heros.get(ixHero);
                Combatant badOne = enemies.get(ixEnemy);

                // Attaque du Hero
                System.out.println("It's " + goodOne.getName() + "'s turn to do an action");
                goodOne.doAction(enemies, heros, ixHero);
                if (badOne.getHealthPoint()>0){
                    badOne.fight(goodOne);
                    displayMessage("> " + badOne.getName()
                            + " attack " + goodOne.getName() + " ! ");
                    if (goodOne.getHealthPoint() <= 0) {
                        displayMessage
                                (goodOne.getName() + " has found an honorable death...");
                        heros.remove(ixHero);
                        ixHero--;
                    }
                }


               // }

                // Tests de fin du jeu
                if (heros.size() == 0) {
                    displayMessage("All the heros have been defeat... RUN FOR YOUR LIFEEE AAAAAAAAAAAAHHHHHHHHHHHHHHHH");
                    break;
                }
                if (enemies.size() == 0) {

                    displayMessage("Congratulations fellow adventurer(s)! You can now proceed with the next fight.");
                    break;
                }

                // Au tour du héro/ennemie suivant
                ixHero = (ixHero + 1) % heros.size();
                ixEnemy = (ixEnemy + 1) % enemies.size();
            }
            System.out.println("############################################################################################");
            for (int n =0; n < heros.size();n++){
                System.out.println("Since you won this fight, "+heros.get(n).getName()+" can choose a reward ! ");
                heros.get(n).chooseReward();
            }
            nbFight++;
        }
        //combat du Boss
        Enemy enemy = new Boss();
        enemies.add(enemy);
        while (true) {
            displayStatus(heros, enemies);
            Combatant goodOne = heros.get(ixHero);
            Combatant badOne = enemies.get(0);

            // Attaque de l'ennemi
            System.out.println("It's " + goodOne.getName() + "'s turn to do an action");
            System.out.println(goodOne.getName() + " will target " + badOne.getName());
            goodOne.doAction(enemies, heros, ixHero);

            if (badOne.getHealthPoint()>0){
                badOne.fight(goodOne);
                displayMessage("> " + badOne.getName()
                        + " attack " + goodOne.getName() + " ! ");
                if (goodOne.getHealthPoint() <= 0) {
                    displayMessage
                            (goodOne.getName() + " has found an honorable death...");
                    heros.remove(ixHero);
                    ixHero--;
                }
            }
            // Tests de fin du jeu

            if (enemies.size() == 0) {
                displayMessage("Well done !!! I mean.. your life don't have sense anymore.. so maybe go back to your fields to grow some wheat to be useful..?");
                break;
            }
            if (heros.size() == 0) {
                displayMessage("All the heros have been defeat... EVERYBODY RUN FOR YOUR LIFEEE AAAAAAAAAAAAHHHHHHHHHHHHHHHH !!!!!!!!");
                break;
            }


            // Au tour du héro suivant
            ixHero = (ixHero + 1) % heros.size();
        }
    }


    private InputParser inputParser;



    // Méthodes d'affichage
    // (STATIQUES pour pouvoir les appeler depuis n'importe où dans le programme)
    //
    // => pourraient éventuellement être déplacées dans le package
    //    "com.isep.utils", en s'inspirant de "InputParser" (méthodes de saisie)

    public static void displayStatus(List<Combatant> h, List<Combatant> e) {

        System.out.println("------------------------------------------------------------------------");
        System.out.print(" Heros : ");
        for (Combatant c: h) {
            System.out.print(c.getName() + "(" + c.getHealthPoint()+ "♥) ");
        }
        System.out.println();
        System.out.print(" Enemies : ");
        for (Combatant c: e) {
            System.out.print(c.getName() + "(" + c.getHealthPoint() + "♥) ");
        }
        System.out.println();
        System.out.println("------------------------------------------------------------------------");
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}