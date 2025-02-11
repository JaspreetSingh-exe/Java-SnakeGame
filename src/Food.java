import java.awt.Rectangle;

public class Food {
    private int x; // X-coordinate of the food
    private int y; // Y-coordinate of the food

    /**
     * Constructor - Spawns the food at a random location when the game starts.
     * @param player The snake object to ensure food does not spawn on the snake.
     */

    public Food(Snake player) {
        this.random_spawn(player);
    }

    /**
     * Randomly spawns food on the grid, ensuring it does not appear on the snake's body.
     * @param player The snake object to check food positioning.
     */

    public void random_spawn(Snake player) {
        boolean onSnake = true; // Flag to check if food spawns on the snake

        while (onSnake) {
            onSnake = false;

            // Generate random X and Y coordinates within the game grid
            x = (int) (Math.random() * (Game.width - 1));
            y = (int) (Math.random() * (Game.height - 1));

            // Ensure food does not spawn on the snake's body
            for (Rectangle r : player.getBody()) {
                if (r.x / Game.dimension == x && r.y / Game.dimension == y) {
                    onSnake = true; // Regenerate food position if it overlaps with the snake
                    break;
                }
            }
        }
    }

    // Getters and Setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
