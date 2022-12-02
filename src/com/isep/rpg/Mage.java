package com.isep.rpg;

import java.util.Scanner;


public class Mage extends Hero{

    int mana = 100;
    public Mage(String n) {
        // Le guerrier possède 5 points de vie
        super(n, 3);
    }

    // Implémentation de la méthode abstraite "fight" par le guerrier
    @Override
    public void fight(Combatant combatant) {
        combatant.lose( weapon.getDamagePoints());
    }

    // Implémentation de la méthode abstraite "take" par le guerrier :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"
    @Override
    public void take(Item item) {
        if (item instanceof Weapon) {
            weapon = (Weapon) item;
        } else {
            Game.displayMessage("Oups ! " + item.getName() + " est inutile...");
        }
    }

    @Override
    public void doAction(Combatant combatant){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("you have  \uD83D\uDCA7" + mana + " mana left, what do you want to do ?");
            System.out.println("    (1) Attack (use 5 \uD83D\uDCA7mana");
            System.out.println("    (2) Eat ");
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
                    itemHeal();
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
    private Weapon weapon;
}

