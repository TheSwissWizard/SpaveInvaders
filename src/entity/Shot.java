package entity;

import javax.swing.ImageIcon;

public class Shot extends Entity {

    public Shot() {
    }

    public Shot(int x, int y) {

        init(x, y);
    }

    private void init(int x, int y) {

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/shot.png"));
        setImage(imageIcon.getImage());

        super.setX(x + 6);

        super.setY(y - 1);
    }
}
