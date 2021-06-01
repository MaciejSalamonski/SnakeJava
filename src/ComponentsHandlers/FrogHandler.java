package ComponentsHandlers;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import SnakeLogic.Snake;
import GameComponents.Frog;

public class FrogHandler {
	public Frog frog;
	public boolean isFrogAlive = false;
	private Thread thread;
	private Random randomNumber;
	
	public FrogHandler() {
		this.StartFrogHandlerThread();
	}

	public void StartFrogHandlerThread() {
		this.thread = new Thread("FrogHandler");
		thread.start();
	}
	
	public void StopFrogHandlerThread() {
		try {
			thread.join();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
	
	private int RandomizeFrogCoordinates(ArrayList<Integer> unavaibleCoords, int maxNumber) {
		randomNumber = new Random();
		int drawCoordinate = randomNumber.nextInt(maxNumber);
		
		while(unavaibleCoords.contains(drawCoordinate)) {
			drawCoordinate = randomNumber.nextInt(maxNumber);
		}
		
		return drawCoordinate;
	}
	
	public void GenerateFrog(ArrayList<Integer> unavaibleCoordsX, ArrayList<Integer> unavaibleCoordsY) {
		if(!isFrogAlive) {
			int maxCoordX = 39;
			int maxCoordY = 39;
			int frogRectangleSize = 10;
			
			int coordX = RandomizeFrogCoordinates(unavaibleCoordsX, maxCoordX);
			int coordY = RandomizeFrogCoordinates(unavaibleCoordsY, maxCoordY);
			
			frog = new Frog(coordX, coordY, frogRectangleSize);
			this.isFrogAlive = true;
		}
	}
	
	public void GameFrame(Snake snake) {
		if(frog.CheckCollision(snake.coordX, snake.coordY)) {
			snake.AddSnakeBodyComponent();
			this.isFrogAlive = false;
		}
	}
	
	public void DrawFrog(Graphics component) {
		frog.DrawComponent(component);
	}
	
	private boolean CheckWallsCollision(int coordX, int coordY, WallsHandler walls) {
		return walls.CheckCollision(coordX, coordY);
	}
	
	public boolean CheckCollision(int coordX, int coordY, WallsHandler walls) {
		if(CheckWallsCollision(coordX, coordY, walls)) {
			return true;
		}
		
		return false;
	}

	private int GetRandomCoordinate() {
		return ThreadLocalRandom.current().nextInt(0, 6);
	}
	
	public void FrogArtificialIntelligence(Snake snake, WallsHandler walls) {
		int coordX = this.frog.getCoordX();
		int coordY = this.frog.getCoordY();
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
			this.frog.Move(coordX, coordY);
		}
	}
}