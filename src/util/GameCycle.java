package util;

import game.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameCycle implements ActionListener {

    private Board board;

    public GameCycle(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.board.doGameCycle();
    }
}
