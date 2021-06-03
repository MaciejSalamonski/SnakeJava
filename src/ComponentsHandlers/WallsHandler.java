package ComponentsHandlers;

import GameComponents.Walls;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import SnakeLogic.Snake;

public class WallsHandler {
	private ArrayList<Integer> coordsX_;
	private ArrayList<Integer> coordsY_;
	private Random randomNumber_;
	private ArrayList<Walls> wallsList_;
	private int wallsNumber_;
	
	public ArrayList<Integer> GetCoordsX() { return coordsX_; }
	public ArrayList<Integer> GetCoordsY() { return coordsY_; }
	public Random GetRandomNumber() { return randomNumber_; }
	public ArrayList<Walls> GetWallsList() { return wallsList_; }
	public int GetWallsNumber() { return wallsNumber_;}
	
	public void SetRandomNumber(Random randomNumber) { randomNumber_ = randomNumber; }
	public void SetCoordsX(ArrayList<Integer> coordsX) { coordsX_ = coordsX; } 
	public void SetCoordsY(ArrayList<Integer> coordsY) { coordsY_ = coordsY; }
	public void SetWallsList(ArrayList<Walls> wallsList) { wallsList_ = wallsList; }
	public void SetWallsNumber(int wallsNumber) { wallsNumber_ = wallsNumber; }
	
	public WallsHandler(int wallsNumber) {
		SetWallsNumber(wallsNumber);
		SetCoordsX(new ArrayList<Integer>());
		SetCoordsY(new ArrayList<Integer>());
		SetWallsList(new ArrayList<Walls>());
	}
	
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
	
	public boolean CheckCollision(Snake snake) {
		for(int index = 0; index < GetWallsList().size(); index++) {
			if(GetWallsList().get(index).CheckCollision(snake.GetCoordX(), snake.GetCoordY())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean CheckCollision(int coordX, int coordY) {
		for(int index = 0; index < GetWallsList().size(); index++) {
			if(GetWallsList().get(index).CheckCollision(coordX, coordY)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean GameFrame(Snake snake) {
		for(int index = 0; index < GetWallsList().size(); index++) {
			if(GetWallsList().get(index).CheckCollision(snake.GetCoordX(), snake.GetCoordY())) {
				return true;
			}
		}
		
		return false;
	}

	public void DrawWallsComponents(Graphics component) {
		for(int index = 0; index < GetWallsList().size(); index++) {
			GetWallsList().get(index).DrawComponent(component);
		}
	}
}