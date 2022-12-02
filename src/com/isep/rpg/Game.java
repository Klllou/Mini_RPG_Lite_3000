
package com.isep.rpg;

import com.isep.utils.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class Game {

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
                    hero = new Healer("⚕" + name);
                    break;
                case "Mage","mage","3":
                    System.out.println("What's your name ?");
                    name = scanner.nextLine();
                    hero = new Mage("\uD83E\uDDD9" + name);
                    break;
                case "Hunter","hunter","4":
                    System.out.println("What's your name ?");
                    name = scanner.nextLine();
                    hero = new Hunter("\uD83C\uDFF9" + name);
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
                System.out.println(goodOne.getName() + " will target " + badOne.getName());
                goodOne.doAction(badOne);
                displayMessage("> "+goodOne.getName() + " attack " + badOne.getName() + " ! " );
                if (badOne.getHealthPoint() <= 0) {
                    displayMessage("Yeay! " + goodOne.getName()
                            + " has defeat " + badOne.getName() + ".");
                    enemies.remove(ixEnemy);
                    ixEnemy--; // Correction: évite que le suivant perde son tour
                } else {

                    // Riposte du Méchant, s'il n'est pas vaincu
                    displayMessage("> " + badOne.getName()
                            + " attack " + goodOne.getName() + " ! ");
                    badOne.fight(goodOne);

                    if (goodOne.getHealthPoint() <= 0) {
                        displayMessage
                                (goodOne.getName() + " has found an honorable death...");
                        heros.remove(ixHero);
                        ixHero--;
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

                // Au tour du héro/ennemie suivant
                ixHero = (ixHero + 1) % heros.size();
                ixEnemy = (ixEnemy + 1) % enemies.size();
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
            goodOne.doAction(badOne);
            displayMessage(">" + goodOne.getName()
                    + " attack " + badOne.getName());
            if (badOne.getHealthPoint() <= 0) {
                displayMessage("Yeay! " + goodOne.getName()
                        + " has defeat " + badOne.getName() + ".");
                enemies.remove(0);
                //ixHero--; // Correction: évite que le suivant perde son tour
            } else {

                // Riposte du gentil, s'il n'est pas vaincu
                displayMessage(">" +badOne.getName()
                        + " attack " + goodOne.getName());
                badOne.fight(goodOne);
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

    int nbFight;
    private List<Combatant> heros;
    private List<Combatant> enemies;


    // Méthodes d'affichage
    // (STATIQUES pour pouvoir les appeler depuis n'importe où dans le programme)
    //
    // => pourraient éventuellement être déplacées dans le package
    //    "com.isep.utils", en s'inspirant de "InputParser" (méthodes de saisie)

    public static void displayStatus(List<Combatant> h, List<Combatant> e) {
        /*for (int i = 0 ; i<5;i++){
            System.out.println();
        }*/
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