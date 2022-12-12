package com.isep.rpg;

import java.util.Scanner;

public class Healer extends SpellCaster {

    public Healer(String n) {
    // Le guerrier possède 5 points de vie
        super(n, 4);
        this.sign = "⚕";
}

    // Implémentation de la méthode abstraite "fight" par le guerrier
    @Override
    public void fight(Combatant combatant) {
        combatant.lose( weapon.getDamagePoints() );
    }

    // Implémentation de la méthode abstraite "take" par le guerrier :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"


    @Override
    public void doAction(Combatant combatant){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("You have  \uD83D\uDCA7"+ mana + " mana left, what do you want to do ?");
            System.out.println("    (1) Attack");
            System.out.println("    (2) Heal (use 15 \uD83D\uDCA7mana)");
            System.out.println("    (3) Eat ");
            System.out.println("    (4) Use Potion to restaure mana ");
            String action = scanner.nextLine();
            switch (action) {
                case "1":
                    fight(combatant);
                    return;
                case "2":
                    if (mana >=15){
                        System.out.println("Who do you want to heal ?");

                        System.out.println();
                        healerHeal();
                        mana -= 15;
                    } else {
                        System.out.println("Not enough mana to heal...");
                    }
                    return;
                case "3":
                    itemHeal();
                    return;
                case "4","use potion":
                    usePotion();
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
    public void healerHeal() {
        gain(5);

    }

}

