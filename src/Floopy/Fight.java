/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

/**
 * for one attack of a fight having one attacker and one defender represents one
 * game tick of a battle
 *
 * @author sunbe
 */
public class Fight {

    Hero attacker;
    Hero defender;

    public Fight(Hero attacker, Hero defender) {
        this.attacker = attacker;
        this.defender = defender;
        int damageToBeDone = attacker.attack();
        int damageDone = attacker.confirmAttack(defender.defend(damageToBeDone));
        System.out.println(attacker.getName() + " did " + damageDone);

    }

}
