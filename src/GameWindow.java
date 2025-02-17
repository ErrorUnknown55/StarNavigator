import java.awt.*;			// need this for GUI objects
import java.awt.event.*;			// need this for Layout Managers
import javax.swing.*;

public class GameWindow extends JFrame implements ActionListener {

    //Declare labels 
    private JLabel gameTimeLabel, playerLifeLabel, currLevelLabel, weaponCoolDwnLabel, highScoreLabel;

    //Declare text fields
    private JTextField gameTimeText, playerLifeText, currLevelText, weaponCoolDwnText, highScoreText;

    //Declare buttons
	private JButton startBtn, restartBtn, exitBtn;

    private JPanel mainPanel;
    private GamePanel gamePanel;

    final private Container c;
    
    public GameWindow() {
        
        //Displays the name of the game
        setTitle("Star Navigator");

        //Set size of the game panel
        setSize(700, 700);

        //Create main panel
        mainPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
		mainPanel.setLayout(flowLayout);

        GridLayout gridLayout;

        //Create a custom font
        Font customFont = new Font("Exo", Font.BOLD, 16);

        //Create Labels
        gameTimeLabel = new JLabel("Time:");
        highScoreLabel = new JLabel("High Score:");
        playerLifeLabel = new JLabel("Lives:");
        currLevelLabel = new JLabel("Level:");
        weaponCoolDwnLabel = new JLabel("Weapon cooldown:");

        //Label FontStyle
        gameTimeLabel.setFont(customFont);
        highScoreLabel.setFont(customFont);
        playerLifeLabel.setFont(customFont);
        currLevelLabel.setFont(customFont);
        weaponCoolDwnLabel.setFont(customFont);

        //Create text fields and set their colour, etc.
        gameTimeText = new JTextField(5);
        highScoreText = new JTextField(5);
        playerLifeText = new JTextField(5);
        currLevelText = new JTextField(5);
        weaponCoolDwnText = new JTextField(5);


        gameTimeText.setEditable(false);
        highScoreText.setEditable(false);
        playerLifeText.setEditable(false);
        currLevelText.setEditable(false);
        weaponCoolDwnText.setEditable(false);

        //Text FontStyle
        gameTimeText.setFont(customFont);
        highScoreText.setFont(customFont);
        playerLifeText.setFont(customFont);
        currLevelText.setFont(customFont);
        weaponCoolDwnText.setFont(customFont);

        gameTimeText.setText("0");
        highScoreText.setText("0");
        playerLifeText.setText("0");
        currLevelText.setText("0");
        weaponCoolDwnText.setText("0");



        //Create infoPanel
		JPanel infoPanel = new JPanel();
        gridLayout = new GridLayout(3, 2);
        infoPanel.setLayout(gridLayout);
        
        //Add user interface objects to infoPanel
        infoPanel.add(gameTimeLabel);
        infoPanel.add(gameTimeText);

        infoPanel.add(highScoreLabel);
        infoPanel.add(highScoreText);

        infoPanel.add(playerLifeLabel);
        infoPanel.add(playerLifeText);

        infoPanel.add(currLevelLabel);
        infoPanel.add(currLevelText);

        infoPanel.add(weaponCoolDwnLabel);
        infoPanel.add(weaponCoolDwnText);


        //Create gamePanel
        gamePanel = new GamePanel(this);
        
        //Create buttons
		startBtn = new JButton("Play");
        restartBtn = new JButton("Restart");
        exitBtn = new JButton("Exit");

        //Button FontStyle
        startBtn.setFont(customFont);
        restartBtn.setFont(customFont);
        exitBtn.setFont(customFont);
        

		//Add listener to each button (same as the current object)
		startBtn.addActionListener(this);
		restartBtn.addActionListener(this);
		exitBtn.addActionListener(this);

        //Create buttonPanel
		JPanel buttonPanel = new JPanel();
		gridLayout = new GridLayout(1, 3);
		buttonPanel.setLayout(gridLayout);
        

        //Add buttons to buttonPanel
		buttonPanel.add(startBtn);
		buttonPanel.add(restartBtn);
		buttonPanel.add(exitBtn);

        //Add sub-panels with GUI objects to mainPanel and set its colour
        mainPanel.add(infoPanel);
        mainPanel.add(gamePanel);
        mainPanel.add(buttonPanel);
        mainPanel.setBackground(Color.WHITE);
        
        //Add mainPanel to window surface
		c = getContentPane();
		c.add(mainPanel);


        //Center the frame on the screen
        setLocationRelativeTo(null);

        //Makes the window non-resizable
        setResizable(false);

        //Set properties of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 
    }

    //Implement a single method in ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        //Start
        if(e.getSource() == startBtn) 
            gamePanel.startGame(); //Start the game
        
        //Restart
        if(e.getSource() == restartBtn)
            gamePanel.restartGame();

        //Exit
        if(e.getSource() == exitBtn) 
            System.exit(0);
        
    }


    public void updateCurrentLevel(int level) {
        currLevelText.setText(Integer.toString(level));
    }

    public void updateHighScore(int highScore) {
        highScoreText.setText(Integer.toString(highScore));
    }

    public void updateGameTime(int time) {
        gameTimeText.setText(Integer.toString(time));
    }

    public void updatePlayerLives(int lives) {
        
        if (lives <= 1 )
            playerLifeText.setForeground(Color.RED);
        else
            playerLifeText.setForeground(Color.black);
        
        playerLifeText.setText(Integer.toString(lives));
    } 

    public void updateWeaponCDTime(int cDTime) {
        weaponCoolDwnText.setText(Integer.toString(cDTime));
    } 

}