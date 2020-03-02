/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

import com.pauliankline.floopyconnector.BaseHero;
import com.pauliankline.floopyconnector.GameBoard;
import com.pauliankline.floopyconnector.GameController;
import java.awt.Point;
import java.util.ArrayList;

/**
 * subclass controller
 *
 * @author sunbe
 */
public class Game extends GameController {

    GameBoard board;

    public Game() {
        board = mkGameBoard();

        createHeroes(getBoard(), 2);

    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    /**
     * creates game board used in the game and adds items (not done yet)
     *
     * @return
     */
    @Override
    public GameBoard mkGameBoard() {

        return new GameBoard(5, 5);
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
        heroes.add(new Hero("Magnet Man", getBoard(), new Point(1, 1)));

        heroes.add(new Hero("Magnet Woman", getBoard(), new Point(1, 2)));
        return heroes;
    }
}
