package ComponentsHandlers;

import GameComponents.Walls;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import SnakeLogic.Snake;

public class WallsHandler {
	public ArrayList<Walls> wallsList;
	public ArrayList<Integer> coordsX;
	public ArrayList<Integer> coordsY;
	public int wallsNumber;
	private Random randomNumber;
	
	public WallsHandler(int wallsNumber) {
		this.wallsNumber = wallsNumber;
		wallsList = new ArrayList<Walls>();
		this.coordsX = new ArrayList<Integer>();
		this.coordsY = new ArrayList<Integer>();
	}
	
	public void GenerateRandomWalls() {
		if(this.wallsList.size() == 0) {
			int coordX;
			int coordY;
			Walls wall;
			
			for(int index = 0; index < this.wallsNumber; index++) {
				randomNumber = new Random();
				coordX = randomNumber.nextInt(39);
				coordY = randomNumber.nextInt(39);
				
				this.coordsX.add(coordX);
				this.coordsY.add(coordY);
				
				int rectangleSize = 10;
				wall = new Walls(coordX, coordY, rectangleSize);
				wallsList.add(wall);
			}
			
			GenerateBoarders();
		}
	}
	
	public void GenerateBoarders() {
		Walls wall;
		int rectangleSize = 10;
		int maximumBoarderResolution = 40;
		int maximumHeightOfBoarder = 40;
		int maximumWidthtOfBoarder = 40;
		int minimumBoarderResolution = 0;
		int minimumHeightOfBoarder = -1;
		int minimumWidthOfBoarder = -1;
		
		for(int index = minimumBoarderResolution; index < maximumBoarderResolution; index++) {
			wall = new Walls(index, minimumHeightOfBoarder, rectangleSize);
			wallsList.add(wall);
		}
		
		for(int index = minimumBoarderResolution; index < maximumBoarderResolution; index++) {
			wall = new Walls(index, maximumHeightOfBoarder, rectangleSize);
			wallsList.add(wall);
		}
		
		for(int index = minimumBoarderResolution; index < maximumBoarderResolution; index++) {
			wall = new Walls(minimumWidthOfBoarder, index, rectangleSize);
			wallsList.add(wall);
		}
		
		for(int index = minimumBoarderResolution; index < maximumBoarderResolution; index++) {
			wall = new Walls(maximumWidthtOfBoarder, index, rectangleSize);
			wallsList.add(wall);
		}
	}
	
	public boolean GameFrame(Snake snake) {
		for(int index = 0; index < wallsList.size(); index++) {
			if(wallsList.get(index).CheckCollision(snake.coordX, snake.coordY)) {
				return true;
			}
		}
		
		return false;
	}

	public void DrawWallsComponents(Graphics component) {
		for(int index = 0; index < wallsList.size(); index++) {
			wallsList.get(index).DrawComponent(component);
		}
	}
	
	public boolean CheckCollision(Snake snake) {
		for(int index = 0; index < wallsList.size(); index++) {
			if(wallsList.get(index).CheckCollision(snake.coordX, snake.coordY)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean CheckCollision(int coordX, int coordY) {
		for(int index = 0; index < wallsList.size(); index++) {
			if(wallsList.get(index).CheckCollision(coordX, coordY)) {
				return true;
			}
		}
		
		return false;
	}
}
