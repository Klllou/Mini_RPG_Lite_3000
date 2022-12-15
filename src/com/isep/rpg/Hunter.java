package com.isep.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hunter extends Hero{
    List<Arrow> arrows = new ArrayList<>();
    public Hunter(String n) {
        // Le guerrier possède 5 points de vie
        super(n, 70);
        this.sign = "\uD83C\uDFF9";
        for (int i =0; i<20;i++) {
            arrows.add(new Arrow());
        }
        this.maxHp = 70;
    }

    // Implémentation de la méthode abstraite "fight" par le guerrier
    @Override
    public void fight(Combatant combatant) {
        combatant.lose( weapon.getDamagePoints());
    }

    // Implémentation de la méthode abstraite "take" par le guerrier :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"


    @Override
    public void doAction(List<Combatant> enemies, List<Combatant> heros, int ixHero){
        Scanner scanner = new Scanner(System.in);
        int index;
        while (true) {
            System.out.println("What do you want to do ?");
            System.out.println("    (1) ⚔Attack (-"+ weapon.getDamagePoints()+"\uD83D\uDCA5) ");
            System.out.println("    (2) \uD83C\uDF72Eat (+♥40)");
            String action = scanner.nextLine();
            switch (action) {
                case "1","attack":
                    if (enemies.size() == 1 ){
                        for (int n = 0 ; n<5; n++){
                            System.out.println();
                        }
                        useArrow();
                        fight(enemies.get(0));
                        System.out.println("> "+ heros.get(ixHero).getName() + " attack " + enemies.get(0).getName() + " ! (-"+weapon.getDamagePoints()+"\uD83D\uDCA5)" );
                        if (isAlive(enemies, 0)) {
                            System.out.println(enemies.get(0).getName() + " has been defeat, well done !");
                            enemies.remove(0);

                        }
                    } else {
                        System.out.println("Who do you want to attack (-" +  weapon.getDamagePoints()+"\uD83D\uDCA5)");
                        for (int k = 1; k<enemies.size()+1; k++){
                            System.out.println("    ("+ k + ")" + enemies.get(k-1).getName() +" ♥"+ enemies.get(k-1).getHealthPoint());
                        }

                        while(true) {
                            index = scanner.nextInt()-1;
                            if(index >= 0 && index<enemies.size()){
                                break;
                            } else {
                                System.out.println("Wrong input, please choose again");
                            }
                        }
                        for (int n = 0 ; n<5; n++){
                            System.out.println();
                        }
                        useArrow();
                        fight(enemies.get(index));
                        System.out.println("> "+ heros.get(ixHero).getName() + " attack " + enemies.get(index).getName() + " ! (-"+weapon.getDamagePoints()+"\uD83D\uDCA5)" );
                        if (isAlive(enemies, index)) {
                            System.out.println(enemies.get(index).getName() + " has been defeat, well done !");
                            enemies.remove(index);
                        }
                    }
                    return;
                case "2","eat":
                    heros.get(ixHero).useFood();
                    System.out.println("> "+ heros.get(ixHero).getName() + " has eaten a delicious \uD83C\uDF72meal !" );
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
    public void useArrow(){
        if (!arrows.isEmpty()){
            arrows.remove(0);
            System.out.println((arrows.size()+1)+ " arrows left");
        } else {
            weapon = (new Weapon("dague", 15));
            System.out.println("The Hunter don't have arrows anymore");
            System.out.println("He will now use his \uD83D\uDDE1dagger");
        }
    }

    @Override
    public void chooseReward(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Which reward do you want ?");
            System.out.println("    (1) increase ⚔Attack (+2\uD83D\uDCA5)");
            System.out.println("    (2) Earn a meal (+\uD83C\uDF721)");
            System.out.println("    (3) increase meal effect (+♥5)");
            System.out.println("    (4) get new arrows (+5 arrows)");
            String reward = scanner.nextLine();
            switch (reward) {
                case "1":
                    this.weapon.increaseDamagePoints();
                    return;
                case "2":
                    food.add(new Food("delicious Meal"));
                    return;
                case "3":
                    for (Food value : food) value.setHpToHeal();
                    return;
                case "4":
                    for (int i = 0; i < 5;i++)
                        arrows.add(new Arrow());
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
}

