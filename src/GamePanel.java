import javax.swing.JPanel;

/**
   A component that displays all the game entities
*/

public class GamePanel extends JPanel {

    Ship ship;

    
    public GamePanel () {
        ship = null;
        
    }

    public void createGameEntities() {

        ship = new Ship(50, 100, 10);
    }
}
