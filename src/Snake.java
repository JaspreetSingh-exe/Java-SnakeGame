import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {
    // List to store snake's body parts (each part is a Rectangle)
    private ArrayList<Rectangle> body;

    // Game board dimensions and unit size
    private int w = Game.width;
    private int h = Game.height;
    private int d = Game.dimension;

    // Variable to track movement direction
    private String move;

    /**
     * Constructor - Initializes the snake with three segments at the center of the game board.
     */
    public Snake() {
        body = new ArrayList<>();

        // Creating the head of the snake
        Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
        temp.setLocation(Game.width / 2 * Game.dimension, Game.height / 2 * Game.dimension);
        body.add(temp);

        // Creating the second segment
        temp = new Rectangle(d, d);
        temp.setLocation((w / 2 - 1) * d, (h / 2) * d);
        body.add(temp);

        // Creating the third segment
        temp = new Rectangle(d, d);
        temp.setLocation((w / 2 - 2) * d, (h / 2) * d);
        body.add(temp);

        // Default movement is "NOTHING" (snake doesn't move initially)
        move = "NOTHING";
    }

    /**
     * Moves the snake in the current direction.
     * The movement is performed by adding a new head segment in the movement direction
     * and removing the last segment to maintain the snake's length.
     */
    public void move() {
        if (!move.equals("NOTHING")) { // Ensure the snake moves only if a direction is set
            Rectangle first = body.get(0); // Get the current head position

            Rectangle temp = new Rectangle(Game.dimension, Game.dimension);

            // Determine the new head position based on the movement direction
            if (move.equals("UP")) {
                temp.setLocation(first.x, first.y - Game.dimension);
            } else if (move.equals("DOWN")) {
                temp.setLocation(first.x, first.y + Game.dimension);
            } else if (move.equals("LEFT")) {
                temp.setLocation(first.x - Game.dimension, first.y);
            } else { // "RIGHT"
                temp.setLocation(first.x + Game.dimension, first.y);
            }

            // Add the new head to the front of the snake's body
            body.add(0, temp);
            // Remove the last segment to maintain the same length
            body.remove(body.size() - 1);
        }
    }

    /**
     * Grows the snake by adding a new head segment in the current movement direction.
     */
    public void grow() {
        Rectangle first = body.get(0); // Get the current head position

        Rectangle temp = new Rectangle(Game.dimension, Game.dimension);

        // Determine the new head position based on the movement direction
        if (move.equals("UP")) {
            temp.setLocation(first.x, first.y - Game.dimension);
        } else if (move.equals("DOWN")) {
            temp.setLocation(first.x, first.y + Game.dimension);
        } else if (move.equals("LEFT")) {
            temp.setLocation(first.x - Game.dimension, first.y);
        } else { // "RIGHT"
            temp.setLocation(first.x + Game.dimension, first.y);
        }

        // Add the new head to the front of the snake's body without removing the last segment (growth)
        body.add(0, temp);
    }

    /**
     * Returns the snake's body.
     */
    public ArrayList<Rectangle> getBody() {
        return body;
    }

    /**
     * Sets the snake's body.
     */
    public void setBody(ArrayList<Rectangle> body) {
        this.body = body;
    }

    /**
     * Gets the X-coordinate of the snake's head.
     */
    public int getX() {
        return body.get(0).x;
    }

    /**
     * Gets the Y-coordinate of the snake's head.
     */
    public int getY() {
        return body.get(0).y;
    }

    /**
     * Gets the current movement direction of the snake.
     */
    public String getMove() {
        return move;
    }

    // Methods to change the movement direction of the snake
    public void up() {
        move = "UP";
    }

    public void down() {
        move = "DOWN";
    }

    public void left() {
        move = "LEFT";
    }

    public void right() {
        move = "RIGHT";
    }
}
