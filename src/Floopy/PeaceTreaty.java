/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

/**
 *upon pickup the owner can't fight for 15 ticks
 * @author sunbe
 */
public class PeaceTreaty extends ItemStuff{
     int countDown;
    public PeaceTreaty() {
        super("Peace Treaty");
      
       super.color="white";
    }
    
    @Override
    public void use() {
        
        }
    
    /**
     * unique pickup for peace treaty that sets the Boolean Peace in hero to true
     */
    /*  @Override
    public void pickUp(){
    countDown=15;
    owner.setPeace(true);
    owner.getBoard().getGameSquare(owner.getLocation()).getItems().remove(this);
    }*/
    /**
     * counts down on each tick then removes from inventory
     */ 
    @Override
    public void tick(){
    countDown--;
    if(countDown==0){
   owner.setPeace(false);
    }  
    }
}
    
    