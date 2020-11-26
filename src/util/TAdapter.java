package util;

import entity.Player;
import entity.Shot;
import game.Board;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TAdapter extends KeyAdapter {

    private Board board;

    public TAdapter(Board board){
        this.board = board;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        this.board.getPlayer().keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        Player player = this.board.getPlayer();

        player.keyPressed(e);

        int x = player.getX();
        int y = player.getY();

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {

            if (this.board.isInGame()) {

                if (!this.board.getShot().isVisible()) {

                    this.board.setShot(new Shot(x, y));
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE){
            if (!this.board.isPaused()){
                this.board.getTimer().stop();
                this.board.setPaused(true);
            }else {
                this.board.setPaused(false);
                this.board.getTimer().start();
            }
        }
    }
}