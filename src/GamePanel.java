
/**
   A component that displays all the game entities
*/
/*import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

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
}*/

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    // Game screen width and height
    private static final int gameScrWidth = 550;
    private static final int gameScrHeight = 500;

    private Thread gameThread;
    private boolean running = false;

    // Player variables
    private int playerX = gameScrWidth / 2;
    private int playerY = gameScrHeight - 50;
    private int playerSpeed = 10;

    // Projectile variables
    private List<Rectangle> projectiles = new ArrayList<>();
    private int projectileSpeed = 7;

    // Enemy variables
    private List<Rectangle> enemies = new ArrayList<>();
    private int enemySpeed = 2;
    private int spawnRate = 60;

    // Score
    private int score = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(gameScrWidth, gameScrHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
    }

    public void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGame() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            update();
            repaint();
            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        // Spawn enemies
        if (Math.random() * spawnRate < 1) {
            int enemyX = (int) (Math.random() * gameScrWidth);
            enemies.add(new Rectangle(enemyX, 0, 20, 20));
        }

        // Move enemies
        Iterator<Rectangle> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Rectangle enemy = enemyIterator.next();
            enemy.y += enemySpeed;
            if (enemy.y > gameScrHeight) {
                enemyIterator.remove();
            }
        }

        // Move projectiles
        Iterator<Rectangle> projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Rectangle projectile = projectileIterator.next();
            projectile.y -= projectileSpeed;
            if (projectile.y < 0) {
                projectileIterator.remove();
            }
        }

        // Check for collisions
        enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Rectangle enemy = enemyIterator.next();
            projectileIterator = projectiles.iterator();
            while (projectileIterator.hasNext()) {
                Rectangle projectile = projectileIterator.next();
                if (enemy.intersects(projectile)) {
                    enemyIterator.remove();
                    projectileIterator.remove();
                    score += 10;
                    break;
                }
            }
            if (enemy.intersects(playerX, playerY, 20, 20)) {
                running = false; // Game over
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw player (triangle)
        int[] xPoints = {playerX, playerX + 20, playerX + 10};
        int[] yPoints = {playerY, playerY, playerY - 20};
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Draw projectiles
        g2d.setColor(Color.RED);
        for (Rectangle projectile : projectiles) {
            g2d.fill(projectile);
        }

        // Draw enemies
        g2d.setColor(Color.GREEN);
        for (Rectangle enemy : enemies) {
            g2d.fill(enemy);
        }

        // Draw score
        g2d.setColor(Color.WHITE);
        g2d.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && playerX > 0) {
            playerX -= playerSpeed;
        }
        if (key == KeyEvent.VK_RIGHT && playerX < gameScrWidth - 20) {
            playerX += playerSpeed;
        }
        if (key == KeyEvent.VK_SPACE) {
            projectiles.add(new Rectangle(playerX + 5, playerY - 20, 5, 10));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}