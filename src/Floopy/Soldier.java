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
public class Soldier extends Hero{
        
     public Soldier(String name, GameBoard gameBoard, Point position) {
        super(name, gameBoard, position);
        setInventorySize(4);
        super.color="red";

    }
}
