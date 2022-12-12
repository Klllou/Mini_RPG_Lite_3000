package com.isep.rpg;

import java.util.Scanner;


public class Mage extends SpellCaster{


    public Mage(String n) {
        // Le guerrier possède 5 points de vie
        super(n, 3);
        this.sign = "\uD83E\uDDD9";
    }

    // Implémentation de la méthode abstraite "fight" par le guerrier
    @Override
    public void fight(Combatant combatant) {
        combatant.lose( weapon.getDamagePoints());
    }

    // Implémentation de la méthode abstraite "take" par le guerrier :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"


    @Override
    public void doAction(Combatant combatant){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("you have  \uD83D\uDCA7" + mana + " mana left, what do you want to do ?");
            System.out.println("    (1) Attack (use 5 \uD83D\uDCA7mana)");
            System.out.println("    (2) Eat ");
            System.out.println("    (3) Use Potion to restaure mana ");
            String action = scanner.nextLine();
            switch (action) {
                case "1","attack":
                    if (mana>=5) {
                        fight(combatant);
                        mana -= 5;
                    } else {
                        System.out.println("Not enough mana to attack...");
                    }
                    return;
                case "2","eat":
                    useFood();
                    return;
                case "3","use potion":
                    usePotion();
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
}

