package com.isep.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Hero extends Combatant {

    Weapon weapon;
    Armor armor;
    List<Potion> food = new ArrayList<>();
    public Hero(String n, int h) {
        super(n, h);
        for (int i =0; i<5;i++) {
            food.add(new Potion());
        }
    }

    public void useFood(){
        if (!food.isEmpty()){
            this.gain(5);
            food.remove(0);
        }
    }

    public void doAction(Combatant combatant){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do ?");
            System.out.println("    (1) Attack");
            System.out.println("    (2) Eat");
            String action = scanner.nextLine();
            switch (action) {
                case "1":
                    fight(combatant);

                    return;
                case "2":
                    useFood();
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

}
