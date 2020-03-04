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
public class StrengthPotion extends ItemStuff {
     int countDown;
    public StrengthPotion() {
        super("Strength Potion");
        setUses(4);
       super.color="red";
    }
    
    @Override
    public void use() {
        getOwner().setBaseAr((int)Math.round(getOwner().getBaseAr() * 1.1));
         this.countDown=20;
        setUses(getUses()-1);
        if(getUses()==0){
        remove();
        }
        
    }
     @Override
    public void tick(){
    countDown--;
    if(countDown==0){
    owner.setBaseAr(owner.getBaseAr()*100/110);
    
    }
    }
}
