package com.isep.rpg;

import java.util.List;
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
    public void doAction(List<Combatant> enemies, List<Combatant> heros, int ixHero){
        Scanner scanner = new Scanner(System.in);
        int index;
        while (true) {
            System.out.println("you have  \uD83D\uDCA7" + mana + " mana left, what do you want to do ?");
            System.out.println("    (1) Attack (-5 \uD83D\uDCA7mana) (-"+ weapon.getDamagePoints()+"\uD83D\uDCA5)");
            System.out.println("    (2) \uD83C\uDF72Eat (+♥5)");
            System.out.println("    (3) Use ⚗Potion (+\uD83D\uDCA720)");
            String action = scanner.nextLine();
            switch (action) {
                case "1","attack":
                    if (mana>=5) {
                        if (enemies.size() == 1 ){
                            fight(enemies.get(0));
                            System.out.println("> "+ heros.get(ixHero).getName() + " attack " + enemies.get(0).getName() + " ! " );
                            if (isAlive(enemies, 0)) {
                                enemies.remove(0);
                            }
                        } else {
                            System.out.println("Who do you want to attack (-" +  weapon.getDamagePoints()+"\uD83D\uDCA5)");
                            for (int k = 1; k<enemies.size()+1; k++){
                                System.out.println("    ("+ k + ")" + enemies.get(k-1).getName() +" ♥"+ enemies.get(k-1).getHealthPoint());
                            }
                            System.out.println();
                            index = scanner.nextInt()-1;
                            fight(enemies.get(index));
                            System.out.println("> "+ heros.get(ixHero).getName() + " attack " + enemies.get(index).getName() + " ! " );
                            if (isAlive(enemies, index)) {
                                enemies.remove(index);
                            }
                        }
                        mana -= 5;
                    } else {
                        System.out.println("Not enough mana to attack...");
                    }
                    return;
                case "2","eat":
                    useFood();
                    System.out.println("> "+ heros.get(ixHero).getName() + " has eaten a delicious \uD83C\uDF72meal !" );
                    return;
                case "3","use potion":
                    usePotion();
                    System.out.println("> "+ heros.get(ixHero).getName() + " has drunk a suspicious ⚗Potion.. But he recovered some mana !" );
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
}

