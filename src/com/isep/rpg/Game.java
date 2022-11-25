
package com.isep.rpg;

import com.isep.utils.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    public Game(InputParser inputParser) {

        this.inputParser = inputParser;

        // Il faut normalement 5 héros de types différents...
        heros = new ArrayList<>();
        enemies = new ArrayList<>();

        /*
        Hero ronal = new Warrior("Ronal");
        ronal.take( new Weapon("knife", 5) );
        heros.add(ronal);
        //
        Hero conan = new Warrior("Conan");
        conan.take( new Weapon("sword", 5) );
        heros.add(conan);

        // Il faut normalement 5 ennemis de types différents...
        enemies = new ArrayList<>();
        enemies.add( new Dragon("Dracofeu") );
        enemies.add( new Dragon("Smaug") );*/
    }

    private void initializeHeros(){
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("choose a class");
            System.out.println("(1) Warrior");
            String herosClass = scanner.nextLine();
            if (herosClass == "") {
                System.out.println(heros);
                return;
            }
            Hero hero = null;
            String name;
            switch(herosClass){

                case "warrior","1":
                    System.out.println("choose a name");
                    name = scanner.nextLine();
                    hero = new Warrior(name);
                    break;
                /*case "mage":
                    name = scanner.nextLine();
                    hero = new Mage(name);

                case 3:
                    System.out.println("Buenos dias");
                    break;*/
                default:

                    System.out.println("Choix incorrect");
                    break;
            }
            if (hero != null) {
                this.heros.add(hero);
            }
        }
    }

    private void initializeEnemies(){
        for (int i =0; i< heros.size() +nbFight ; i++) {
            Enemy enemy = new Dragon();
            enemies.add(enemy);

        }
        System.out.println(enemies);
    }

    public void start() {

        int ixHero = 0;
        initializeHeros();
        for(int i = 0; i<4;i++){

            initializeEnemies();
            // Boucle de jeu
            while (true) {

                displayStatus(heros, enemies);

                Combatant goodOne = heros.get(ixHero);
                Combatant badOne = enemies.get(0);



                // Attaque de l'ennemi
                displayMessage("Le méchant " + badOne.getName()
                        + " attaque le gentil " + goodOne.getName() + "...");

                badOne.fight(goodOne);
                if (goodOne.getHealthPoint() <= 0) {
                    displayMessage
                            ("Le pauvre " + goodOne.getName() + " a été vaincu...");
                    heros.remove(ixHero);
                    ixHero--; // Correction: évite que le suivant perde son tour
                } else {

                    // Riposte du gentil, s'il n'est pas vaincu
                    displayMessage("Le gentil " + goodOne.getName()
                            + " attaque le méchant " + badOne.getName() + "...");
                    goodOne.doAction(badOne);
                    if (badOne.getHealthPoint() <= 0) {
                        displayMessage("Bravo, " + goodOne.getName()
                                + " a vaincu " + badOne.getName() + " !!!");
                        enemies.remove(0);
                    }

                }

                // Tests de fin du jeu
                if (heros.size() == 0) {
                    displayMessage("Les héros ont perdu, c'est la fin du monde...");
                    break;
                }
                if (enemies.size() == 0) {
                    displayMessage("BRAVO, les héros ont gagné, le monde est sauvé !!!");
                    break;
                }

                // Au tour du héro suivant
                ixHero = (ixHero + 1) % heros.size();
            }
            nbFight++;
        }

    }


    private InputParser inputParser;

    int nbFight;
    private List<Combatant> heros;
    private List<Combatant> enemies;


    // Méthodes d'affichage
    // (STATIQUES pour pouvoir les appeler depuis n'importe où dans le programme)
    //
    // => pourraient éventuellement être déplacées dans le package
    //    "com.isep.utils", en s'inspirant de "InputParser" (méthodes de saisie)

    public static void displayStatus(List<Combatant> h, List<Combatant> e) {
        System.out.println("#########################");
        for (Combatant c: h) {
            System.out.print(c.getName() + "(" + c.getHealthPoint() + ") ");
        }
        System.out.println();
        for (Combatant c: e) {
            System.out.print(c.getName() + "(" + c.getHealthPoint() + ") ");
        }
        System.out.println();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}