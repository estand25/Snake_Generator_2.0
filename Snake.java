package snake.generator2.pk0;

/**
 * Create the snake and its body
 * @author Standley Eugene
 */
public class Snake {
    
    // Stores the joints / body part location for our snake
    private final int[] x = new int[Board.getAllDots()];
    private final int[] y = new int[Board.getAllDots()];

    //Stores direction of our snake
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean moveUp = false;
    private boolean moveDown = false;

    // Stores # of dots / joints the snake has
    // (starts with 3)
    private int joints = 0;

    /**
     * Returns the Snake X location
     * @param index
     * @return
     */
    public int getSnakeX(int index){
        return x[index];
    }

    /**
     * Returns the Snake Y location
     * @param index
     * @return
     */
    public int getSnakeY(int index){
        return y[index];
    }

    /**
     * Set the Snake X location
     * @param i
     */
    public void setSnakeX(int i){
        x[0] = i;
    }

    /**
     * Set the Snake's Y location
     * @param i
     */
    public void setSnakeY(int i){
        x[0] = i;
    }

    /**
     * Return if Snake is moving Left
     * @return
     */
    public boolean isMovingLeft(){
        return moveLeft;
    }

    /**
     * Set if Snake moved Left
     * @param ml
     */
    public void setMoveLeft(boolean ml){
        this.moveLeft = ml;
    }

    /**
     * Return if Snake is moving Right
     * @return
     */
    public boolean isMovingRight(){
        return moveRight;
    }

    /**
     * Set if Snake moved Right
     * @param mR
     */
    public void setMoveRight(boolean mR){
        this.moveRight = mR;
    }

    /**
     * Return if Snake is moving Up
     * @return
     */
    public boolean isMovingUp(){
        return moveUp;
    }

    /**
     * Set if Snake moved Up
     * @param mU
     */
    public void setMoveUp(boolean mU){
        this.moveUp = mU;
    }

    /**
     * Return if Snake is moving Down
     * @return
     */
    public boolean isMovingDown(){
        return moveDown;
    }

    /**
     * Set if Snake moved Down
     * @param mD
     */
    public void setMoveDown(boolean mD){
        this.moveDown = mD;
    }

    /**
     * Return the number of joints on the snake
     * @return
     */
    public int getJoints(){
        return joints;
    }

    /**
     * Set the number of joints on the snake
     * @param j
     */
    public void setJoints(int j){
        joints = j;
    }

    /*
     * Moves the Snake and snakes body (dots)
     */
    public void move() {
        for (int i = joints; i > 0; i--) {

            // Moves the joints of the snake 'up the chain'
            // Meaning, the joint of the snake all move up one
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];

            System.out.println("X location - "+x[i]);
            System.out.println("Y location - "+y[i]);
        }

        // Moves snake left
        if (moveLeft) {
            x[0] -= Board.gotDotSize();
        }

        // Move snake right
        if (moveRight) {
            x[0] += Board.gotDotSize();
        }

        // Move snake up
        if (moveUp) {
            y[0] -= Board.gotDotSize();
        }

        // Move snake down
        if (moveDown) {
            y[0] += Board.gotDotSize();
        }
    }
    
}
