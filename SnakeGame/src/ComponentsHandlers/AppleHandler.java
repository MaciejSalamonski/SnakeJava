package ComponentsHandlers;

import GameComponents.Apple;
import SnakeLogic.Snake;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/*
 * AppleHandler class
 * 
 * A class that generates apples in random places
 * on the board and draws them.
 */

public class AppleHandler {
	
	/*
	 *  Class variables
	 *  
	 *  apple_ - An instance of an Apple class object
	 *  applesList_ - Container (list) in which apples are stored
	 *  randomNumber - A Random type variable used to draw coordinates for apples
	 *  thread_ - Thread responsible for performing calculations on apple objects
	 */
	
	private Apple apple_;
	private ArrayList<Apple> applesList_;
	private Random randomNumber_;
	private Thread thread_;
	
	/*
	 * Getters
	 */
	
	public Apple GetApple() {return apple_;}
	public ArrayList<Apple> GetApplesList() {return applesList_;}
	public Random GetRandomNumber() {return randomNumber_;}
	public Thread GetThread() {return thread_;}
	
	/*
	 * Setters 
	 */
	
	public void SetApple(Apple apple) {apple_ = apple;}
	public void SetApplesList(ArrayList<Apple> applesList) {applesList_ = applesList;}
	public void SetRandomNumber(Random randomNumber) {randomNumber_ = randomNumber;}
	public void SetThread(Thread thread) {thread_ = thread;}
	
	/*
	 * AppleHandler constructor
	 * 
	 * Creates a new apple on the apple list.
	 * In the current version, only one apple is generated, but
	 * it is possible to generate several apples.
	 */

	public AppleHandler() {
		SetApplesList(new ArrayList<Apple>());
		StartAppleHandlerThread();
	}
	
	/*
	 * StartAppleHandlerThread method
	 * 
	 * Creation and start of thread operation.
	 */
	
	private void StartAppleHandlerThread() {
		SetThread(new Thread("AppleHandler"));
		GetThread().start();
	}

	/*
	 * StopAppleHandlerThread method
	 * 
	 * Stops the thread. Handles possible exceptions.
	 */
	
	public void StopAppleHandlerThread() {
		try {
			GetThread().join();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
	
	/*
	 * RandomizeAppleCoordinates method
	 * 
	 * Generate random coordinates which not occurs in given list
	 * 
	 * unavaibleCoord - list of coordinates that cannot be generated
	 * maxNumber - max integer (value of coordinate) that can be generated
	 */
	
	private int RandomizeAppleCoordinates(ArrayList<Integer> unavaibleCoords, int maxNumber) {
		SetRandomNumber(new Random());
		int drawCoordinate = GetRandomNumber().nextInt(maxNumber);
		
		while(unavaibleCoords.contains(drawCoordinate)) {
			drawCoordinate = GetRandomNumber().nextInt(maxNumber);
		}
		
		return drawCoordinate;
	}
	
	/*
	 * GenerateApple method
	 * 
	 * Generates apple objects in a random place on the board.
	 * 
	 * unavaibleCoordsX - list of X coordinates where apple cannot be generated 
	 * unavaibleCoordsY - list of Y coordinates where apple cannot be generated
	 */
	
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
	
	/*
	 * GameFrame method
	 * 
	 * Method that check if snake has eaten the apple every frame
	 * and add new part (of snake body) to snake if it did.
	 */
	
	public void GameFrame(Snake snake) {
		for(int index = 0; index < GetApplesList().size(); index++) {
			if(GetApplesList().get(index).CheckCollision(snake.GetCoordX(), snake.GetCoordY())) {
				snake.AddSnakeBodyComponent();
				GetApplesList().remove(index);
				index--;
			}
		}
	}
	
	/*
	 * DrawApple method
	 * 
	 * Draws apples on the board.
	 * 
	 * component - graphic object
	 */
	
	public void DrawApple(Graphics component) {
		for(int index = 0; index < GetApplesList().size(); index++) {
			GetApplesList().get(index).DrawComponent(component);
		}
	}
}