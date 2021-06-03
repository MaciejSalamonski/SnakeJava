package UserInterface;
import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake Game");
		setResizable(false);
		GameWindowInit();
	}

	public void GameWindowInit() {
		GameWindow gameWindow = new GameWindow();
		add(gameWindow);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}