package SnakeLogic;

import GameComponents.SnakeBody;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * Snake class
 * 
 * Class responsible for creating the snake object.
 */

public class Snake {
	
	/*
	 * Class variables
	 * 
	 * color_ - snake color variable
	 * coordX_ - X coordinate of snake body part
	 * coordY_ - Y coordinate of snake body part
	 * directionDown_ - down direction flag
	 * directionLeft_ - left direction flag
	 * directionUp_ - up direction flag
	 * directionRight_ - right direction flag
	 * rectangleSize_ - size of the snake's body part on the board
	 * snakeBody_ - list that stores all parts of the snake's body
	 * snakeBodyPart_ - snakeBody object (part of snake's body).
	 * SnakeBoty types stores information about coordinates for each part
	 * of snake's body etc.
	 */
	
	private Color color_;
	private int coordX_;
	private int coordY_;
	private boolean directionDown_ = false;
	private boolean directionLeft_ = false;
	private boolean directionUp_ = false;
	private boolean directionRight_ = false;
	private int rectangleSize_;
	private ArrayList<SnakeBody> snakeBody_ = new ArrayList<SnakeBody>();
	private SnakeBody snakeBodyPart_;
	
	/*
	 * Getters
	 */
	
	public Color GetColor() { return color_; }
	public int GetCoordX() { return coordX_; }
	public int GetCoordY() { return coordY_; }
	public boolean GetDirectionDown() { return directionDown_; }
	public boolean GetDirectionLeft() { return directionLeft_; }
	public boolean GetDirectionUp() { return directionUp_; }
	public boolean GetDirectionRight() { return directionRight_; }
	public int GetRectangleSize() { return rectangleSize_; }
	public ArrayList<SnakeBody> GetSnakeBody() { return snakeBody_; }
	public SnakeBody GetSnakeBodyPart() { return snakeBodyPart_; }
	
	/*
	 * Setters 
	 */
	
	public void SetColor(Color color) { color_ = color; }
	public void SetCoordX(int coordX) { coordX_ = coordX; }
	public void SetCoordY(int coordY) { coordY_ = coordY; }
	public void SetDirectionDown(boolean directionDown) { directionDown_ = directionDown; }
	public void SetDirectionLeft(boolean directionLeft) { directionLeft_ = directionLeft; }
	public void SetDirectionUp(boolean directionUp) { directionUp_ = directionUp; }
	public void SetDirectionRight(boolean directionRight) { directionRight_ = directionRight; }
	public void SetRectangleSize(int rectangleSize) { rectangleSize_ = rectangleSize; }

	/*
	 * Snake first constructor
	 * 
	 * The constructor sets the coordinates, the size of the body
	 * parts on the board, the color of the snake's body part,
	 * and adds the body part to the rest of the snake's body.
	 * 
	 * coordX - X coordinate of the snake's body part
	 * coordY - Y coordinate of the snake's body part
	 * color - color of the snake's body part
	 */
	
	public Snake(int coordX, int coordY, Color color) {
		int rectangleSize = 10;
		
		SetCoordX(coordX);
		SetCoordY(coordY);
		SetRectangleSize(rectangleSize);
		SetColor(color);
		AddSnakeBodyComponent();
	}
	
	/*
	 * Snake second constructor
	 * 
	 * The second constructor does the same as the first.
	 * The only difference is that it doesn't take the color parameter.
	 * Color of the snake's body parts is set to BLUE by default.
	 * 
	 * coordX - X coordinate of the snake's body part
	 * coordY - Y coordinate of the snake's body part
	 */
	
	public Snake(int coordX, int coordY) {
		int rectangleSize = 10;
		
		SetCoordX(coordX);
		SetCoordY(coordY);
		SetRectangleSize(rectangleSize);
		SetColor(Color.BLUE);
		AddSnakeBodyComponent();
	}
	
	/*
	 * AddSnakeBodyComponent method
	 * 
	 * Method that add body part to snake's body.
	 */
	
	public void AddSnakeBodyComponent() {
		SnakeBody snakeBodyPart = new SnakeBody(GetCoordX(), GetCoordY(), GetRectangleSize(), GetColor());
		GetSnakeBody().add(snakeBodyPart);
	}
	
	/*
	 * GetListOfSnakeCoordinatesX method
	 * 
	 * Get list of snakes X coordinates.
	 */
	
