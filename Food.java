package snake.generator2.pk0;

import java.awt.Image;

/**
 * Generates Food
 * @author Standley Eugene
 */
public final class Food {
    private int foodx; // Stores X pos of our food
    private int foody; // Stores Y pos of our food

    // Image of the food
    private Image food;

    /**
     * Get the food X location
     * @return
     */
    public int getFoodX() {
        return foodx;
    }

    /**
     * Get the food Y location
     * @return
     */
    public int getFoodY() {
        return foody;
    }

    /**
     * Get the food image
     * @return
     */
    public Image GetFoodImage() {
        return food;
    }

    /**
     * Set the X location of the Food
     * @param x
     */
    public void setFoodX(int x){
        foodx = x;
    }

    /**
     * Set the Y location of the food
     * @param y
     */
    public void setFoodY(int y){
        foody = y;
    }

    /**
     * Set the Image for the food
     * @param foodImage
     */
    public void setFoodImage(Image foodImage){
        food = foodImage;
    }
}

