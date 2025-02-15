import java.awt.*;			// need this for GUI objects
import java.awt.event.*;			// need this for Layout Managers
import javax.swing.*;

public class GameWindow extends JFrame implements ActionListener {

    //Declare labels 
    private JLabel gameTimeLabel, playerLifeLabel, currScoreLabel;

    //Declare text fields
    private JTextField gameTimeText, playerLifeText, currScoreText;

    //Declare buttons
	private JButton startBtn, restartBtn, exitBtn;

    private JPanel mainPanel;
    private GamePanel gamePanel;

    final private Container c;
    
    public GameWindow() {
        
        //Displays the name of the game
        setTitle("Star Navigator");

        //Set size of the game panel
        setSize(700, 650);

        //Create main panel
        mainPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
		mainPanel.setLayout(flowLayout);

        GridLayout gridLayout;

        //Create a custom font
        Font customFont = new Font("Exo", Font.BOLD, 16);

        //Create Labels
        gameTimeLabel = new JLabel("Time: ");
        playerLifeLabel = new JLabel("Lives: ");
        currScoreLabel = new JLabel("Score:");

        //Label FontStyle
        gameTimeLabel.setFont(customFont);
        playerLifeLabel.setFont(customFont);
        currScoreLabel.setFont(customFont);

        //Create text fields and set their colour, etc.
        gameTimeText = new JTextField(5);
        playerLifeText = new JTextField(5);
        currScoreText = new JTextField(5);

        gameTimeText.setEditable(false);
        playerLifeText.setEditable(false);
        currScoreText.setEditable(false);

        //Text FontStyle
        gameTimeText.setFont(customFont);
        playerLifeText.setFont(customFont);
        currScoreText.setFont(customFont);

        gameTimeText.setText("0");
        playerLifeText.setText("0");
        currScoreText.setText("0");



        //Create infoPanel
		JPanel infoPanel = new JPanel();
        gridLayout = new GridLayout(1, 4);
        infoPanel.setLayout(gridLayout);
        infoPanel.setBackground(Color.white);//Set the background color white
        
        //Add user interface objects to infoPanel
        infoPanel.add(gameTimeLabel);
        infoPanel.add(gameTimeText);

        infoPanel.add(playerLifeLabel);
        infoPanel.add(playerLifeText);

        infoPanel.add(currScoreLabel);
        infoPanel.add(currScoreText);

        //Create gamePanel
        gamePanel = new GamePanel(this);
        gamePanel.setPreferredSize(new Dimension(550, 500));
    
    
        //Create buttons
		startBtn = new JButton("Play");
        restartBtn = new JButton("Restart");
        exitBtn = new JButton("Exit");

        //Button FontStyle
        startBtn.setFont(customFont);
        restartBtn.setFont(customFont);
        exitBtn.setFont(customFont);
        

		// add listener to each button (same as the current object)
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

        // add sub-panels with GUI objects to mainPanel and set its colour
        mainPanel.add(infoPanel);
        mainPanel.add(gamePanel);
        mainPanel.add(buttonPanel);
        mainPanel.setBackground(Color.WHITE);
        
        // add mainPanel to window surface
		c = getContentPane();
		c.add(mainPanel);


        // Center the frame on the screen
        setLocationRelativeTo(null);

        // set properties of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 
    }

    //Implement a single method in ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        //Start
        if(e.getSource() == startBtn) 
            gamePanel.startGame(); // Start the game
        

        if(e.getSource() == restartBtn)
            gamePanel.restartGame();

        //Exit
        if(e.getSource() == exitBtn) 
            System.exit(0);
        
    }


    public void updateCurrentScore(int score) {
        currScoreText.setText(Integer.toString(score));
    }

    public void updateGameTime(int time) {
        gameTimeText.setText(Integer.toString(time));
    }

    public void updatePlayerLives(int lives) {
        playerLifeText.setText(Integer.toString(lives));
    } 

}