	public ArrayList<Integer> GetListOfSnakeCoordinatesX() {
		ArrayList<Integer> coordsX = new ArrayList<Integer>();
		
		for(int index = 0; index < GetRectangleSize(); index++) {
			coordsX.add(GetSnakeBody().get(index).GetCoordX());
		}
		
		return coordsX;
	}
	
	/*
	 * GetListOfSnakeCoordiantesY method
	 * 
	 * Get list of snakes Y coordinates.
	 */
	
	public ArrayList<Integer> GetListOfSnakeCoordinatesY() {
		ArrayList<Integer> coordsY = new ArrayList<Integer>();
		
		for(int index = 0; index < GetRectangleSize(); index++) {
			coordsY.add(GetSnakeBody().get(index).GetCoordY());
		}
		
		return coordsY;
	}
	
	/*
	 * TurnSnake method
	 * 
	 * Method that turns snake in given direction.
	 * 
	 * direction - input direction by player
	 */
	
	public void TurnSnake(String direction) {
		if(direction == "Up" && !GetDirectionDown()) {
			SetDirectionRight(false);
			SetDirectionLeft(false);
			SetDirectionUp(true);
		}
		
		if(direction == "Down" && !GetDirectionUp()) {
			SetDirectionRight(false);
			SetDirectionLeft(false);
			SetDirectionDown(true);
		}
		
		if(direction == "Left" && !GetDirectionRight()) {
			SetDirectionUp(false);
			SetDirectionDown(false);
			SetDirectionLeft(true);
		}
		
		if(direction == "Right" && !GetDirectionLeft()) {
			SetDirectionUp(false);
			SetDirectionDown(false);
			SetDirectionRight(true);
		}
	}
	
	/*
	 * MoveSnake method
	 * 
	 * Method that  responsible for the movement
	 * of the snake in a given direction.
	 */
	
	public void MoveSnake() {
		if(GetDirectionUp()) {
			SetCoordY(GetCoordY() - 1);
		} else if(GetDirectionDown()) {
			SetCoordY(GetCoordY() + 1);
		} else if(GetDirectionLeft()) {
			SetCoordX(GetCoordX() - 1);
		} else if(GetDirectionRight()) {
			SetCoordX(GetCoordX() + 1);
		}
		
		SnakeBody snakeBodyPart = new SnakeBody(GetCoordX(), GetCoordY(), GetRectangleSize(), GetColor());
		GetSnakeBody().add(snakeBodyPart);
		
		
		if(GetSnakeBody().size() > 1) {
			GetSnakeBody().remove(0);
		}
	}
	
	/*
	 * GetSnakeHead method
	 * 
	 * Getting snake head.
	 */
	
	private SnakeBody GetSnakeHead() {
		int headPosition = GetSnakeBody().size() - 1;
		
		return GetSnakeBody().get(headPosition);
	}
	
	/*
	 * CheckSelfCollision method
	 * 
	 * Method that checks if the snake has bitten its tail.
	 */
	
	private boolean CheckSelfCollision() {
		int minumumSnakeBodySizeToSelfCollision = 4;
		
		if(GetSnakeBody().size() > minumumSnakeBodySizeToSelfCollision) {
			for(int index = 0; index < GetSnakeBody().size() - 2; index++) {
				if(GetSnakeBody().get(index).CheckCollision(GetSnakeHead().GetCoordX(), GetSnakeHead().GetCoordY())) {
					return true;
				}
			}
			
			return false;
		}
		
		return false;
	}
	
	/*
	 * CheckCollision() method
	 * 
	 * Method that checks for collisions.
	 */
	
	public boolean CheckCollision() {
		if(CheckSelfCollision()) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * CheckCollision(int, int) method
	 * 
	 * Method that checks for collisions with certain (given) coordinates.
	 * 
	 * coordX - X coordinate of point
	 * coordY - Y coordinate of point
	 */
	
	public boolean CheckCollision(int coordX, int coordY) {
		for(int index = 0; index < GetSnakeBody().size(); index++) {
			if(GetSnakeBody().get(index).CheckCollision(coordX, coordY)) {	
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * DrawSnakeBody method
	 * 
	 * Draws body parts (entire snake) on the board.
	 * 
	 * component - graphic object
	 */

	public void DrawSnakeBody(Graphics component) {
		for(int index = 0; index < GetSnakeBody().size(); index++) {
			GetSnakeBody().get(index).DrawComponent(component);
		}
	}
}