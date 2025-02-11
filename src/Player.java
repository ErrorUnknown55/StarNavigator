
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {
    private int pX, pY; //Player position 
    private int pWidth = 20, pHeight = 20; //Size of player
    private int speed = 10; //Speed of the player
    private Color color = Color.WHITE;

    public Player (int x, int y) {
        this.pX = x;
        this.pY = y;
    }

    //Move the player Left
    public void moveLeft () {
        pX -= speed;
        
        if(pX < 0)
            pX = 0; //Prevents the player form moving out of the screen
    }

    //Move the player Right
    public void moveRigth (int scrWidth) {
        pX += speed;
        
        if (pX > 530)
            pX = 530; //Prevents the player form moving out of the screen    
    }
    
    //Get the player shape(for collision detection)
    public Rectangle getBounds() {
        return new Rectangle(pX, pY, pWidth, pHeight);
    }

     // Draw the player
     public void draw(Graphics2D g2d) {
        int[] xPoints = {pX, pX + pWidth, pX + pWidth / 2};
        int[] yPoints = {pY + pHeight, pY + pHeight, pY};
        g2d.setColor(color);
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    // Getter for player's X position (used for projectile spawning)
    public int getX() {
        return pX;
    }

    // Getter for player's Y position (used for projectile spawning)
    public int getY() {
        return pY;
    }
}
