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
public class Thief extends Hero {

    public Thief(String name, GameBoard gameBoard, Point position) {
        super(name, gameBoard, position);
        super.color = "grey";
        super.type="thief";
    }
/**
 * unique attack for thieves that has 50% chance to steal a random item from the enemy
 * and use it instead of attacking
 * @return 
 */
     @Override
    public int attack() {
    if (random.nextBoolean()) {
    return baseAr + random.nextInt(100);
    }
    if (!(getEnemy().getInventory().isEmpty())) {
    ((ItemStuff)getEnemy().getInventory().get(0)).setOwner(this);
    ((ItemStuff)getEnemy().getInventory().get(0)).use();
    }
    return 0;
    }
}
