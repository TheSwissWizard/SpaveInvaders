package sprite;

import javax.swing.*;

public class Bomb extends Sprite {

    private boolean destroyed;

    public Bomb(int x, int y) {

        init(x, y);
    }

    private void init(int x, int y) {

        this.setDestroyed(true);

        super.x = x;
        super.y = y;

        ImageIcon imageIcon = new ImageIcon("src/images/bomb.png");
        setImage(imageIcon.getImage());
    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }
}
