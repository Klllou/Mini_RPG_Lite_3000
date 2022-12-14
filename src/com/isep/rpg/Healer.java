package com.isep.rpg;

import java.util.List;
import java.util.Scanner;

public class Healer extends SpellCaster {

    public Healer(String n) {
        // Le guerrier possède 5 points de vie
        super(n, 80);
        this.sign = "⚕";
        this.maxHp = 80;
    }

    // Implémentation de la méthode abstraite "fight" par le guerrier
    @Override
    public void fight(Combatant combatant) {
        combatant.lose( weapon.getDamagePoints() );
    }

    // Implémentation de la méthode abstraite "take" par le guerrier :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"


    @Override
    public void doAction(List<Combatant> enemies, List<Combatant> heros, int ixHero){
        Scanner scanner = new Scanner(System.in);
        int index;
        while (true) {
            System.out.println("He has \uD83C\uDF72" + food.size()+ " left and ⚗"+ potions.size()+" left.");
            System.out.println("You have  \uD83D\uDCA7"+ mana + " mana left, what do you want to do ?");
            System.out.println("    (1) ⚔Attack (-"+ weapon.getDamagePoints()+"\uD83D\uDCA5)");
            System.out.println("    (2) Heal (-15 \uD83D\uDCA7mana) (+♥30)");
            if (!food.isEmpty()){
                System.out.println("    (2) \uD83C\uDF72Eat (+"+ food.get(0).getHpToHeal()+"♥)");
            }
            if (!potions.isEmpty()){
                System.out.println("    (4) Use ⚗Potion (+\uD83D\uDCA720)");
            }

            String action = scanner.nextLine();
            switch (action) {
                case "1":
                    if (enemies.size() == 1 ){
                        for (int n = 0 ; n<5; n++){
                            System.out.println();
                        }
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
                        fight(enemies.get(index));
                        System.out.println("> "+ heros.get(ixHero).getName() + " attack " + enemies.get(index).getName() + " ! (-"+weapon.getDamagePoints()+"\uD83D\uDCA5)" );
                        if (isAlive(enemies, index)) {
                            System.out.println(enemies.get(index).getName() + " has been defeat, well done !");
                            enemies.remove(index);
                        }
                    }
                    return;
                case "2":
                    if (mana >=15){
                        System.out.println("Who do you want to heal ?");
                        for (int i = 0; i<heros.size(); i++){
                            System.out.println("("+ (i+1) + ")" + heros.get(i).getName() + " ");
                        }
                        System.out.println();
                        int indey = scanner.nextInt()-1;
                        System.out.println();
                        heros.get(indey).healerHeal();
                        System.out.println("> "+ heros.get(indey).getName() + " has been healed  ! " );
                        mana -= 15;
                    } else {
                        System.out.println("Not enough mana to heal...");
                    }
                    return;
                case "3":
                    heros.get(ixHero).useFood();
                    System.out.println("> "+ heros.get(ixHero).getName() + " has eaten a delicious \uD83C\uDF72meal !" );
                    return;
                case "4","use potion":
                    heros.get(ixHero).usePotion();
                    if (!potions.isEmpty()){
                        System.out.println("> "+ heros.get(ixHero).getName() + " has drunk a suspicious ⚗potion.. But he recovered some mana !" );
                    } else {
                        System.out.println("> "+ heros.get(ixHero).getName() + " don't have any ⚗potion left..");
                    }

                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
    @Override
    public void chooseReward(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Which reward do you want ?");
            System.out.println("    (1) increase ⚔Attack (+2\uD83D\uDCA5)");
            System.out.println("    (2) Earn a meal (+\uD83C\uDF721)");
            System.out.println("    (3) Earn a suspicious Potion (+⚗1)");
            System.out.println("    (4) increase meal efficiency (+♥5)");
            System.out.println("    (5) increase potion efficiency (+\uD83D\uDCA75)");
            String reward = scanner.nextLine();
            switch (reward) {
                case "1":
                    this.weapon.increaseDamagePoints();
                    return;
                case "2":
                    food.add(new Food("delicious Meal"));
                    return;
                case "3":
                    potions.add(new Potion());
                    return;
                case "4":
                    for (Food value : food) value.setHpToHeal();
                    return;
                case "5":
                    for (Potion potion : potions) potion.setManaToRegenerate();
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }


}

