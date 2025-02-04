import java.awt.*;

public class Ship {
    
    private int x;
    private int y;
    private int speed;

    public Ship(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void draw(Graphics2D g2d) {
        int[] xPoints = {x, x + 20, x + 10};
        int[] yPoints = {y, y, y - 20};
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(xPoints, yPoints, 3);
    }
}