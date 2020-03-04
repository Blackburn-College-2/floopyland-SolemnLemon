/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

/**
 *
 * @author sunbe
 */
public class Tome extends ItemStuff{
    public Tome(){
    super("Tome o' Misdirection");
    }
    public void pickUp(){
    owner.setTome(true);
    }
    
    @Override
    public void use(){//empty because tome can't be used
        
    }
    
}
