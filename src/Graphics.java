import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Graphics extends JPanel implements ActionListener {
    private Timer t = new Timer(100, this); // Timer to trigger repainting and updating the game
    public String state; // Game state ("START", "RUNNING", "END")

    private Snake s; // Snake object
    private Food f;  // Food object
    private Game game; // Game instance

    /**
     * Constructor - Initializes the game graphics, starts the timer, and sets up key listeners.
     * @param g The game instance.
     */
    public Graphics(Game g) {
        t.start();
        state = "START"; // Initial state before the game starts

        game = g;
        s = g.getPlayer();
        f = g.getFood();

        // Add a key listener to detect user input
        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    /**
     * Renders the game elements on the screen based on the game state.
     * @param g The graphics object used for drawing.
     */
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, Game.width * Game.dimension + 5, Game.height * Game.dimension + 5);

        // Display different elements based on the game state
        if (state.equals("START")) {
            g2d.setColor(Color.white);

            // Center calculations
            int centerX = Game.width * Game.dimension / 2;
            int centerY = Game.height * Game.dimension / 2;

            // Title
            g2d.drawString("Press Any Key to Start", centerX - 60, centerY - 50);

            // Instructions
            g2d.drawString("Controls:", centerX - 30, centerY - 20);
            g2d.drawString("W - Move Up", centerX - 40, centerY);
            g2d.drawString("S - Move Down", centerX - 40, centerY + 20);
            g2d.drawString("A - Move Left", centerX - 40, centerY + 40);
            g2d.drawString("D - Move Right", centerX - 40, centerY + 60);

            // Game over Rule
            g2d.drawString("Avoid hitting walls and yourself!", centerX - 90, centerY + 100);
        }

        else if (state.equals("RUNNING")) {
            // Draw food
            g2d.setColor(Color.red);
            g2d.fillRect(f.getX() * Game.dimension, f.getY() * Game.dimension, Game.dimension, Game.dimension);

            // Draw snake
            g2d.setColor(Color.green);
            for (Rectangle r : s.getBody()) {
                g2d.fill(r);
            }
        }
        else { // "END" state
            g2d.setColor(Color.white);
            g2d.drawString("Game Over! Your Score: " + (s.getBody().size() - 3),
                    Game.width / 2 * Game.dimension - 70, Game.height / 2 * Game.dimension - 20);
        }
    }

    /**
     * Triggers repainting and game updates at each timer tick.
     * @param e The action event triggered by the timer.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Refresh the screen
        game.update(); // Update the game logic
    }
}
