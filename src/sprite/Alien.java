package sprite;

import javax.swing.ImageIcon;

public class Alien extends Sprite {

    private Bomb bomb;

    public Alien(int x, int y) {

        init(x, y);
    }

    private void init(int x, int y) {

        super.x = x;
        super.y = y;

        this.bomb = new Bomb(x, y);

        ImageIcon imageIcon = new ImageIcon("src/images/alien.png");

        super.setImage(imageIcon.getImage());
    }

    public void move(int direction) {
        this.x += direction;
    }

    public Bomb getBomb() {
        return this.bomb;
    }
}
