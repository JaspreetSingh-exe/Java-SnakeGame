import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Game implements KeyListener {
    // Game objects
    private Snake player;
    private Food food;
    private Graphics graphics;

    // Game window
    private JFrame window;

    // Game board dimensions and cell size
    public static final int width = 30;
    public static final int height = 30;
    public static final int dimension = 20;

    /**
     * Constructor - Initializes the game window, snake, food, and graphics.
     */

    public Game() {
        window = new JFrame(); // Create the game window

        player = new Snake(); // Initialize the snake
        food = new Food(player); // Initialize the food object with snake reference
        graphics = new Graphics(this); // Initialize graphics

        window.add(graphics); // Add graphics to the game window

        // Configure window properties
        window.setTitle("Snake");
        window.setSize(width * dimension + 2, height * dimension + dimension + 4);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Starts the game by setting the game state to "RUNNING".
     */

    public void start() {
        graphics.state = "RUNNING";
    }

    /**
     * Updates the game logic (snake movement, collisions, food consumption).
     */

    public void update() {
        if (graphics.state.equals("RUNNING")) { // Ensure the game is in running state
            if (check_food_collision()) {
                player.grow(); // Increase snake length
                food.random_spawn(player); // Generate new food location
            }
            else if (check_wall_collision() || check_self_collision()) {
                graphics.state = "END"; // End the game on collision
            }
            else {
                player.move(); // Move the snake forward
            }
        }
    }

    /**
     * Checks if the snake collides with the walls.
     * @return true if collision occurs, false otherwise.
     */

    private boolean check_wall_collision() {
        return (player.getX() < 0 || player.getX() >= width * dimension
                || player.getY() < 0 || player.getY() >= height * dimension);
    }

    /**
     * Checks if the snake's head collides with the food.
     * @return true if food is eaten, false otherwise.
     */

    private boolean check_food_collision() {
        return (player.getX() == food.getX() * dimension && player.getY() == food.getY() * dimension);
    }

    /**
     * Checks if the snake collides with itself.
     * @return true if the snake bites itself, false otherwise.
     */

    private boolean check_self_collision() {
        for (int i = 1; i < player.getBody().size(); i++) {
            if (player.getX() == player.getBody().get(i).x &&
                    player.getY() == player.getBody().get(i).y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    /**
     * Handles keyboard input for controlling the snake's direction.
     */

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); // Get the pressed key

        if (graphics.state.equals("RUNNING")) { // Ensure input is registered only when game is running
            if (keyCode == KeyEvent.VK_W && !player.getMove().equals("DOWN")) {
                player.up();
            }
            if (keyCode == KeyEvent.VK_S && !player.getMove().equals("UP")) {
                player.down();
            }
            if (keyCode == KeyEvent.VK_A && !player.getMove().equals("RIGHT")) {
                player.left();
            }
            if (keyCode == KeyEvent.VK_D && !player.getMove().equals("LEFT")) {
                player.right();
            }
        }
        else {
            this.start(); // Restart the game if it's not running
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    // Getters and Setters

    public Snake getPlayer() {
        return player;
    }

    public void setPlayer(Snake player) {
        this.player = player;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public JFrame getWindow() {
        return window;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }
}
