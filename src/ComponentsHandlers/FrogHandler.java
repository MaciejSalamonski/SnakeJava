package ComponentsHandlers;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import SnakeLogic.Snake;
import GameComponents.Frog;

public class FrogHandler {
	private Frog frog_;
	private boolean frogAlive_ = false;
	private Random randomNumber_;
	private Thread thread_;
	
	public Frog GetFrog() { return frog_; }
	public boolean GetFrogAlive() { return frogAlive_; }
	public Random GetRandomNumber() { return randomNumber_; }
	public Thread GetThread() { return thread_; }
	
	public void SetFrog(Frog frog) { frog_ = frog; }
	public void SetFrogAlive(boolean frogAlive) { frogAlive_ = frogAlive; }
	public void SetRandomNumber(Random randomNumber) { randomNumber_ = randomNumber; }
	public void SetThread(Thread thread) { thread_ = thread; }
	
	public FrogHandler() {
		StartFrogHandlerThread();
	}

	public void StartFrogHandlerThread() {
		SetThread(new Thread("Frog Handler"));
		GetThread().start();
	}
	
	public void StopFrogHandlerThread() {
		try {
			GetThread().join();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
	
	private int RandomizeFrogCoordinates(ArrayList<Integer> unavaibleCoords, int maxNumber) {
		SetRandomNumber(new Random());
		int drawCoordinate = GetRandomNumber().nextInt(maxNumber);
		
		while(unavaibleCoords.contains(drawCoordinate)) {
			drawCoordinate = GetRandomNumber().nextInt(maxNumber);
		}
		
		return drawCoordinate;
	}
	
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
	
	public void GameFrame(Snake snake) {
		if(GetFrog().CheckCollision(snake.GetCoordX(), snake.GetCoordY())) {
			snake.AddSnakeBodyComponent();
			SetFrogAlive(false);
		}
	}
	
	public void DrawFrog(Graphics component) {
		GetFrog().DrawComponent(component);
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
		int beginRange = 0;
		int endRange = 6;
		
		return ThreadLocalRandom.current().nextInt(beginRange, endRange);
	}
	
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
}