/*
 * The board of the gamesss
 */
package snake.generator2.pk0;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Handle the board of the games
 * @author Standley Eugene
 */
public class Board extends JPanel implements ActionListener {

    // Private cons.
    private int WIDTH = 850;
    private int HEIGHT = 550;
    private static int DOT_SIZE = 10;
    private static int ALL_DOTS = 900;
    private final int RAND_POS = 30;
    private final int DELAY = 140;

    private int dots;
    private int score;

    private boolean inGame = true;

    private Timer timer;

    private final Image ball;
    private final Image head;

    private final Snake snake = new Snake();
    private final Food food = new Food();

    /*
     * Constructions the Board
     * sets the KeyListener
     * sets the Background
     * gets the dot image
     * gets the apple image
     * gets the head image
     * sets the focus on the Board
     * then initiaze the game
     */
    public Board() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        ImageIcon iid = new ImageIcon(this.getClass().getClassLoader().getResource("\\resources\\images\\dot0.png"));
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(this.getClass().getClassLoader().getResource("\\resources\\images\\food0.png"));
        food.setFoodImage(iia.getImage());

        ImageIcon iih = new ImageIcon(this.getClass().getClassLoader().getResource("\\resources\\images\\head.png"));
        head = iih.getImage();

        setFocusable(true);
        initGame();
    }

    /*
     * Init. the Game
     * Set the Default Snake lenght and Location
     */
    public void initGame() {
        // Set our snake intital size
        snake.setJoints(1);

        // Set the Game Score
        dots = 1;
        score = dots;

        // Create our snake's body
        for (int z = 0; z < snake.getJoints(); z++) {
            snake.setSnakeX(z);
            snake.setSnakeY(z);
        }

        // Set Randomized food location
        LocateFood();

        // Set the timer
        timer = new Timer(DELAY, this);
        timer.start();
    }

    /*
     * Paints the Component and the child components
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Only draw if the game is running / the snake is alive
        if (inGame) {
            this.repaint();
            outPutScore(g);
            g.drawImage(food.GetFoodImage(), food.getFoodX(), food.getFoodY(), this);

            for (int z = 0; z < snake.getJoints(); z++) {
                if (z == 0) {
                    g.drawImage(head, snake.getSnakeX(z), snake.getSnakeY(z), this);
                    score = dots;
                }
                else {
                    g.drawImage(ball, snake.getSnakeX(z), snake.getSnakeY(z), this);
                    score = dots;
                }
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();
            this.repaint();

        } else {
            gameOver(g);
        }
        this.repaint();
    }

    /*
     * Paints the Component screen when the game ends
     */
    public void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2,
                     HEIGHT / 2);
    }

    /*
     * Output the Score on the Screen
     * Bottom right ---
     */
    public void outPutScore(Graphics g){
        String msg ="Score: ";
        msg = msg + String.valueOf(score-1);
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(small);
        g.drawString(msg,(WIDTH - metr.stringWidth(msg)) / 2,
                     HEIGHT / 2);
    }

    /*
     * Check if the current snake location is where
     * the apple is
     */
    public void checkFood() {
        // Check if the head of the snake eat the food
        if ((snake.getSnakeX(0) == food.getFoodX())
                && (snake.getSnakeY(0) == food.getFoodY())) {

            // Increase the lenght of the snake/score
            dots++;
            score = dots;

            // Add another join to the snake
            snake.setJoints(snake.getJoints() + 1);

            // Create new food
            LocateFood();
        }
    }

    /*
     * Checks if the Snake has collied with inside or the board sides
     */
    public void checkCollision() {

          for (int z = snake.getJoints(); z > 0; z--) {

              if ((z > 4) && (snake.getSnakeX(0) == snake.getSnakeX(z))
                      && (snake.getSnakeY(0) == snake.getSnakeY(z))) {
                  // if this happens the game is over
                  inGame = false;
              }
          }

        // If the snake interests with the board edges...
        if (snake.getSnakeY(0) > HEIGHT) {
            inGame = false;
        }

        if (snake.getSnakeY(0) < 0) {
            inGame = false;
        }

        if (snake.getSnakeX(0) > WIDTH) {
            inGame = false;
        }

        if (snake.getSnakeX(0) < 0) {
            inGame = false;
        }

        // If the game has ended, then we can stop our timer
        if (!inGame){
            timer.stop();
        }
    }

    /*
     * Randomally places the apple on the board
     */
    public void LocateFood() {
        // Set random X location for food
        int r = (int) (Math.random() * RAND_POS);
        food.setFoodX(((r * DOT_SIZE)));

        // Set random Y location for food
        r = (int) (Math.random() * RAND_POS);
        food.setFoodY(((r * DOT_SIZE)));
    }

    /**
     * Does the Checks of apple, collisions, and move
     * than repaints the screen
     **/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {

            checkCollision();
            checkFood();
            snake.move();
        }
        super.repaint();
    }

    /*
     * Tranlates the Keystroke into movings
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
                snake.setMoveLeft(true);
                snake.setMoveUp(false);
                snake.setMoveDown(false);
            }

            if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
                snake.setMoveRight(true);
                snake.setMoveUp(false);
                snake.setMoveDown(false);
            }

            if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
                snake.setMoveUp(true);
                snake.setMoveRight(false);
                snake.setMoveLeft(false);
            }

            if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
                snake.setMoveDown(true);
                snake.setMoveRight(false);
                snake.setMoveLeft(false);
            }
        }
    }

    /**
     * Returns the locations of the food
     * @return
     */
    public static int getAllDots(){
        return ALL_DOTS;
    }

    /**
     * Returns the size of the food array
     * @return
     */
    public static int gotDotSize(){
        return DOT_SIZE;
    }
}
