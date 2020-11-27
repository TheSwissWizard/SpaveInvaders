package game;

import entity.Alien;
import entity.Bomb;
import entity.Player;
import entity.Shot;
import util.Commons;
import util.GameCycle;
import util.KeyPressAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel {

    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    private Shot shot;
    
    private int xDirection = -1;
    private int deaths = 0;

    private boolean inGame = true;
    private final String explosionImage = "/images/explosion.png";
    private String message = Commons.MESSAGE_OVER;

    private Timer timer;

    private boolean isPaused = false;

    private JButton restartButton;

    public Board() {

        init();
        gameInit();
    }

    private void init() {

        this.addKeyListener(new KeyPressAdapter(this));
        this.setFocusable(true);
        this.d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        this.setBackground(Color.black);

        this.timer = new Timer(Commons.DELAY, new GameCycle(this));
        this.timer.start();
    }


    private void gameInit() {

        this.aliens = new ArrayList<>();

        for (int i = 0; i < Commons.ALIEN_ROWS; i++) {
            for (int j = 0; j < Commons.ALIEN_COLUMNS; j++) {

                var alien = new Alien(Commons.ALIEN_INIT_X + (Commons.ALIEN_WIDTH + 6) * j,
                        Commons.ALIEN_INIT_Y + (Commons.ALIEN_WIDTH + 6) * i);
                this.aliens.add(alien);
            }
        }

        this.player = new Player();
        this.shot = new Shot();
    }

    private void drawAliens(Graphics g) {

        for (Alien alien : this.aliens) {

            if (alien.isVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {

                alien.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {

        if (this.player.isVisible()) {

            g.drawImage(this.player.getImage(), this.player.getX(), this.player.getY(), this);
        }

        if (this.player.isDying()) {

            this.player.die();
            this.inGame = false;
        }
    }

    private void drawShot(Graphics g) {

        if (this.shot.isVisible()) {

            g.drawImage(this.shot.getImage(), this.shot.getX(), this.shot.getY(), this);
        }
    }

    private void drawBombing(Graphics g) {

        for (Alien a : this.aliens) {

            Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.GREEN);

        if (this.inGame) {

            g.drawLine(0, Commons.BOARD_GROUND,
                    Commons.BOARD_WIDTH, Commons.BOARD_GROUND);

            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);

        } else {

            if (this.timer.isRunning()) {
                this.timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Commons.BOARD_HEIGHT / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_HEIGHT / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(this.message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(this.message)) / 2,Commons.BOARD_HEIGHT / 2);

    }

    private void update() {

        if (this.deaths == Commons.ALIENS_TO_DESTROY) {

            this.inGame = false;
            this.timer.stop();
            this.message = Commons.MESSAGE_WON;
        }

        // player
        this.player.move();

        // shot
        if (this.shot.isVisible()) {

            int shotX = this.shot.getX();
            int shotY = this.shot.getY();

            for (Alien alien : this.aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && this.shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {

                        ImageIcon imageIcon = new ImageIcon(getClass().getResource(explosionImage));
                        alien.setImage(imageIcon.getImage());
                        alien.setDying(true);
                        this.deaths++;
                        this.shot.die();
                    }
                }
            }

            if (this.shot.getY() - Commons.SHOT_SPEED <= 0) {
                this.shot.die();
            } else {
                this.shot.setY(this.shot.getY() - Commons.SHOT_SPEED);
            }
        }

        // aliens
        for (Alien alien : this.aliens) {

            if (alien.getX() >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && this.xDirection == 1) {

                this.xDirection = -1;

                for (Alien alien2 : this.aliens) {

                    alien2.setY(alien2.getY() + Commons.ALIEN_DOWN_SPEED);
                }
            }

            if (alien.getX() <= Commons.BORDER_LEFT && xDirection == -1) {

                this.xDirection = 1;

                for (Alien a : this.aliens) {

                    a.setY(a.getY() + Commons.ALIEN_DOWN_SPEED);
                }
            }
        }

        for (Alien alien : this.aliens) {

            if (alien.isVisible()) {

                if (alien.getY() > Commons.BOARD_GROUND - Commons.ALIEN_HEIGHT) {
                    this.inGame = false;
                    this.message = Commons.MESSAGE_INVASION;
                }

                alien.move(this.xDirection);
            }
        }

        // bombs
        Random generator = new Random();

        for (Alien alien : this.aliens) {

            int shotChance = generator.nextInt(Commons.CHANCE_GENERATOR);
            Bomb bomb = alien.getBomb();

            if (shotChance == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (this.player.isVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)) {

                    ImageIcon imageIcon = new ImageIcon(this.explosionImage);
                    this.player.setImage(imageIcon.getImage());
                    this.player.setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() + Commons.BOMB_SPEED);

                if (bomb.getY() >= Commons.BOARD_GROUND - Commons.BOMB_HEIGHT) {

                    bomb.setDestroyed(true);
                }
            }
        }
    }

    public void doGameCycle() {

        update();
        repaint();
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public boolean isInGame() {
        return inGame;
    }

    public Player getPlayer() {
        return player;
    }

    public Timer getTimer() {
        return timer;
    }
}
