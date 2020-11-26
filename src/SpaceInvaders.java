import game.Board;
import util.Commons;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class SpaceInvaders extends JFrame  {

    public SpaceInvaders() {

        init();
    }

    private void init() {

        this.add(new Board());

        this.setTitle("Space Invaders");
        this.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            SpaceInvaders ex = new SpaceInvaders();
            ex.setVisible(true);
        });
    }
}
