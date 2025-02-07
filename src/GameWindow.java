
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameWindow extends JFrame implements ActionListener, KeyListener, MouseListener {

    //Declare labels 
    private JLabel gameTimeLabel;
    private JLabel playerLifeLabel;

    //Declare text fields
    private JTextField gameTimeText;
    private JTextField playerLifeText;


    //Declare buttons
	private JButton startBtn;
	private JButton pauseBtn;
	private JButton exitBtn;

    private JPanel mainPanel;
    private GamePanel gamePanel;

    private Container c;
    
    public GameWindow() {
        
        //Displays the name of the game
        setTitle("Star Navigator");

        //Set size of the game panel
        setSize(750, 650);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // set properties of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Create main panel
        mainPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
		mainPanel.setLayout(flowLayout);

        GridLayout gridLayout;

        //Create Labels
        gameTimeLabel = new JLabel("Time: ");
        playerLifeLabel = new JLabel("Player Lives: ");

        //Create text fields and set their colour, etc.
        gameTimeText = new JTextField(5);
        playerLifeText = new JTextField(5);

        gameTimeText.setEditable(false);
        playerLifeText.setEditable(false);

        gameTimeText.setBackground(Color.LIGHT_GRAY);
        playerLifeText.setBackground(Color.lightGray);

        //Create infoPanel
		JPanel infoPanel = new JPanel();
        gridLayout = new GridLayout(1, 2);
        infoPanel.setLayout(gridLayout);
        infoPanel.setBackground(Color.white);
        
        //Add user interface objects to infoPanel
        infoPanel.add(gameTimeLabel);
        infoPanel.add(gameTimeText);

        infoPanel.add(playerLifeLabel);
        infoPanel.add(playerLifeText);



        //Create gamePanel
        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(550, 500));
        gamePanel.setBackground(Color.BLACK);
        gamePanel.createGameEntities();


        //create buttons
		startBtn = new JButton ("Play");
        pauseBtn = new JButton ("Pasue");
        exitBtn = new JButton ("Exit");

		// add listener to each button (same as the current object)
		startBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
		exitBtn.addActionListener(this);

        //Create buttonPanel
		JPanel buttonPanel = new JPanel();
		gridLayout = new GridLayout(1, 3);
		buttonPanel.setLayout(gridLayout);
        

        //Add buttons to buttonPanel
		buttonPanel.add (startBtn);
		buttonPanel.add (pauseBtn);
		buttonPanel.add (exitBtn);

        // add sub-panels with GUI objects to mainPanel and set its colour
        mainPanel.add(infoPanel);
        mainPanel.add(gamePanel);
        mainPanel.add(buttonPanel);
        mainPanel.setBackground(new Color(0, 0, 204));
        
        // add mainPanel to window surface
		c = getContentPane();
		c.add(mainPanel);
    }

    //Implement a single method in ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
    //Implement methods in KeyListener interface

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
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