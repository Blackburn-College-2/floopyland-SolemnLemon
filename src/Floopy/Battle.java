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

    public Battle(Hero a, Hero b) {
        System.out.println("combatStarted");
        this.a = a;
        this.b = b;
        a.setInBattle(true);
        b.setInBattle(true);
        a.setCombat(this);
        b.setCombat(this);
    }

    public void fight() {
        if (b.haseTome()) {
            int damageToBeDone = (int) 0.9 * a.attack();
            int damageDone = a.confirmAttack(b.defend(damageToBeDone));
            a.setHp(a.getHp() - (((int) Math.round(damageDone * 0.01))));
            System.out.println(a.getName() + " did " + damageDone + " to " + b.getName());
        } else {
            int damageToBeDone = a.attack();
            int damageDone = a.confirmAttack(b.defend(damageToBeDone));
            System.out.println(a.getName() + " did " + damageDone + " to " + b.getName());
        }

        
        swap();
    }

    public void end(Hero loser) {
        System.out.println("combat between " + a.getName() + " and " + b.getName() + "has ended " + loser.getName() + " has died");
        a.setCombat(null);
        b.setCombat(null);
        a.setInBattle(false);
        b.setInBattle(false);

    }

    public void swap() {
        Hero c = a;
        a = b;
        b = c;
    }
}
