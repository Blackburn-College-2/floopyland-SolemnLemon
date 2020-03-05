/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

import java.util.ArrayList;

/**
 * creates an iteration of one fight between two heroes
 *
 * @author sunbe
 */
public class Battle {

    Hero a;
    Hero b;

    /**
     * creates a new Battle between a passed in hero a and hero b
     *
     * @param a hero that attacks first
     * @param b hero that defends first
     */
    public Battle(Hero a, Hero b) {
        System.out.println(a.getName() + " started a fight with " + b.getName());
        this.a = a;
        this.b = b;
        a.setInBattle(true);
        b.setInBattle(true);
        a.setCombat(this);
        b.setCombat(this);
        a.setEnemy(b);
        b.setEnemy(a);
    }

    /**
     * runs one fight between the the attacker and defender of hero then
     * switches them for the next call
     *
     */
    public void fight() {
        if (b.haseTome()) {
            int damageToBeDone = (int) 0.9 * a.attack();
            int damageDone = a.confirmAttack(b.defend(damageToBeDone));
            a.setHp(a.getHp() - (((int) Math.round(damageDone * 0.01))));
            System.out.println(a.getName() + " did " + damageDone + " to " + b.getName());
        } else {
            int damageToBeDone = a.attack();
            int damageDone = a.confirmAttack(b.defend(damageToBeDone));
            System.out.println(a.getName() + " did " + damageDone + " damage to " + b.getName());
        }
        swap();
    }

    /**
     * sets all combat related things to null and false and prints a message of
     * the winner/loser
     *
     * @param loser the loser of the fight
     */
    public void end(Hero loser) {
        System.out.println("combat between " + a.getName() + " and " + b.getName() + "has ended " + loser.getName() + " has died");
        a.setCombat(null);
        b.setCombat(null);
        a.setInBattle(false);
        b.setInBattle(false);

    }

    /**
     * swaps heroes a and b
     */
    public void swap() {
        Hero c = a;
        a = b;
        b = c;
    }
}
