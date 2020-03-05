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
public class Healer extends Hero {

    public Healer(String name, GameBoard gameBoard, Point position) {
        super(name, gameBoard, position);
        super.color = "green";
        super.type = "Healer";
    }

    @Override
    public int confirmAttack(int damageDone) {
        setHp((int) Math.round(getHp() + damageDone * .25));
        if (getHp() > 1000) {
            setHp(1000);
        }
        return damageDone;
    }
}
