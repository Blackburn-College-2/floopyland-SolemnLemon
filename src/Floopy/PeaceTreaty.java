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
    public void pickUp(){
    countDown=15;
    owner.setPeace(true);
    }
      @Override
    public void tick(){
    countDown--;
    if(countDown==0){
   owner.setPeace(false);
    }  
    }
}
    
    