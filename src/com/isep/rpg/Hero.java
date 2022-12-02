package com.isep.rpg;

import java.util.Scanner;

public abstract class Hero extends Combatant {

    public Hero(String n, int h) {
        super(n, h);
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
                    itemHeal();
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
    // Abstrait car n'importe quel hero peut prendre un objet mais son
    // utilisation dépend du type du héro (une arme n'est pas utile à un mage)
    public abstract void take(Item item);
}
