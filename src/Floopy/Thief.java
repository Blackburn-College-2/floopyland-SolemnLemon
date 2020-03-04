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
    }

    @Override
    public int attack() {
        if (random.nextBoolean()) {
            return baseAr + random.nextInt(100);
        }
        if (!(getEnemy().getInventory().get(0) == null)) {
            ((ItemStuff)getEnemy().getInventory().get(0)).setOwner(this);
            ((ItemStuff)getEnemy().getInventory().get(0)).use();
        }
        return 0;
    }
}
