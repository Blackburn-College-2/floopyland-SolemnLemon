/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

import com.pauliankline.floopyconnector.Item;

/**
 *
 * @author sunbe
 */
public abstract class ItemStuff extends Item {

    public ItemStuff(String name) {
        super(name);

    }
    public void tick(){}

    public abstract void use();
    int uses = 4;
    Hero owner;
    

    public void remove() {
     
            owner.getInventory().remove(this);
        }
        //getters and setters
    public int getUses() {
        return uses;
    }
public void pickUp(){}
    //this is a health potion
    public Hero getOwner() {
        return owner;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

    public void setOwner(Hero owner) {
        this.owner = owner;
    }
public String getName(){
return this.name;
}
}
