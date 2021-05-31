package ComponentsHandlers;

import GameComponents.Apple;
import SnakeLogic.Snake;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class AppleHandler {
	public Apple apple;
	public ArrayList<Apple> applesList;
	private Thread thread;
	private Random randomNumber;

	public AppleHandler() {
		applesList = new ArrayList<Apple>();
		this.StartAppleHandlerThread();
	}
	
	public void StartAppleHandlerThread() {
		thread = new Thread("AppleHandler");
		thread.start();
	}

	public void StopAppleHandlerThread() {
		try {
			thread.join();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
	
	private int RandomizeAppleCoordinates(ArrayList<Integer> unavaibleCoords, int maxNumber) {
		randomNumber = new Random();
		int drawCoordinate = randomNumber.nextInt(maxNumber);
		
		while(unavaibleCoords.contains(drawCoordinate)) {
			drawCoordinate = randomNumber.nextInt(maxNumber);
		}
		
		return drawCoordinate;
	}
	
	public void GenerateApple(ArrayList<Integer> unavaibleCoordsX, ArrayList<Integer> unavaibleCoordsY) {
		if(this.applesList.size() == 0) {
			int maxCoordX = 39;
			int maxCoordY = 39;
			int appleRectangleSize = 10;
			
			int coordX = RandomizeAppleCoordinates(unavaibleCoordsX, maxCoordX);
			int coordY = RandomizeAppleCoordinates(unavaibleCoordsY, maxCoordY);
			
			apple = new Apple(coordX, coordY, appleRectangleSize);
			applesList.add(apple);
		}
	}
	
	public void GameFrame(Snake snake) {
		for(int index = 0; index < applesList.size(); index++) {
			if(applesList.get(index).CheckCollision(snake.coordX, snake.coordY)) {
				snake.AddSnakeBodyComponent();
				applesList.remove(index);
				index--;
			}
		}
	}
	
	public void DrawApple(Graphics component) {
		for(int index = 0; index < applesList.size(); index++) {
			applesList.get(index).DrawComponent(component);
		}
	}
}
