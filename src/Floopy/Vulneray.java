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
public class Vulneray extends ItemStuff {


    public Vulneray() {
        super("Health Potion");
        setUses(4);

    }
    
    public void use(){
    owner.setHp(owner.getHp()+200);
    setUses(getUses()-1);
    if(getUses()==0){
    owner.getInventory().remove(this);
    }
    }
    
//getters and setters
    @Override
    public int getUses() {
        return uses;
    }

    //this is a health potion
    @Override
    public Hero getOwner() {
        return owner;
    }

    @Override
    public void setUses(int uses) {
        this.uses = uses;
    }

    @Override
    public void setOwner(Hero owner) {
        this.owner = owner;
    }
}
