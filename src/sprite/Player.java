package sprite;


import util.Commons;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int width;
    private final int moveSpeed = 2;

    public Player() {
        init();
    }

    private void init() {

        ImageIcon playerIcon = new ImageIcon(getClass().getResource("/images/player.png"));

        this.width = playerIcon.getImage().getWidth(null);
        super.setImage(playerIcon.getImage());

        super.setX(270);
        super.setY(280);
    }

    /**
     * Sets {@link Sprite#x} (x position) according to {@link Sprite#dx} (delta x)
     */
    public void move() {

        super.x += super.dx;

        if (super.x <= this.moveSpeed) {

            super.x = this.moveSpeed;
        }

        if (super.x >= Commons.BOARD_WIDTH -this.moveSpeed * this.width) {

            super.x = Commons.BOARD_WIDTH - this.moveSpeed * this.width;
        }
    }

    /**
     * Sets {@link Sprite#dx} (delta x) depending on what key got pressed
     * @param e KeyEvent - the key that got pressed
     */
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {

            super.dx = this.moveSpeed * (-1);
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {

            super.dx = this.moveSpeed;
        }
    }

    /**
     * Sets {@link Sprite#dx} (delta x) to 0 after key got released again
     * @param e {@link KeyEvent} - key got released
     */
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {

            super.dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {

            super.dx = 0;
        }
    }
}
