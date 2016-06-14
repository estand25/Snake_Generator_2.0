/*
 * The actually Game generation
 */
package snake.generator2.pk0;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * The Game board
 * @author Standley Eugene
 */
public class Game extends JFrame{

    Game() {
        Board b = new Board();
        add(b);
        pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake Generator");

        this.setResizable(true);
        this.setVisible(true);
    }
    /**
     * Main Run method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Game game = new Game();
        //game.repaint();

        //Create a new thread so our GUI can itself
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                JFrame frame = new Game();
                frame.setVisible(true);
            }
        });
    }

}
