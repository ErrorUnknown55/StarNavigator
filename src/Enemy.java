import java.awt.*;

public class Projectile {
    private int x;
    private int y;
    private int speed;

    public Projectile(int x, int y, int speed) {
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

    public void move() {
        y += speed;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(x, y, 20, 20);
    }
}