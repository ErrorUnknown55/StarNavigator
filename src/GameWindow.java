import java.awt.*;			// need this for GUI objects
import java.awt.event.*;			// need this for Layout Managers
import javax.swing.*;

public class GameWindow extends JFrame implements ActionListener, /*KeyListener,*/ MouseListener {

    //Declare labels 
    private JLabel gameTimeLabel, playerLifeLabel, currScoreLabel, highScoreLabel;

    //Declare text fields
    private JTextField gameTimeText, playerLifeText, currScoreText, highScoreText;

    //Declare buttons
	private JButton startBtn, pauseBtn, exitBtn;

    private JPanel mainPanel;
    private GamePanel gamePanel;

    final private Container c;
    
    public GameWindow() {
        
        //Displays the name of the game
        setTitle("Star Navigator");

        //Set size of the game panel
        setSize(750, 650);

        //Create main panel
        mainPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
		mainPanel.setLayout(flowLayout);

        GridLayout gridLayout;



        //Create Labels
        gameTimeLabel = new JLabel("Time: ");
        playerLifeLabel = new JLabel("Player Lives: ");
        currScoreLabel = new JLabel("Current score: ");
        highScoreLabel = new JLabel("High Score: ");

        //Create text fields and set their colour, etc.
        gameTimeText = new JTextField(5);
        playerLifeText = new JTextField(5);
        currScoreText = new JTextField(5);
        highScoreText = new JTextField(5);

        gameTimeText.setEditable(false);
        playerLifeText.setEditable(false);
        currScoreText.setEditable(false);
        highScoreText.setEditable(false);

        gameTimeText.setBackground(Color.LIGHT_GRAY);
        playerLifeText.setBackground(Color.lightGray);
        currScoreText.setBackground(Color.lightGray);
        highScoreText.setBackground(Color.lightGray);


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

        infoPanel.add(highScoreLabel);
        infoPanel.add(highScoreText);

        infoPanel.add(currScoreLabel);
        infoPanel.add(currScoreText);


        //Create gamePanel
        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(550, 500));
    
    
        //Create buttons
		startBtn = new JButton("Play");
        pauseBtn = new JButton("Pasue");
        exitBtn = new JButton("Exit");

		// add listener to each button (same as the current object)
		startBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
		exitBtn.addActionListener(this);

        //Create buttonPanel
		JPanel buttonPanel = new JPanel();
		gridLayout = new GridLayout(1, 3);
		buttonPanel.setLayout(gridLayout);
        

        //Add buttons to buttonPanel
		buttonPanel.add(startBtn);
		buttonPanel.add(pauseBtn);
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
        if (e.getSource() == startBtn) {
            gamePanel.startGame(); // Start the game
        } 

        //Exit
        if (e.getSource() == exitBtn) {
            System.exit(0);
        }
    }
    
    

    //Implement methods in MouseListener interface
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}