import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
   A component that displays all the game entities
*/

public class GamePanel extends JPanel {

    private JPanel panel;

    Ship ship;
    Enemy enemy;

    
    public GamePanel () {
        ship = null;
        enemy = null;
    }

    public void createGameEntities() {

        ship = new Ship(10, 0, 10);
        enemy =  new Enemy(0, 0, 10);
    }

    public void drawGameEntities() {
        Graphics g = panel.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        
        if(ship != null)
            ship.draw(g2);
    }
}
