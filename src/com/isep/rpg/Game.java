
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
            System.out.println("> Choose a class or press enter to continue.");
            System.out.println("     (1) \uD83D\uDDE1Warrior ");
            System.out.println("     (2) ⚕Healer");
            System.out.println("     (3) Mage");
            System.out.println("     (4) Hunter");
            String herosClass = scanner.nextLine();
            if (herosClass == "") {
                return;
            }
            Hero hero = null;
            String name;
            switch(herosClass){

                case "Warrior","warrior","1":
                    System.out.println("What's your name ?");
                    name = scanner.nextLine();
                    hero = new Warrior("\uD83D\uDDE1" + name);
                    break;
                case "Healer", "healer", "2":
                    System.out.println("What's your name ?");
                    name = scanner.nextLine();
                    hero = new Healer("\uD83E\uDDD9" + name);
                    break;
                /*case 3:
                    System.out.println("Buenos dias");
                    break;*/
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
        for (int i =0; i< heros.size() +nbFight ; i++) {
            Enemy enemy = new Dragon();
            enemies.add(enemy);

        }
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
                displayMessage("> "+badOne.getName()
                        + " attaque " + goodOne.getName() + " ! " );

                badOne.fight(goodOne);
                if (goodOne.getHealthPoint() <= 0) {
                    displayMessage
                            (goodOne.getName() + " has found an honorable death...");
                    heros.remove(ixHero);
                    ixHero--; // Correction: évite que le suivant perde son tour
                } else {

                    // Riposte du gentil, s'il n'est pas vaincu
                    displayMessage("> " + goodOne.getName()
                            + " attaque " + badOne.getName() + " ! ");
                    System.out.println();
                    System.out.println("It's " + goodOne.getName() + "'s turn to do an action");
                    goodOne.doAction(badOne);
                    if (badOne.getHealthPoint() <= 0) {
                        displayMessage("Yeay! " + goodOne.getName()
                                + " has defeat " + badOne.getName() + ".");
                        enemies.remove(0);
                    }

                }

                // Tests de fin du jeu
                if (heros.size() == 0) {
                    displayMessage("All the heros have been defeat... RUN FOR YOUR LIFEEE AAAAAAAAAAAAHHHHHHHHHHHHHHHH");
                    break;
                }
                if (enemies.size() == 0) {
                    displayMessage("Congratulations fellow adventurer(s)! You can now proceed with the next fight.");
                    break;
                }

                // Au tour du héro suivant
                ixHero = (ixHero + 1) % heros.size();
            }
            nbFight++;
        }
        Enemy enemy = new Boss();
        enemies.add(enemy);
        while (true) {
            displayStatus(heros, enemies);
            Combatant goodOne = heros.get(ixHero);
            Combatant badOne = enemies.get(0);

            // Attaque de l'ennemi
            displayMessage(">" + badOne.getName()
                    + " attack " + goodOne.getName());

            badOne.fight(goodOne);
            if (goodOne.getHealthPoint() <= 0) {
                displayMessage
                        (goodOne.getName() +  " has found an honorable death...");
                heros.remove(ixHero);
                ixHero--; // Correction: évite que le suivant perde son tour
            } else {

                // Riposte du gentil, s'il n'est pas vaincu
                displayMessage(">" +goodOne.getName()
                        + " attack " + badOne.getName());
                System.out.println();
                goodOne.doAction(badOne);
                if (badOne.getHealthPoint() <= 0) {
                    displayMessage("Yeay! " + goodOne.getName()
                            + " has defeat " + badOne.getName() + ".");
                    enemies.remove(0);
                }

            }

            // Tests de fin du jeu
            if (heros.size() == 0) {
                displayMessage("All the heros have been defeat... EVERYBODY RUN FOR YOUR LIFEEE AAAAAAAAAAAAHHHHHHHHHHHHHHHH !!!!!!!!");
                break;
            }
            if (enemies.size() == 0) {
                displayMessage("Well done !!! I mean.. your life don't have sense anymore.. so maybe go back to your fields to grow some wheat to be useful ?");
                break;
            }

            // Au tour du héro suivant
            ixHero = (ixHero + 1) % heros.size();
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
        for (int i = 0 ; i<5;i++){
            System.out.println();
        }
        System.out.println("--------------------------");
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
        System.out.println("--------------------------");
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}