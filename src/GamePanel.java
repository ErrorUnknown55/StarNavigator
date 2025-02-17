import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;


public class GamePanel extends JPanel implements Runnable, KeyListener {
    
    //Game screen width and height
    private static final int gameScrWidth = 500;
    private static final int gameScrHeight = 500;

    private Thread gameThread;
    
    private boolean running = false;

    //GameWindow obj
    private GameWindow gameWindow;

    //Player obj
    private Player player;

    //Timer variables
    private int timer = 0;
    private Timer gameTimer;

    //Projectile variables
    private List<Rectangle> projectiles = new ArrayList<>();
    private int projectileSpeed = 10;
    private int weapCDTime = 0; 
    private int setCoolDownTime = 0;

    //Enemy variables
    private List<Rectangle> enemies = new ArrayList<>();
    private int enemySpeed = 5;
    private int spawnRate = 60;

    //Current HighScore
    private int currHighScore = 0;

    //Lives
    private int lives = 3;

    public GamePanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setPreferredSize(new Dimension(gameScrWidth, gameScrHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        //Initialize player
        player = new Player(gameScrWidth / 2, gameScrHeight - 50);

        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer++; //Increment timer every second
                gameWindow.updateGameTime(timer); //Update the game time in the GameWindow
            }
        });
        
    }

    public void startGame() {
        running = true;
        gameWindow.updatePlayerLives(lives);
        gameThread = new Thread(this);
        gameThread.start();
        gameTimer.start();
        requestFocusInWindow();  //Request focues to receive key events
    }

    
    public void stopGame() {
        running = false;
        if(currHighScore <= timer){
            currHighScore = timer;
            gameWindow.updateHighScore(currHighScore);
        }
            
        gameTimer.stop();
    }

    public void restartGame() {
        stopGame();
        
        //Reset the game veriables
        timer = 0;
        lives = 3;
        enemySpeed = 5;
        spawnRate = 60;
        weapCDTime = 0;
        enemies.clear();
        projectiles.clear();

        gameWindow.updateGameTime(timer);
        gameWindow.updatePlayerLives(lives);
        gameWindow.updateWeaponCDTime(0);

        //Restart the game
        startGame();
    }

    @Override
    public void run() {
        while (running) {
            update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    


    private void update() {
        
        //Spawn enemies
        if(Math.random() * spawnRate < 1) {
            int enemyX = (int) (Math.random() * (gameScrWidth - 20));
            enemies.add(new Rectangle(enemyX, 0, 25, 25));
        }

        //Move enemies
        Iterator<Rectangle> enemyIterator = enemies.iterator();
        while(enemyIterator.hasNext()) {
            Rectangle enemy = enemyIterator.next();
            enemy.y += enemySpeed;
            if(enemy.y > gameScrHeight) {
                enemyIterator.remove();
            }
        }

        //Weapon Cooldown timer
        if(weapCDTime > 0) {
            weapCDTime --;
            gameWindow.updateWeaponCDTime(weapCDTime);
        }
        
        //Changes the game play based on the level
        switch (timer) {
            case 0:
                enemySpeed = 4;
                spawnRate = 60;
                setCoolDownTime = 120;
                gameWindow.updateCurrentLevel(1);
                break;
            
            case 10:
                enemySpeed = 6;
                spawnRate = 55;
                gameWindow.updateCurrentLevel(2);
                break;

            case 20:
                enemySpeed = 8;
                spawnRate = 50;
                gameWindow.updateCurrentLevel(3);
                break;

            case 30:
                spawnRate = 45;
                setCoolDownTime = 240;
                gameWindow.updateCurrentLevel(4);
                break;

            case 40:
                enemySpeed = 10;
                spawnRate = 40;
                gameWindow.updateCurrentLevel(5);
                break;
            
            case 50:
                enemySpeed = 12;
                spawnRate = 35;
                setCoolDownTime = 480;
                gameWindow.updateCurrentLevel(6);
                break;

            case 60:
                enemySpeed = 14;
                spawnRate = 30;
                gameWindow.updateCurrentLevel(7);
                break;

            case 70:
                enemySpeed = 16;
                gameWindow.updateCurrentLevel(8);
                break;

            case 80:
                enemySpeed = 18;
                setCoolDownTime = 520;
                gameWindow.updateCurrentLevel(9);
                break;

            case 90:
                enemySpeed = 20;
                gameWindow.updateCurrentLevel(10);
                break;
        
            default:
                break;
        }
        

        //Move projectiles
        Iterator<Rectangle> projectileIterator = projectiles.iterator();
        while(projectileIterator.hasNext()) {
            Rectangle projectile = projectileIterator.next();
            projectile.y -= projectileSpeed;
            if(projectile.y < 0) {
                projectileIterator.remove();
            }
        }

        //Check for collisions
        enemyIterator = enemies.iterator();
        while(enemyIterator.hasNext()) {
            Rectangle enemy = enemyIterator.next();
            projectileIterator = projectiles.iterator();
            while(projectileIterator.hasNext()) {
                Rectangle projectile = projectileIterator.next();
                if (enemy.intersects(projectile)) {
                    enemyIterator.remove();
                    projectileIterator.remove();
                    break;
                }
            }

            if(enemy.intersects(player.getBounds())) {
                enemyIterator.remove();
                lives--;
                gameWindow.updatePlayerLives(lives);//Updates the life count

                if(lives <= 0){
                    stopGame();
                }

            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Draw player (triangle)
        player.draw(g2d);
    
        //Draw projectiles
        g2d.setColor(Color.GREEN);
        for (Rectangle projectile : projectiles) {
            g2d.fill(projectile);
        }

        //Draw enemies
        g2d.setColor(Color.RED);
        for (Rectangle enemy : enemies) {
            g2d.fill(enemy);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT) 
            player.moveLeft();
        
        if(key == KeyEvent.VK_RIGHT) 
            player.moveRigth(gameScrHeight);
        
        if(key == KeyEvent.VK_SPACE && weapCDTime == 0) {
            projectiles.add(new Rectangle(player.getX() + 5, player.getY() - 20, 5, 10));
            weapCDTime = setCoolDownTime;
        } 
            
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}