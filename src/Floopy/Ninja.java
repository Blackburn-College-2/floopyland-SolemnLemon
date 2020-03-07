/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

import com.pauliankline.floopyconnector.GameBoard;
import java.awt.Point;

/**
 *
 * @author sunbe
 */
public class Ninja extends Hero {

    public Ninja(String name, GameBoard gameBoard, Point position) {
        super(name, gameBoard, position);
super.color="white";
super.type="Ninja";
    }
    /**
     * unique defend for ninjas that only takes 75% damage
     * 
     * @param incomingDamage
     * @return 
     */
      public int defend(int incomingDamage) {
        takeDamage((int)Math.round(incomingDamage*.75));
        return incomingDamage;
    }

}
