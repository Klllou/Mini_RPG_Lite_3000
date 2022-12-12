package com.isep.rpg;

public class Warrior extends Hero {

    public Warrior(String n) {
        // Le guerrier possède 5 points de vie
        super(n, 5);
        this.sign = "\uD83D\uDDE1";
    }

    // Implémentation de la méthode abstraite "fight" par le guerrier
    @Override
    public void fight(Combatant combatant) {
        combatant.lose( weapon.getDamagePoints());
    }

    // Implémentation de la méthode abstraite "take" par le guerrier :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"


}