package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;
import SnakeLogic.Snake;
import ComponentsHandlers.AppleHandler;
import ComponentsHandlers.FrogHandler;
import ComponentsHandlers.WallsHandler;
import FileHandler.FileHandler;

/*
 * GameWindows class
 * 
 * Class responsible for creating the main program window. The class
 * extends JPanel and implements Runnable interface.
 */

public class GameWindow extends JPanel implements Runnable {
	
	/*
	 * Class variables
	 * 
	 * width_ - board width
	 * height_ - board height
	 * thread_ - thread object
	 * gameFrames_ - game frames counter
	 * bestResult_ - best player result
	 * gameState_ - game state 
	 * snakePlayer_ - snake object
	 * pressedKey_ - key object
	 * appleHandler_ - apple handler object
	 * fileHandler_ - file handler object
	 * frogHandler_ - frog handler object
	 * wallsHandler_ - walls handler object
	 * serialVersionUID - Default variable required by compiler
	 */
	
	public final int width_ = 400;
	public final int height_ = 400;
	private Thread thread_;
	private int gameFrames_ = 0;
	private int bestResult_ = 0;
	private boolean gameState_ = false;
	private Snake snakePlayer_;
	private Key pressedKey_;
	private AppleHandler appleHandler_;
	private FileHandler fileHandler_;
	private FrogHandler frogHandler_;
	private WallsHandler wallsHandler_;
	private static final long serialVersionUID = 1L;
	
	/*
	 * Getters
	 */
	
	public int GetWidth() { return width_; }
	public int GetHeight() { return height_; }
	public Thread GetThread() { return thread_; }
	public int GetGameFrames() { return gameFrames_; }
	public int GetBestResult() { return bestResult_; }
	public boolean GetGameState() { return gameState_; }
	public Snake GetSnakePlayer() { return snakePlayer_; }
	public Key GetPressedKey() { return pressedKey_; }
	public AppleHandler GetAppleHandler() { return appleHandler_; }
	public FileHandler GetFileHandler() { return fileHandler_; }
	public FrogHandler GetFrogHandler() { return frogHandler_; }
	public WallsHandler GetWallsHandler() { return wallsHandler_; }
	
	/*
	 * Setters
	 */
	
	public void SetThread(Thread thread) { thread_ = thread; }
	public void SetGameFrames(int gameFrames) { gameFrames_ = gameFrames; }
	public void SetBestResult(int bestResult) { bestResult_ = bestResult; }
	public void SetGameState(boolean gameState) { gameState_ = gameState; }
	public void SetSnakePlayer(Snake snakePlayer) { snakePlayer_ = snakePlayer; }
	public void SetPressedKey(Key pressedKey) { pressedKey_ = pressedKey; }
	public void SetAppleHandler(AppleHandler appleHandler) { appleHandler_ = appleHandler; }
	public void SetFileHandler(FileHandler fileHandler) { fileHandler_ = fileHandler; }
	public void SetFrogHandler(FrogHandler frogHandler) { frogHandler_ = frogHandler;}
	public void SetWallsHandler(WallsHandler wallsHandler) { wallsHandler_ = wallsHandler; }
	
	/*
	 * GameWindow constructor
	 * 
	 * The Key listener is added in the constructor.
	 * Additionally, inside the constructor, the size of the board
	 * is set and the method that starts the game is called.
	 */
	
	public GameWindow() {
		setPreferredSize(new Dimension(GetWidth(), GetHeight()));
		setFocusable(true);
		SetPressedKey(new Key());
		addKeyListener(GetPressedKey());
		StartGame();
	}
	
	/*
	 * StartGame method
	 * 
	 * Inside this method, the components of the game are initialized
	 * and the main thread of the program is created.
	 */
	
	public void StartGame() {
		int snakeStartCoordX = 20;
		int snakeStartCoordY = 20;
		int wallsNumber = 15;
		
		SetSnakePlayer(new Snake(snakeStartCoordX, snakeStartCoordY));
		SetWallsHandler(new WallsHandler(wallsNumber));
		GetWallsHandler().GenerateRandomWalls();
		SetFrogHandler(new FrogHandler());
		SetAppleHandler(new AppleHandler());
		SetFileHandler(new FileHandler("BestResult.txt"));
		
		GetFrogHandler().GenerateFrog(GetWallsHandler().GetCoordsX(), GetWallsHandler().GetCoordsY());
		GetAppleHandler().GenerateApple(GetWallsHandler().GetCoordsX(), GetWallsHandler().GetCoordsY());
		SetGameState(true);
		SetThread(new Thread(this, "Main Loop"));
		SetBestResult(GetFileHandler().ReadBestResultFromFile());
		GetThread().start();
	}
	
	/*
	 * StopGame method
	 * 
	 * Method that stops the game and ends the main thread.
	 */
	
	public void StopGame() {
		SetGameState(false);
		
		GetAppleHandler().StopAppleHandlerThread();
		GetFrogHandler().StopFrogHandlerThread();
		GetFileHandler().StopFileHandlerThread();
	}
	
	/*
	 * GameFrame method
	 * 
	 * Method that checks the action in each frame, e.g. in case of collision.
	 */
	
