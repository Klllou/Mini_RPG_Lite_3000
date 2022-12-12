package com.isep.rpg;

import java.util.List;
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
    public void doAction(List<Combatant> enemies, List<Combatant> heros, int ixHero){
        Scanner scanner = new Scanner(System.in);
        int index;
        while (true) {
            System.out.println("You have  \uD83D\uDCA7"+ mana + " mana left, what do you want to do ?");
            System.out.println("    (1) ⚔Attack (-"+ weapon.getDamagePoints()+"\uD83D\uDCA5)");
            System.out.println("    (2) Heal (-15 \uD83D\uDCA7mana)");
            System.out.println("    (3) \uD83C\uDF72Eat (+♥5)");
            System.out.println("    (4) Use ⚗Potion  (+\uD83D\uDCA720)");
            String action = scanner.nextLine();
            switch (action) {
                case "1":
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
                    return;
                case "2":
                    if (mana >=15){
                        System.out.println("Who do you want to heal ?");
                        for (int i = 0; i<heros.size(); i++){
                            System.out.println("("+ i + ")" + heros.get(i).getName() + " ");
                        }
                        System.out.println();
                        int indey = scanner.nextInt();
                        System.out.println();
                        heros.get(indey).healerHeal();
                        System.out.println("> "+ heros.get(indey).getName() + " has been healed  ! " );
                        mana -= 15;
                    } else {
                        System.out.println("Not enough mana to heal...");
                    }
                    return;
                case "3":
                    useFood();
                    System.out.println("> "+ heros.get(ixHero).getName() + " has eaten a delicious \uD83C\uDF72meal !" );
                    return;
                case "4","use potion":
                    usePotion();
                    System.out.println("> "+ heros.get(ixHero).getName() + " has drunk a suspicious ⚗Potion.. But he recovered some mana !" );
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }


}

