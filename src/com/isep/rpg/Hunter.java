package com.isep.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hunter extends Hero{
    List<Arrow> arrows = new ArrayList<>();
    public Hunter(String n) {
        // Le guerrier possède 5 points de vie
        super(n, 4);
        this.sign = "\uD83C\uDFF9";
        for (int i =0; i<2;i++) {
            arrows.add(new Arrow());
        }
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
            System.out.println("What do you want to do ?");
            System.out.println("    (1) ⚔Attack (-"+ weapon.getDamagePoints()+"\uD83D\uDCA5) ");
            System.out.println("    (2) \uD83C\uDF72Eat (+♥5)");
            String action = scanner.nextLine();
            switch (action) {
                case "1","attack":
                    if (enemies.size() == 1 ){
                        fight(enemies.get(0));
                        useArrow();
                        System.out.println("> "+ heros.get(ixHero).getName() + " attack " + enemies.get(0).getName() + " ! " );
                        if (isAlive(enemies, 0)) {
                            enemies.remove(0);
                        }
                    } else {
                        System.out.println("Who do you want to attack (-" +  weapon.getDamagePoints()+"\uD83D\uDCA5) ");
                        for (int k = 1; k<enemies.size()+1; k++){
                            System.out.println("    ("+ k + ")" + enemies.get(k-1).getName() +" ♥"+ enemies.get(k-1).getHealthPoint());
                        }
                        System.out.println();
                        index = scanner.nextInt()-1;
                        fight(enemies.get(index));
                        useArrow();
                        System.out.println("> "+ heros.get(ixHero).getName() + " attack " + enemies.get(index).getName() + " ! " );
                        if (isAlive(enemies, index)) {
                            enemies.remove(index);
                        }
                    }
                    return;
                case "2","eat":
                    useFood();
                    System.out.println("> "+ heros.get(ixHero).getName() + " has eaten a delicious \uD83C\uDF72meal !" );
                    return;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
    public void useArrow(){
        if (!arrows.isEmpty()){
            arrows.remove(0);
            System.out.println((arrows.size()+1)+ " arrows left");
        } else {
            weapon = (new Weapon("dague", 4));
            System.out.println("The Hunter don't have arrows anymore");
            System.out.println("He will now use his \uD83D\uDDE1dagger");
        }
    }

}

