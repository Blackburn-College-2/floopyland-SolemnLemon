/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

import com.pauliankline.floopyconnector.BaseHero;
import com.pauliankline.floopyconnector.GameBoard;
import com.pauliankline.floopyconnector.GameController;
import com.pauliankline.floopyconnector.Item;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * subclass controller
 *
 * @author sunbe
 */
public class Game extends GameController {

    Random random = new Random();

    /**
     * creates game board used in the game and adds items (not done yet)
     *
     * @return
     */
    @Override
    public GameBoard mkGameBoard() {
        GameBoard board =new GameBoard(10, 10);
ItemStuff hp =new Vulneray();
board.getGameSquare(new Point(1,1)).addItem(hp);
        return board;
    }

    /**
     * populates a board for the game with heroes storing each hero's location
     * in their own location property.
     *
     * @param board board to be populated
     * @param amount amount of heroes
     * @return array list of heroes
     */
    @Override
    public ArrayList<BaseHero> createHeroes(GameBoard board, int amount) {
        ArrayList<BaseHero> heroes = new ArrayList();
        for (int i = 0; i < amount; i++) {
            ArrayList<Hero> subclasses = new ArrayList();
            int r = random.nextInt(4);
            switch (r) {
                case 0:
                    heroes.add(new Tanker(randomNameGenerator(), board, new Point(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()))));
                    break;
                case 1:
                    heroes.add(new Healer(randomNameGenerator(), board, new Point(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()))));
                    break;
                case 2:
                    heroes.add(new Ninja(randomNameGenerator(), board, new Point(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()))));
                    break;
                case 3:
                    heroes.add(new Soldier(randomNameGenerator(), board, new Point(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()))));
                    break;
                default:
                    break;
            }

        }
        return heroes;
    }

    /**
     * generates a random name for each hero
     *
     * @return
     */
    public String randomNameGenerator() {
        String[] names = new String[11];
        names[0] = "Hamburger ";
        names[1] = "Stronk ";
        names[2] = "The unstoppable ";
        names[3] = "Invisible ";
        names[4] = "Dooooom! ";
        names[5] = "Compiler Error ";
        names[6] = "Magic ";
        names[7] = "Healy wheely ";
        names[8] = "John ";
        names[9] = "Super ";
        names[10] = "Peanut Butter";

        String[] names2 = new String[5];
        names2[0] = "Man";
        names2[1] = "Woman";
        names2[2] = "Lad";
        names2[3] = "Lass";
        names2[4] = "Fortnite";

        return names[random.nextInt(11)] + names2[random.nextInt(5)];
    }
}
