package sprite;

import javax.swing.*;

public class Bomb extends Sprite {

    private boolean isDestroyed;

    public Bomb(int x, int y) {

        init(x, y);
    }

    private void init(int x, int y) {

        this.setDestroyed(true);

        super.x = x;
        super.y = y;

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/bomb.png"));
        setImage(imageIcon.getImage());
    }

    public void setDestroyed(boolean destroyed) {
        this.isDestroyed = destroyed;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}
