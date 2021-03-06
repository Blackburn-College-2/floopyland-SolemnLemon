/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

import com.pauliankline.floopyconnector.BaseHero;
import com.pauliankline.floopyconnector.GameBoard;
import com.pauliankline.floopyconnector.Item;
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
    boolean living = true;
    boolean inBattle = false;

    boolean peace = false;
    boolean tome = false;
    int baseAr;
    int speed;
    //only applicable if in combat otherwise will be null
    Battle combat;

    Hero enemy;

    public Hero(String name, GameBoard gameBoard, Point position) {
        super(gameBoard, position);
        this.speed = random.nextInt(8);
        this.name = name;
        baseAr = 50;
        getBoard().getGameSquare(position).addHero(this);

        super.color = "red";

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
        getBoard().getGameSquare(getLocation()).getHeroesPresent().remove(this);
        //this.location.setLocation(null);
        setLiving(false);

        getCombat().end(this);

    }
/**
 * uses a health potion if low health or strength potion if has one
 * @return if an item was used during the call
 */
    public boolean useItem() {
        System.out.println("using an item");
        for (int i = 0; i < getInventory().size(); i++) {
            if (getInventory().get(i) instanceof Vulneray) {
                if (getHp() < 1000) {
                    ((ItemStuff) getInventory().get(i)).use();
                    System.out.println(getName() + " used a health potion.");
                    return true;
                }
            }
            if (getInventory().get(i) instanceof StrengthPotion) {
                ((ItemStuff) getInventory().get(i)).use();
                System.out.println(getName() + "used a Strength Potion");
                return true;
            }
        }
        System.out.println("failed to used an item ");
        return false;
    }

    /**
     * chooses what the hero does on the given tick
     *ticks items with counters
     * A hero can only do one thing per tick using this list of priorities
     * if the higher one cannot be done go one lower
     * fight
     * uses and item if possible 
     * picks up an item if possible
     * move
     * will always detects surroundings and starts fight if anyone is close
     * @param gameTick current tick being acted on
     */
    @Override
    public void gameTickAction(long gameTick) {
        for (int i = 0; i < getInventory().size(); i++) {
            ((ItemStuff) (getInventory().get(i))).tick();
        }
        if (getLiving()) {
            if (!isInBattle()) {
                //uses and item if can if not pick up item on tile if can't moves
                if (!useItem()) {
                    if (getBoard().getGameSquare(getLocation()).hasItems()) {
                        pickUp(getBoard().getGameSquare(getLocation()).getItems().get(0));
                        getBoard().getGameSquare(getLocation()).getItems().remove(0);
                    } else {
                        move();
                    }
                }
                ArrayList<Hero> nearby = scan();
                if (!nearby.isEmpty()) {
                    startCombat(nearby);
                }
            } else {
                System.out.println(isInBattle());
                combat.fight();
            }
        }

    }
/**
 * starts combat with the nearby hero with the highest health
 * hero with higher speed will attack first
 * @param nearby nearby heroes
 */
    public void startCombat(ArrayList<Hero> nearby) {

        Hero activeHero = nearby.get(0);//viewer for ordering 
        if (activeHero.isInBattle()) {
            System.out.println(getName() + " spotted " + activeHero.getName() + " but " + activeHero.getName() + " was already in a fight");
        }else{

        if (nearby.size() >= 1) {
//more than one hero in range
//looks through array list only updating active hero if it comes across a hero with higher health than the running max

            int highestHp = 0;
            for (int i = 0; i < nearby.size(); i++) {
                if (nearby.get(i).getHp() > highestHp) {
                    activeHero = nearby.get(i);
                }
            }
        }
        //checks speed first attacker is hero with higher speed

        if (getSpeed() >= activeHero.getSpeed()) {
            setCombat(new Battle(this, activeHero));
        } else {
            setCombat(new Battle(activeHero, this));
        }
        }
    }

    /**
     * sets hero's location to randomly chosen adjacent on the new tile.
     */
    public void move() {
        getBoard().getGameSquare(getLocation()).removeHero(this);
        location = chooseRandomDirection();
        getBoard().getGameSquare(getLocation()).addHero(this);

    }
