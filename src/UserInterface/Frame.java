package UserInterface;
import javax.swing.JFrame;

/*
 * Frame class
 * 
 * Class responsible for launching and handling
 * the main game window.
 */

public class Frame extends JFrame {
	
	/*
	 * Class variable
	 * 
	 * serialVersionUID - Default variable required by compiler.
	 */
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Frame constructor
	 * 
	 * Constructor to create frame. Initializing frame.
	 * Define on close operation, sets title and resize ability.
	 */
	
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake Game");
		setResizable(false);
		GameWindowInit();
	}
	
	/*
	 * GameWindowInit method
	 * 
	 * Initializing game window.
	 */

	public void GameWindowInit() {
		GameWindow gameWindow = new GameWindow();
		add(gameWindow);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}