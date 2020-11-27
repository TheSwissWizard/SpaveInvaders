package game;

import util.Commons;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class SpaceInvaders extends JFrame  implements ActionListener {

    private static JButton startButton = new JButton("Start");

    public SpaceInvaders(boolean setUp){
        if (!setUp){
            init();
        }
    }

    private void init() {

        this.add(new Board());

        this.setTitle("Space Invaders");
        this.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public static void setUp(){
        SpaceInvaders spaceInvaders = new SpaceInvaders(true);
        spaceInvaders.setTitle("Space Invaders");
        spaceInvaders.setSize(Commons.BOARD_WIDTH/2, Commons.BOARD_HEIGHT/2);
        spaceInvaders.setResizable(false);
        spaceInvaders.setLocationRelativeTo(null);
        spaceInvaders.add(startButton);
        startButton.addActionListener(spaceInvaders);

        spaceInvaders.setVisible(true);

    }

    private static void run(){
        EventQueue.invokeLater(() -> {

            SpaceInvaders ex = new SpaceInvaders(false);
            ex.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            this.dispose();
            run();
        }
    }
}
