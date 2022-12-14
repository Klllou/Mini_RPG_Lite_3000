
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


    public Game(InputParser inputParser) {

        this.inputParser = inputParser;

        heros = new ArrayList<>();
        enemies = new ArrayList<>();

    }

    private void initializeHeros(int nbHero) {
        for (int i = 0; i < nbHero; i++) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("> Choose a class");
            System.out.println("     (1) \uD83D\uDDE1Warrior ");
            System.out.println("     (2) ⚕Healer");
            System.out.println("     (3) \uD83E\uDDD9Mage");
            System.out.println("     (4) \uD83C\uDFF9Hunter");
            String herosClass;
            while (true) {
                herosClass = scanner.nextLine();
                if (herosClass.equals("1") || herosClass.equals("warrior") || herosClass.equals("Warrior") ||
                        herosClass.equals("2") || herosClass.equals("healer") || herosClass.equals("Healer") ||
                        herosClass.equals("3") || herosClass.equals("mage") || herosClass.equals("Mage") ||
                        herosClass.equals("4") || herosClass.equals("Hunter") || herosClass.equals("hunter") ||
                        herosClass.equals("")) {
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
            switch (herosClass) {
                case "Warrior", "warrior", "1":
                    hero = new Warrior(name);
                    hero.setWeapon("Sword", 20);
                    break;
                case "Healer", "healer", "2":
                    hero = new Healer(name);
                    hero.setWeapon("Stick", 10);
                    break;
                case "Mage", "mage", "3":
                    hero = new Mage(name);
                    hero.setWeapon("MagicWand", 40);
                    break;
                case "Hunter", "hunter", "4":
                    hero = new Hunter(name);
                    hero.setWeapon("Bow", 30);
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

    private void initializeEnemies() {
        Random random = new Random();
        for (int i = 0; i < heros.size() + nbFight; i++) {
            int nb;
            nb = random.nextInt(3);
            switch (nb) {
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi there Adventurer, you're mission will be, if you accept it... \ni hope you will... it's important you know.. but i'll understand if you prefer to sleep away your problems..\nAnyway..." +
                "\nYou will have to save the \uD83C\uDF0FWorld from the Massive invasion of \uD83D\uDC7EMonsters." +
                "\nTo do so, choose your \uD83E\uDDB8heros (the number of ennemies will vary according to the number of heros..). \n" +
                "Each \uD83E\uDDB8Hero will have 5 \uD83C\uDF72meals with him, spellcaster will also be given 5 ⚗potions each to regenerate their \uD83D\uDCA7mana. ");
        System.out.println("Now that you are more aware of the current situation of the \uD83C\uDF0Fworld, i will let you begin your \uD83D\uDDFAadventure");
        System.out.println();
        Random random = new Random();
        int ixHero = 0;
        int ixEnemy;
        int nbHero = 0;

        //Création de la liste des héros
        System.out.println("How many Heros do you want ?");

        while (true) {
            System.out.print(" ");
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                if (num > 0) {
                    nbHero = num;
                    break;
                } else {
                    System.out.println("Please enter a positive number");
                }
            } else {
                System.out.println("Please enter a positive number");
                scanner.next(); // pour consommer l'entrée non valide et passer à la suivante
            }
        }

        initializeHeros(nbHero);

        //Boucle de 4 combats Avant le Combat final contre le BOSS
        for(int i = 0; i<4;i++){

            //Création de la liste d'ennemies
            initializeEnemies();

            // Détermination de quel ennemie et quel héro commence à attaquer
            ixHero = random.nextInt(heros.size());
            ixEnemy = random.nextInt(enemies.size());
            // Boucle de jeu
            while (true) {

                displayStatus(heros, enemies);

                Combatant goodOne = heros.get(ixHero);
                Combatant badOne = enemies.get(ixEnemy);

                // Attaque du Hero
                System.out.print("It's " + goodOne.getName() + "'s turn to do an action. ");
                goodOne.doAction(enemies, heros, ixHero);
                //Riposte de L'ennemie si encore vivant
                if (badOne.getHealthPoint()>0){
                    badOne.fight(goodOne);
                    displayMessage("> "+ badOne.getName()
                            + " attack " + goodOne.getName() + " ! (-"+ badOne.damagePoints+ "♥)");
                    if (goodOne.getHealthPoint() <= 0) {
                        displayMessage(goodOne.getName() + " has found an honorable death...");
                        heros.remove(ixHero);
                        ixHero--;
                    }
                }
                //Tests de fin du jeu
                if (heros.size() == 0) {
                    System.out.println("############################################################################################");
                    displayMessage("All the heros have been defeat... RUN FOR YOUR LIFEEE AAAAAAAAAAAAHHHHHHHHHHHHHHHH");
                    break;
                }
                if (enemies.size() == 0) {
                    System.out.println("############################################################################################");
                    displayMessage("Congratulations fellow adventurer(s)! You can now proceed with the next fight.");
                    break;
                }

                // Au tour du héro/ennemie suivant
                ixHero = (ixHero + 1) % heros.size();
                ixEnemy = (ixEnemy + 1) % enemies.size();
            }
            //Méchanisme de récompenses

            for (int n =0; n < heros.size();n++){
                System.out.println("Since you won this fight, "+heros.get(n).getName()+" can choose a reward ! ");
                heros.get(n).chooseReward();
            }
            //permet de rajouter un ennemie à chaque fois que la boucle se répète
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
            goodOne.doAction(enemies, heros, ixHero);

            if (badOne.getHealthPoint()>0){
                badOne.fight(goodOne);
                displayMessage("> " + badOne.getName()
                        + " attack " + goodOne.getName() + " ! (-"+badOne.getDamagePoints()+"♥)");
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
            System.out.print(c.getName() + "(♥" + c.getHealthPoint()+ "/" + c.maxHp+ ") ");
        }
        System.out.println();
        System.out.print(" Enemies : ");
        for (Combatant c: e) {
            System.out.print(c.getName() + "(♥" + c.getHealthPoint()+ "/" + c.maxHp+ ") ");
        }
        System.out.println();
        System.out.println("------------------------------------------------------------------------");
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}