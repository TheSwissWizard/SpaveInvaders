package entity;

import java.awt.Image;

public class Entity {

    private boolean visible;
    private Image image;
    private boolean isDying;

    protected int x;
    protected int y;
    protected int dx;

    public Entity() {

        this.visible = true;
    }

    public void die() {

        this.visible = false;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return this.x;
    }

    public void setDying(boolean dying) {
        this.isDying = dying;
    }

    public boolean isDying() {
        return this.isDying;
    }
}