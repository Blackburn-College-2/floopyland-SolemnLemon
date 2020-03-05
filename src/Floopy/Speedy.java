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
public class Speedy extends Hero{
    
    public Speedy(String name, GameBoard gameBoard, Point position) {
        super(name, gameBoard, position);
        //speed is high enough that they will always go first
        super.setSpeed(100);
        super.color="blue";
        super.type="Speedy";

    }
    
}
