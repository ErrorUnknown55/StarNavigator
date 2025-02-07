import javax.swing.JPanel;

/**
   A component that displays all the game entities
*/

public class GamePanel extends JPanel {

    Ship ship;
    Enemy enemy;

    
    public GamePanel () {
        ship = null;
        enemy = null;
        
    }

    public void createGameEntities() {

        ship = new Ship(50, 100, 10);
        enemy =  new Enemy(0, 0, 10);
    }
}
