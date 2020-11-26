import game.SpaceInvaders;

import java.awt.*;

public class Main {

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {

            SpaceInvaders ex = new SpaceInvaders();
            ex.setVisible(true);
        });
    }
}
