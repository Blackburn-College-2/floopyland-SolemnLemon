/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

import com.pauliankline.floopyconnector.BaseHero;
import com.pauliankline.floopyconnector.GameBoard;
import com.pauliankline.floopyconnector.Item;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * General version of hero for all heroes to inherit from
 *
 * @author sunbe
 */
public class Hero extends BaseHero {

    Random random = new Random();
    //an array so that it has a defined size 
    int inventorySize = 3;
    ArrayList<Item> inventory;
    boolean living = true;
    boolean inBattle = false;
    String name;
    int baseAr;

    public Hero(String name, GameBoard gameBoard, Point position) {
        
        super(gameBoard, position);
        System.out.println("test");
        this.name = name;
        baseAr = 10;
        getBoard().getGameSquare(getLocation()).addHero(this);
        
        setHp(100);
        super.color="red";
    }

    /**
     * returns Boolean of wether hero is in combat or not
     *
     * @return inBattle attribute
     */
    @Override
    public boolean isInBattle() {
        return inBattle;
    }

    /**
     * returns Boolean showing if here is alive or dead
     *
     * @return the opposite of the "living" attribute
     */
    @Override
    public boolean isDead() {
        return !living;
    }

    /**
     * to be run whenever a heroes health is 0 or some other loss condition is
     * reached for said hero sets position to null to remove hero from the board
     */
    @Override
    public void die() {
 
        this.location.setLocation(null);
        setLiving(false);

    }

    /**
     * chooses what the hero does on the given tick
     *
     * @param gameTick current tick being acted on
     */
    @Override
    public void gameTickAction(long gameTick) {
        if (!isInBattle()) {
            if (getBoard().getGameSquare(getLocation()).hasItems()) {
                pickUp(getBoard().getGameSquare(getLocation()).getItems().get(0));
            } else {
                move();
            }

        }

    }

    /**
     * sets hero's location to randomly chosen adjacent one also picks up 1 item
     * on the new tile.
     */
    public void move() {

        location = chooseRandomDirection();
        getBoard().getGameSquare(getLocation()).addHero(this);
//checks square for items then adds them if their are any
        if (getBoard().getGameSquare(location).hasItems()) {
            pickUp(getBoard().getGameSquare(getLocation()).getItems().get(0));
            getBoard().getGameSquare(getLocation()).getItems().remove(0);

        }
    }

    public ArrayList<Hero> scan() {
        //good lord this is going to be hellish
        ArrayList<Hero> enemys = new ArrayList();

        return enemys;
    }
/**
 * checks a square if it has an enemy in it and returns it
 * also manages wrapping
 * @param x x value of square
 * @param y value of square
 * @return hero in the square
 */
    public BaseHero checkSquare(int x, int y) {
        if (getBoard().getGameSquare(new Point(xWrap(x), yWrap(y))).heroesArePresent()) {
            return getBoard().getGameSquare(new Point(xWrap(x), yWrap(y))).getHeroesPresent().get(0);
        }
        return null;
    }

    public int yWrap(int y) {
        if (y < 0) {
            y = getBoard().getHeight() - 1;
        }
        return y % getBoard().getHeight();
    }

    public int xWrap(int x) {
        if (x < 0) {
            x = getBoard().getHeight() - 1;
        }
        return x % getBoard().getHeight();
    }

    /**
     * checks if inventory has space for an item and if it does it adds it to
     * it.
     *
     * @param item
     */
    public void pickUp(Item item) {
        if (inventory.size() < inventorySize) {
            inventory.add(item);

        }
    }

    /**
     * chooses a random place within 1 tile to move to if moving exceeds the map
     * boarders the hero moves to a point on the other side of the map this
     * gives the map a "looping" effect to it
     *
     * @return new location hero will have after moving
     */
    public Point chooseRandomDirection() {
        Point[] direction = new Point[4];
        direction[0] = new Point((int) getLocation().getX() + 1, (int) getLocation().getY());
        direction[1] = new Point((int) getLocation().getX() - 1, (int) getLocation().getY());
        direction[2] = new Point((int) getLocation().getX(), (int) getLocation().getY() + 1);
        direction[3] = new Point((int) getLocation().getX(), (int) getLocation().getY() - 1);

        Point newSquare = direction[random.nextInt(4)];
        if (newSquare.getX() > getBoard().getWidth()) {
            newSquare = new Point(0, (int) newSquare.getY());
        }
        if (newSquare.getX() < 0) {
            newSquare = new Point(getBoard().getWidth(), (int) newSquare.getY());
        }
        if (newSquare.getY() > getBoard().getHeight()) {
            newSquare = new Point((int) newSquare.getX(), 0);
        }
        if (newSquare.getY() < 0) {
            newSquare = new Point((int) newSquare.getX(), (int) getBoard().getHeight());
        }
        return newSquare;
    }

    /**
     * I have no idea what this is supposed to do name isn't clear and
     * documentation is blank
     *
     * @return
     */
    @Override
    public String enemy() {
       
        return null;
    }

    //getters and setters
    public void setLiving(boolean living) {
        this.living = living;
    }

    public void setInBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }

    public Point getLocation() {
        return location;
    }

    public GameBoard getBoard() {

        return this.gameboard;
    }

    /**
     *
     * @return damage value that would be done before defensive abilities are
     * applied
     */
    public int attack() {
        return baseAr + random.nextInt(10);
    }

    /**
     * currently does nothing but some hero's abilities effect how damage is
     * done to them others care about how much damage is done this will be
     * overridden by hero's subclasses if this is the case
     *
     * @param incomingDamage damage to be done by the attacker
     * @return damage done to the defender
     */
    public int defend(int incomingDamage) {
        takeDamage(incomingDamage);
        return incomingDamage;
    }

    public void takeDamage(int incomingDamage) {
        setHp(getHp() - incomingDamage);
    }

    /**
     * called by the attacker inputting the output of defend confirms what
     * damage the attack did only currently used for healers
     *
     * @param damageDone damage done by the hero
     */
    public int confirmAttack(int damageDone) {
        return damageDone;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }
}
