package ComponentsHandlers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import SnakeLogic.Snake;
import GameComponents.Frog;

/*
 * FrogHandler class
 * 
 * A class that generates frogs in random places
 * on the board and draws them.
 */

public class FrogHandler {
	
	/*
	 *  Class variables
	 *  
	 *  frog_ - An instance of an Frog class object
	 *  frogAlive_ - flag that stores frog state (alive/not alive)
	 *  randomNumber - A Random type variable used to draw coordinates for frog
	 *  thread_ - Thread responsible for performing calculations on frog objects
	 */
	
	private Frog frog_;
	private boolean frogAlive_ = false;
	private Random randomNumber_;
	private Thread thread_;
	
	/*
	 * Getters
	 */
	
	public Frog GetFrog() { return frog_; }
	public boolean GetFrogAlive() { return frogAlive_; }
	public Random GetRandomNumber() { return randomNumber_; }
	public Thread GetThread() { return thread_; }
	
	/*
	 * Setters
	 */
	
	public void SetFrog(Frog frog) { frog_ = frog; }
	public void SetFrogAlive(boolean frogAlive) { frogAlive_ = frogAlive; }
	public void SetRandomNumber(Random randomNumber) { randomNumber_ = randomNumber; }
	public void SetThread(Thread thread) { thread_ = thread; }
	
	/*
	 * FrogHandler constructor
	 * 
	 * Invokes the method that starts the thread.
	 */
	
	public FrogHandler() {
		StartFrogHandlerThread();
	}
	
	/*
	 * Method StartFrogHandlerThread
	 * 
	 * Creation and start of thread operation.
	 */

	public void StartFrogHandlerThread() {
		SetThread(new Thread("Frog Handler"));
		GetThread().start();
	}
	
	/*
	 * Method StopFrogHandlerThread
	 * 
	 * Stops the thread. Handles possible exceptions.
	 */
	
	public void StopFrogHandlerThread() {
		try {
			GetThread().join();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
	
	/*
	 * Method RandomizeFrogCoordinates
	 * 
	 * Generate random coordinates which not occurs in given list.
	 * 
	 * unavaibleCoord - list of coordinates that cannot be generated
	 * maxNumber - max integer (value of coordinate) that can be generated
	 */
	
	private int RandomizeFrogCoordinates(ArrayList<Integer> unavaibleCoords, int maxNumber) {
		SetRandomNumber(new Random());
		int drawCoordinate = GetRandomNumber().nextInt(maxNumber);
		
		while(unavaibleCoords.contains(drawCoordinate)) {
			drawCoordinate = GetRandomNumber().nextInt(maxNumber);
		}
		
		return drawCoordinate;
	}
	
	/*
	 * Method GenerateFrog
	 * 
	 * Generates frog objects in a random place on the board.
	 * 
	 * unavaibleCoordsX - list of X coordinates where frog cannot be generated 
	 * unavaibleCoordsY - list of Y coordinates where frog cannot be generated
	 */
	
	public void GenerateFrog(ArrayList<Integer> unavaibleCoordsX, ArrayList<Integer> unavaibleCoordsY) {
		if(!GetFrogAlive()) {
			int maxCoordX = 39;
			int maxCoordY = 39;
			int frogRectangleSize = 10;
			
			int coordX = RandomizeFrogCoordinates(unavaibleCoordsX, maxCoordX);
			int coordY = RandomizeFrogCoordinates(unavaibleCoordsY, maxCoordY);
			
			SetFrog(new Frog(coordX, coordY, frogRectangleSize));
			SetFrogAlive(true);
		}
	}
	
	/*
	 * Method CheckWallsCollision
	 * 
	 * This method checks if  the frog's coordinates (X & Y)
	 * match the coordinates of any of the walls.
	 * 
	 * coordX - frog X coordinate that needs to be checked
	 * coordY - frog Y coordinate that needs to be checked
	 * walls - An object of type WallsHandler. It stores the coordinates
	 * of all generated walls. We call the CheckCollision method on this
	 * object to check if the frog's coordinates are different that
	 * coordinates of any wall.
	 */
	
	private boolean CheckWallsCollision(int coordX, int coordY, WallsHandler walls) {
		return walls.CheckCollision(coordX, coordY);
	}
	
	/*
	 * CheckCollision method
	 * 
	 * A method that checks whether the drawn coordinates, which will
	 * be the next position of the frog on the board, will cause a 
	 * collision with the walls.
	 * 
	 * coordX - randomly drawn X coordinate, which will be the frog's
	 * location in the next frame
	 * coordY - randomly drawn Y coordinate, which will be the frog's
	 * location in the next frame
	 * walls - An object of type WallsHandler. It stores the coordinates
	 * of all generated walls
	 */
	
	public boolean CheckCollision(int coordX, int coordY, WallsHandler walls) {
		if(CheckWallsCollision(coordX, coordY, walls)) {	
			return true;
		}
		
		return false;
	}
	
	/*
	 * GetRandomCoordinate method
	 * 
	 * Creation of random coordinates that will be used in an AI
	 * algorithm consisting in moving the frog on the board.
	 */
	
	private int GetRandomCoordinate() {
		int beginRange = 0;
		int endRange = 6;
		
		return ThreadLocalRandom.current().nextInt(beginRange, endRange);
	}
	
	/*
	 * FrogArtificialIntelligence method
	 * 
	 * A method that implements AI algorithm.
	 * The frog moves on the board in random directions
	 * (specified coordinates range inside GetRandomCoordinate method).
	 * Additionally, the frog doesn't collide with walls and the snake.
	 * 
	 * snake - snake object. It stores the coordinates of all snakes body parts
	 * walls - An object of type WallsHandler. It stores the coordinates
	 * of all generated walls
	 */
	
	public void FrogArtificialIntelligence(Snake snake, WallsHandler walls) {
		int coordX = GetFrog().GetCoordX();
		int coordY = GetFrog().GetCoordY();
		int randomCoordinate = GetRandomCoordinate();
		
		switch(randomCoordinate) {
			case 0:
				coordX++;
				break;
			case 1:
				coordX--;
				break;
			case 2:
				coordY++;
				break;
			case 3:
				coordY--;
				break;
			default:
				break;
		}
		
		if(!snake.CheckCollision(coordX, coordY) && !CheckCollision(coordX, coordY, walls)) {
			GetFrog().Move(coordX, coordY);
		}
	}
	
	/*
	 * GameFrame method
	 * 
	 * Method that check if snake has eaten the frog every frame
	 * and add new part (of snake body) to snake if it did.
	 */
	
	public void GameFrame(Snake snake) {
		if(GetFrog().CheckCollision(snake.GetCoordX(), snake.GetCoordY())) {
			snake.AddSnakeBodyComponent();
			SetFrogAlive(false);
		}
	}
	
	/*
	 * Method DrawFrog
	 * 
	 * Draws frogs on the board.
	 * 
	 * component - graphic object
	 */
	
	public void DrawFrog(Graphics component) {
		GetFrog().DrawComponent(component);
	}
}