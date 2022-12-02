package com.isep.rpg;

import java.util.Scanner;

public class Mage extends Hero{
    public Mage(String n) {
        // Le guerrier possède 5 points de vie
        super(n, 3);
    }

    // Implémentation de la méthode abstraite "fight" par le guerrier
    @Override
    public void fight(Combatant combatant) {
        combatant.lose( 7/*weapon.getDamagePoints()*/ );
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
            System.out.println("What do you want to do ?");
            System.out.println("    (1) Attack");
            System.out.println("    (2) Eat / Heal yourself");
            String action = scanner.nextLine();
            switch (action) {
                case "1","attack":
                    fight(combatant);
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