/**
 * looks around hero for enemies
 * @return 
 */
    public ArrayList<Hero> scan() {
        //good lord this is going to be hellish
        ArrayList<Hero> enemys = new ArrayList();
        enemys = offsetCheck(-1, 1, enemys);
        enemys = offsetCheck(0, 1, enemys);
        enemys = offsetCheck(1, 1, enemys);
        enemys = offsetCheck(-1, 0, enemys);
        enemys = offsetCheck(1, 0, enemys);
        enemys = offsetCheck(-1, -1, enemys);
        enemys = offsetCheck(0, -1, enemys);
        enemys = offsetCheck(1, -1, enemys);

        return enemys;
    }
/**
 * only to be called by scan
 * checks a given point if it has an enemy
 * @param xOffset
 * @param yOffset
 * @param enemys
 * @return 
 */
    private ArrayList<Hero> offsetCheck(int xOffset, int yOffset, ArrayList<Hero> enemys) {
        Hero camera;
        camera = checkSquare((int) getLocation().getX() + xOffset, (int) getLocation().getY() + yOffset);
        if (!(camera == null)) {
            if (!(camera.isInBattle()) || !hasPeace()) {
                enemys.add(camera);
                System.out.println(getName() + " has spotted an enemy");
            }
        }
        return enemys;
    }

    /**
     * checks a square if it has an enemy in it and returns it also manages
     * wrapping
     *
     * @param x x value of square
     * @param y value of square
     * @return hero in the square
     */
    public Hero checkSquare(int x, int y) {
        if (getBoard().getGameSquare(new Point(xWrap(x), yWrap(y))).heroesArePresent()) {
            return (Hero) getBoard().getGameSquare(new Point(xWrap(x), yWrap(y))).getHeroesPresent().get(0);
        }
        return null;
    }
/**
 * calculates wrapping for y value
 * @param y
 * @return 
 */
    public int yWrap(int y) {
        if (y < 0) {
            y = getBoard().getHeight() - 1;
        }
        return y % getBoard().getHeight();
    }
/**
 * calculates wrapping for x value
 * @param x
 * @return 
 */
    public int xWrap(int x) {
        if (x < 0) {
            x = getBoard().getHeight() - 1;
        }
        return x % getBoard().getHeight();
    }

    /**
     * checks if inventory has space for an item and if it does it adds it to
     * it.
     * @param item
     */
    public void pickUp(Item item) {
        if (inventory.size() < inventorySize) {
            System.out.println(getName() + " picked up a" + item.getClass());
            inventory.add(item);
            ((ItemStuff) item).setOwner(this);
            ((ItemStuff) item).pickUp();

        } else {
            System.out.println(getName() + " tried to pick up a " + item.getClass() + " but was out of inventory space");
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
        newSquare = new Point(xWrap((int) newSquare.getX()), yWrap((int) newSquare.getY()));
        //checks if a hero is at the new point then re does the random if there is
        // I realize this is ridiculously inefficient but it works
        if (getBoard().getGameSquare(newSquare).heroesArePresent()) {
            newSquare = chooseRandomDirection();
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
        if (!(getEnemy() == null)) {
            return getEnemy().getName();
        } else {
            return "no enemy";
        }
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
        return baseAr + random.nextInt(100);
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
        if (getHp() <= 0) {
            die();
        }
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
//getters and setters

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return this.name;
    }

    public int setInventorySize(int i) {
        return this.inventorySize = i;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCombat(Battle combat) {
        this.combat = combat;
    }

    public Battle getCombat() {
        return combat;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public boolean getLiving() {
        return living;
    }

    public int getBaseAr() {
        return baseAr;
    }

    public void setBaseAr(int baseAr) {
        this.baseAr = baseAr;
    }

    public boolean haseTome() {
        return tome;
    }

    public void setTome(boolean tome) {
        this.tome = tome;
    }

    public boolean hasPeace() {
        return peace;
    }

    public void setPeace(boolean peace) {
        this.peace = peace;
    }

    public Hero getEnemy() {
        return enemy;
    }

    public void setEnemy(Hero enemy) {
        this.enemy = enemy;
    }
}
