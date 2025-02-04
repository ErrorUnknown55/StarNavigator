
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame implements ActionListener {

    // declare buttons
	private JButton startBtn;
	private JButton pauseBtn;
	private JButton exitBtn;

    private JPanel mainPanel;

    private Container c;
    
    public GameWindow() {
        
        //Displays the name of the game
        setTitle("Star Navigator");

        //Set size of the game panel
        setSize(500, 550);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // set properties of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        //create buttons
		startBtn = new JButton ("Play");
        pauseBtn = new JButton ("Pasue");
        exitBtn = new JButton ("Exit");

		// add listener to each button (same as the current object)
		startBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
		exitBtn.addActionListener(this);


        //Create main panel
        mainPanel = new JPanel();

        GridLayout gridLayout;

        



        // create buttonPanel
		JPanel buttonPanel = new JPanel();
		gridLayout = new GridLayout(1, 4);
		buttonPanel.setLayout(gridLayout);
        

        // add buttons to buttonPanel

		buttonPanel.add (startBtn);
		buttonPanel.add (pauseBtn);
		buttonPanel.add (exitBtn);

        // add sub-panels with GUI objects to mainPanel and set its colour
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
}