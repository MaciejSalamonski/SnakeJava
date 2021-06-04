package ComponentsHandlers;

import GameComponents.Walls;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import SnakeLogic.Snake;

/*
 * WallsHandler class
 * 
 * This class is responsible for generating walls on the board.
 */

public class WallsHandler {
	
	/*
	 *  Class variables
	 *  
	 *  coordsX_ - List that stores the X coordinates of the walls
	 *  coordsY_ - List that stores the Y coordinates of the walls
	 *  randomNumber_ - A Random type variable used to draw coordinates for frog
	 *  wallsList_ - List of generated walls
	 *  wallsNumber_ - Number of generated walls
	 */
	
	private ArrayList<Integer> coordsX_;
	private ArrayList<Integer> coordsY_;
	private Random randomNumber_;
	private ArrayList<Walls> wallsList_;
	private int wallsNumber_;
	
	/*
	 * Getters
	 */
	
	public ArrayList<Integer> GetCoordsX() { return coordsX_; }
	public ArrayList<Integer> GetCoordsY() { return coordsY_; }
	public Random GetRandomNumber() { return randomNumber_; }
	public ArrayList<Walls> GetWallsList() { return wallsList_; }
	public int GetWallsNumber() { return wallsNumber_;}
	
	/*
	 * Setters
	 */
	
	public void SetRandomNumber(Random randomNumber) { randomNumber_ = randomNumber; }
	public void SetCoordsX(ArrayList<Integer> coordsX) { coordsX_ = coordsX; } 
	public void SetCoordsY(ArrayList<Integer> coordsY) { coordsY_ = coordsY; }
	public void SetWallsList(ArrayList<Walls> wallsList) { wallsList_ = wallsList; }
	public void SetWallsNumber(int wallsNumber) { wallsNumber_ = wallsNumber; }
	
	/*
	 * WallsHandler constructor
	 * 
	 * The constructor sets individual variables and creates that
	 * lists stores walls and coordinates.
	 * 
	 * wallsNumber - Number of generated walls
	 */
	
	public WallsHandler(int wallsNumber) {
		SetWallsNumber(wallsNumber);
		SetCoordsX(new ArrayList<Integer>());
		SetCoordsY(new ArrayList<Integer>());
		SetWallsList(new ArrayList<Walls>());
	}
	
	/*
	 * GenerateBoarders method
	 * 
	 * Method that generate boarders on the board.
	 */
	
	public void GenerateBoarders() {
		int rectangleSize = 10;
		int maximumBoarderResolution = 40;
		int maximumHeightOfBoarder = 40;
		int maximumWidthtOfBoarder = 40;
		int minimumBoarderResolution = 0;
		int minimumHeightOfBoarder = -1;
		int minimumWidthOfBoarder = -1;
		Walls wall;
		
		for(int index = minimumBoarderResolution; index < maximumBoarderResolution; index++) {
			wall = new Walls(index, minimumHeightOfBoarder, rectangleSize);
			GetWallsList().add(wall);
		}
		
		for(int index = minimumBoarderResolution; index < maximumBoarderResolution; index++) {
			wall = new Walls(index, maximumHeightOfBoarder, rectangleSize);
			GetWallsList().add(wall);
		}
		
		for(int index = minimumBoarderResolution; index < maximumBoarderResolution; index++) {
			wall = new Walls(minimumWidthOfBoarder, index, rectangleSize);
			GetWallsList().add(wall);
		}
		
		for(int index = minimumBoarderResolution; index < maximumBoarderResolution; index++) {
			wall = new Walls(maximumWidthtOfBoarder, index, rectangleSize);
			GetWallsList().add(wall);
		}
	}
	
	/*
	 * GenerateRandomWalls method
	 * 
	 * Method that generates walls with random coordinates on the board.
	 */
	
	public void GenerateRandomWalls() {
		if(GetWallsList().size() == 0) {
			int coordX;
			int coordY;
			Walls wall;
			
			for(int index = 0; index < GetWallsNumber(); index++) {
				int rectangleSize = 10;
				int drawMaximumNumber = 35;
				
				SetRandomNumber(new Random());
				coordX = GetRandomNumber().nextInt(drawMaximumNumber);
				coordY = GetRandomNumber().nextInt(drawMaximumNumber);
				
				GetCoordsX().add(coordX);
				GetCoordsY().add(coordY);
				
				wall = new Walls(coordX, coordY, rectangleSize);
				GetWallsList().add(wall);
			}
			
			GenerateBoarders();
		}
	}
	
	/*
	 * CheckCollision(Snake) method
	 * 
	 * Check for collisions between the snake and walls.
	 * 
	 * snake - snake object
	 */
	
	public boolean CheckCollision(Snake snake) {
		for(int index = 0; index < GetWallsList().size(); index++) {
			if(GetWallsList().get(index).CheckCollision(snake.GetCoordX(), snake.GetCoordY())) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * CheckCollision(int, int) method
	 * 
	 * Check for collisions between the wall and the coordinate pair.
	 * 
	 * coordX - X coordinate to check
	 * coordY - Y coordinate to check
	 */
	
	public boolean CheckCollision(int coordX, int coordY) {
		for(int index = 0; index < GetWallsList().size(); index++) {
			if(GetWallsList().get(index).CheckCollision(coordX, coordY)) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * GameFrame method
	 * 
	 * Method that check if the snake hit wall 
	 * during the duration of the frame
	 * 
	 * snake - snake object
	 */
	
	public boolean GameFrame(Snake snake) {
		for(int index = 0; index < GetWallsList().size(); index++) {
			if(GetWallsList().get(index).CheckCollision(snake.GetCoordX(), snake.GetCoordY())) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * DrawWalls method
	 * 
	 * Draws walls on the board.
	 * 
	 * component - graphic object
	 */

	public void DrawWalls(Graphics component) {
		for(int index = 0; index < GetWallsList().size(); index++) {
			GetWallsList().get(index).DrawComponent(component);
		}
	}
}