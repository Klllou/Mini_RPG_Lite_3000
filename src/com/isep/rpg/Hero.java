package com.isep.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Hero extends Combatant {

    Weapon weapon;
    Armor armor;

    List<Food> food = new ArrayList<>();
    public Hero(String n, int h) {
        super(n, h);
        for (int i =0; i<5;i++) {
            food.add(new Food("delicious Meal"));
        }
    }

    public void useFood(){
        if (!food.isEmpty()){
            this.gain(food.get(0).getHpToHeal());
            food.remove(0);
        }
    }

    public void doAction(List<Combatant> enemies, List<Combatant> heros, int ixHero){
        Scanner scanner = new Scanner(System.in);
        int index;
        while (true) {
            System.out.println("What do you want to do ?");
            System.out.println("    (1) ⚔Attack (-"+ weapon.getDamagePoints()+"\uD83D\uDCA5)");
            System.out.println("    (2) \uD83C\uDF72Eat (+"+ food.get(0).getHpToHeal()+"♥)");
            String action = scanner.nextLine();
            switch (action) {
                case "1":
                    if (enemies.size() == 1 ){
                        fight(enemies.get(0));
                        System.out.println("> "+ heros.get(ixHero).getName() + " attack " + enemies.get(0).getName() + " ! " );
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
                        fight(enemies.get(index));
                        System.out.println("> "+ heros.get(ixHero).getName() + " attack " + enemies.get(index).getName() + " ! " );
                        if (isAlive(enemies, index)) {
                            System.out.println(enemies.get(index).getName() + " has been defeat, well done !");
                            enemies.remove(index);
                        }
                    }
                    return;
                case "2":
                    useFood();
                    System.out.println("> "+ heros.get(ixHero).getName() + " has eaten a delicious \uD83C\uDF72meal !" );
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }

    public void setWeapon(String weaponName, int damagePoints){
        this.weapon = new Weapon(weaponName, damagePoints);
    }


    public void setArmor(String armorName, int armorPoints){
        this.armor = new Armor(armorName, armorPoints);
    }
    // Abstrait car n'importe quel hero peut prendre un objet mais son
    // utilisation dépend du type du héro (une arme n'est pas utile à un mage)

    public void chooseReward(){

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Which reward do you want ?");
            System.out.println("    (1) increase ⚔Attack (+2\uD83D\uDCA5)");
            System.out.println("    (2) Earn a meal (+\uD83C\uDF721)");
            System.out.println("    (3) increase meal efficiency (+♥2)");
            String reward = scanner.nextLine();
            switch (reward) {
                case "1":
                   this.weapon.increaseDamagePoints();
                    return;
                case "2":
                    food.add(new Food("delicious Meal"));
                    return;
                case "3":
                    for (int i = 0; i < food.size();i++)
                        food.get(i).setHpToHeal();
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
}
