package sprite;

import javax.swing.ImageIcon;

public class Shot extends Sprite {

    public Shot() {
    }

    public Shot(int x, int y) {

        init(x, y);
    }

    private void init(int x, int y) {

        ImageIcon imageIcon = new ImageIcon("src/images/shot.png");
        setImage(imageIcon.getImage());

        super.setX(x + 6);

        super.setY(y - 1);
    }
}
