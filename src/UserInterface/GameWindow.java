package UserInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import SnakeLogic.Snake;
import ComponentsHandlers.AppleHandler;
import ComponentsHandlers.FrogHandler;
import ComponentsHandlers.WallsHandler;

public class GameWindow extends JPanel implements Runnable {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	private Thread thread;
	private int gameFrames = 0;
	private int bestResult = 1;
	private boolean runGame = false;
	private Snake snakePlayer;
	private Key pressedKey;
	private AppleHandler appleHandler;
	private FrogHandler frogHandler;
	private WallsHandler wallsHandler;
	
	public GameWindow() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		pressedKey = new Key();
		addKeyListener(pressedKey);
		this.StartGame();
	}
	
	public void StartGame() {
		this.snakePlayer = new Snake(10, 10);
		this.wallsHandler = new WallsHandler(10);
		this.wallsHandler.GenerateRandomWalls();
		this.frogHandler = new FrogHandler();
		this.appleHandler = new AppleHandler();
		
		this.frogHandler.GenerateFrog(wallsHandler.coordsX, wallsHandler.coordsY);
		this.appleHandler.GenerateApple(wallsHandler.coordsX, wallsHandler.coordsY);
		this.runGame = true;
		thread = new Thread(this, "MainLoop");
		thread.start();
	}
	
	public void StopGame() {
		runGame = false;
		
		this.appleHandler.StopAppleHandlerThread();
		this.frogHandler.StartFrogHandlerThread();
	}
	
	public void run() {
		while(runGame) {
			GameFrame();
			repaint();
		}
	}
	
	public void paint(Graphics component) {
		if(runGame) {
			DrawBackground(component);
			this.snakePlayer.DrawSnakeBody(component);
			this.appleHandler.DrawApple(component);
			this.wallsHandler.DrawWallsComponents(component);
			this.frogHandler.DrawFrog(component);
		} else {
			ShowBestResult(component);
		}
	}
	
	private void DrawBackground(Graphics component) { 
		component.clearRect(0, 0, WIDTH, HEIGHT);
		component.fillRect(0, 0, WIDTH, HEIGHT);
	}
	
	private void GameFrame() {
		this.gameFrames++;
		
		if(gameFrames > 300000) {
			this.snakePlayer.MoveSnake();
			this.frogHandler.FrogArtificialIntelligence(this.snakePlayer, this.wallsHandler);
			this.gameFrames = 0;
		}
		
		this.appleHandler.GenerateApple(this.wallsHandler.coordsX, this.wallsHandler.coordsY);
		this.appleHandler.GameFrame(this.snakePlayer);
		
		this.frogHandler.GenerateFrog(this.wallsHandler.coordsX, this.wallsHandler.coordsY);
		this.frogHandler.GameFrame(this.snakePlayer);
		
		if(snakePlayer.CheckCollision() || this.wallsHandler.CheckCollision(snakePlayer)) {
			StopGame();
		}
	}
	
	private void ShowBestResult(Graphics component) {
		String message = "Game Over";
		String recordMessage = "New Record!";
		String bestResultMessage = "Best result: " + this.bestResult;
		String restartMessage = "Press Space to restart";
		String playerResult = "Your result: " + this.snakePlayer.snakeBody.size();
		
		Font font = new Font("SAN_SERIF", Font.BOLD, 30);
		Font secondFont = new Font("SAN_SERIF", Font.BOLD, 20);
		FontMetrics metrices = getFontMetrics(font);
		FontMetrics secondMetrices = getFontMetrics(secondFont);
		
		component.setColor(Color.WHITE);
		component.setFont(font);
		
		if(this.snakePlayer.snakeBody.size() > this.bestResult) {
			component.drawString(recordMessage, 200 - metrices.stringWidth(recordMessage) / 2, 50);
			this.bestResult = this.snakePlayer.snakeBody.size();
		} else {
			component.drawString(message, 200 - metrices.stringWidth(message) / 2, 50);
		}
		
		component.setFont(secondFont);
		component.drawString(restartMessage, 200 - secondMetrices.stringWidth(restartMessage) / 2, 70);
		component.setFont(secondFont);
		component.drawString(bestResultMessage, 200 - secondMetrices.stringWidth(playerResult) / 2, 90);
		component.drawString(playerResult, 200 - secondMetrices.stringWidth(playerResult) / 2, 110);
	}
	
	private class Key implements KeyListener {
		public void keyTyped(KeyEvent event) {}
		public void keyReleased(KeyEvent event) {}
		
		public void keyPressed(KeyEvent event) {
			String key = KeyEvent.getKeyText(event.getKeyCode());
			snakePlayer.TurnSnake(key);
			
			if(!runGame && key == "Space") {
				StartGame();
			}
		}
		
	}
}