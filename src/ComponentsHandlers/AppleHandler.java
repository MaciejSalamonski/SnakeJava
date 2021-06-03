package ComponentsHandlers;

import GameComponents.Apple;
import SnakeLogic.Snake;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class AppleHandler {
	private Apple apple_;
	private ArrayList<Apple> applesList_;
	private Random randomNumber_;
	private Thread thread_;
	
	public Apple GetApple() {return apple_;}
	public ArrayList<Apple> GetApplesList() {return applesList_;}
	public Random GetRandomNumber() {return randomNumber_;}
	public Thread GetThread() {return thread_;}
	
	public void SetApple(Apple apple) {apple_ = apple;}
	public void SetApplesList(ArrayList<Apple> applesList) {applesList_ = applesList;}
	public void SetRandomNumber(Random randomNumber) {randomNumber_ = randomNumber;}
	public void SetThread(Thread thread) {thread_ = thread;}

	public AppleHandler() {
		SetApplesList(new ArrayList<Apple>());
		StartAppleHandlerThread();
	}
	
	public void StartAppleHandlerThread() {
		SetThread(new Thread("AppleHandler"));
		GetThread().start();
	}

	public void StopAppleHandlerThread() {
		try {
			GetThread().join();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
	
	private int RandomizeAppleCoordinates(ArrayList<Integer> unavaibleCoords, int maxNumber) {
		SetRandomNumber(new Random());
		int drawCoordinate = GetRandomNumber().nextInt(maxNumber);
		
		while(unavaibleCoords.contains(drawCoordinate)) {
			drawCoordinate = GetRandomNumber().nextInt(maxNumber);
		}
		
		return drawCoordinate;
	}
	
	public void GenerateApple(ArrayList<Integer> unavaibleCoordsX, ArrayList<Integer> unavaibleCoordsY) {
		if(GetApplesList().size() == 0) {
			int maxCoordX = 39;
			int maxCoordY = 39;
			int appleRectangleSize = 10;
			
			int coordX = RandomizeAppleCoordinates(unavaibleCoordsX, maxCoordX);
			int coordY = RandomizeAppleCoordinates(unavaibleCoordsY, maxCoordY);
			
			SetApple(new Apple(coordX, coordY, appleRectangleSize));
			GetApplesList().add(GetApple());
		}
	}
	
	public void GameFrame(Snake snake) {
		for(int index = 0; index < GetApplesList().size(); index++) {
			if(GetApplesList().get(index).CheckCollision(snake.GetCoordX(), snake.GetCoordY())) {
				snake.AddSnakeBodyComponent();
				GetApplesList().remove(index);
				index--;
			}
		}
	}
	
	public void DrawApple(Graphics component) {
		for(int index = 0; index < GetApplesList().size(); index++) {
			GetApplesList().get(index).DrawComponent(component);
		}
	}
}