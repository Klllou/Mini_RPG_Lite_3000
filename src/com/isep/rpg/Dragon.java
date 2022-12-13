package com.isep.rpg;

import java.util.List;

public class Dragon extends Enemy {

        public Dragon() {
        // Le dragon possède 5 points de vie et inflige 3 points de dégats
        super( "dragon",10, 0);
        this.sign = "\uD83D\uDC09";
        }

        @Override
        public void doAction(List<Combatant> enemies,List<Combatant> heros, int ixHero) {

        }

        @Override
        public void chooseReward() {

        }

// Implémentation de la méthode abstraite "fight" par le dragon
        /*@Override
        public void fight(Combatant combatant) {
            combatant.lose(getDamagePoints() );
        }*/
}