	private void GameFrame() {
		int maxGameFrames = 300000;
		int resetGameFrames = 0;
		
		SetGameFrames(GetGameFrames() + 1);
		
		if(GetGameFrames() > maxGameFrames) {
			GetSnakePlayer().MoveSnake();
			GetFrogHandler().FrogArtificialIntelligence(GetSnakePlayer(), GetWallsHandler());
			SetGameFrames(resetGameFrames);
		}
		
		GetAppleHandler().GenerateApple(GetWallsHandler().GetCoordsX(), GetWallsHandler().GetCoordsY());
		GetAppleHandler().GameFrame(GetSnakePlayer());
		
		GetFrogHandler().GenerateFrog(GetWallsHandler().GetCoordsX(), GetWallsHandler().GetCoordsY());
		GetFrogHandler().GameFrame(GetSnakePlayer());
		
		if(GetSnakePlayer().CheckCollision() || GetWallsHandler().CheckCollision(GetSnakePlayer())) {
			StopGame();
		}
	}
	
	/*
	 * run method
	 * 
	 * Method that acts as game loop.
	 */
	
	@Override
	public void run() {
		while(GetGameState()) {
			GameFrame();
			repaint();
		}
	}
	
	/*
	 * DrawBackground method
	 * 
	 * Method that draws a black board with the given dimensions.
	 * 
	 * component - graphics object
	 */
	
	private void DrawBackground(Graphics component) { 
		component.clearRect(0, 0, GetWidth(), GetHeight());
		component.fillRect(0, 0, GetWidth(), GetHeight());
	}
	
	/*
	 * WriteBestResult method
	 * 
	 * Method used to write the best result to a file (BestResult.txt)
	 */
	
	private void WriteBestResult() {
		try {
			GetFileHandler().WriteBestResultToFile(GetBestResult());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/*
	 * ShowResult method
	 * 
	 * Method that shows the player number of points he has score,
	 * the best score, the key that needs to be pressed to restart the game.
	 * 
	 * component - graphic object
	 */
	
	private void ShowResult(Graphics component) {
		int bestResultHeight = 220;
		int bigFontSize = 30;
		int centerAlignment = 2; 
		int gameOverHeight = 100;
		int newRecordHeight = 190;
		int playerResultHeight = 250;
		int restartGameHeight = 320;
		int smallFontSize = 22;
		int startPosition = 200;
		int playerResult = (GetSnakePlayer().GetSnakeBody().size() - 1);
		String gameOverMessage = "Game Over!";
		String newRecordMessage = "New Record!";
		String bestResultMessage = "Best result: " + (GetBestResult());
		String restartGameMessage = "Press Space to restart";
		String playerResultMessage = "Your result: " + playerResult;
		
		Font bigFont = new Font("TIMES_NEW_ROMAN", Font.BOLD, bigFontSize);
		Font smallFont = new Font("TIMES_NEW_ROMAN", Font.BOLD, smallFontSize);
		FontMetrics metrices = getFontMetrics(bigFont);
		FontMetrics secondMetrices = getFontMetrics(smallFont);
		
		component.setColor(Color.WHITE);
		component.setFont(bigFont);
		
		if(playerResult  > GetBestResult()) {
			component.drawString(gameOverMessage, startPosition - (metrices.stringWidth(gameOverMessage) / centerAlignment), gameOverHeight);
			component.drawString(newRecordMessage, startPosition - (metrices.stringWidth(newRecordMessage) / centerAlignment), newRecordHeight);
			SetBestResult(playerResult);
			WriteBestResult();	
		} else {
			component.drawString(gameOverMessage, startPosition - (metrices.stringWidth(gameOverMessage) / centerAlignment), gameOverHeight);
		}
		
		component.setFont(smallFont);
		component.drawString(bestResultMessage, startPosition - secondMetrices.stringWidth(playerResultMessage) / centerAlignment, bestResultHeight);
		component.drawString(playerResultMessage, startPosition - secondMetrices.stringWidth(playerResultMessage) / centerAlignment, playerResultHeight);
		component.drawString(restartGameMessage, startPosition - secondMetrices.stringWidth(restartGameMessage) / centerAlignment, restartGameHeight);
	}
	
	/*
	 * paint method
	 * 
	 * Method that draws all game components.
	 * 
	 * component - graphic object
	 */
	
	@Override
	public void paint(Graphics component) {
		if(GetGameState()) {
			DrawBackground(component);
			GetSnakePlayer().DrawSnakeBody(component);
			GetAppleHandler().DrawApple(component);
			GetWallsHandler().DrawWalls(component);
			GetFrogHandler().DrawFrog(component);
		} else {
			ShowResult(component);
		}
	}
	
	/*
	 * Key class
	 * 
	 * Class that implements key listener.
	 * Used to get key events from the keyboard.
	 */
	
	private class Key implements KeyListener {
		
		/*
		 * keyTyped method
		 * 
		 * Method keyTyped must be overridden (empty method body)
		 * 
		 * event - KeyEvent object
		 */
		
		@Override
		public void keyTyped(KeyEvent event) {}
		
		/*
		 * keyReleased method
		 * 
		 * Method keyReleased must be overridden (empty method body)
		 * 
		 * event - KeyEvent object
		 */
		
		@Override
		public void keyReleased(KeyEvent event) {}
		
		/*
		 * keyPressed method
		 * 
		 * Overriding the keyPressed method.
		 * Method that changes the direction of the snake and
		 * restart the game by pressing the appropriate keys.
		 * 
		 * event - KeyEvent object
		 */
		
		@Override
		public void keyPressed(KeyEvent event) {
			String restartGameKey = "Space";
			String keyPressed = KeyEvent.getKeyText(event.getKeyCode());
			GetSnakePlayer().TurnSnake(keyPressed);
			
			if(!GetGameState() && keyPressed == restartGameKey) {
				StartGame();
			}
		}
		
	}
}