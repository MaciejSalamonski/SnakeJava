package SnakeLogic;

import GameComponents.SnakeBody;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
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
	
	public void SetColor(Color color) { color_ = color; }
	public void SetCoordX(int coordX) { coordX_ = coordX; }
	public void SetCoordY(int coordY) { coordY_ = coordY; }
	public void SetDirectionDown(boolean directionDown) { directionDown_ = directionDown; }
	public void SetDirectionLeft(boolean directionLeft) { directionLeft_ = directionLeft; }
	public void SetDirectionUp(boolean directionUp) { directionUp_ = directionUp; }
	public void SetDirectionRight(boolean directionRight) { directionRight_ = directionRight; }
	public void SetRectangleSize(int rectangleSize) { rectangleSize_ = rectangleSize; }

	public Snake(int coordX, int coordY, Color color) {
		int rectangleSize = 10;
		
		SetCoordX(coordX);
		SetCoordY(coordY);
		SetRectangleSize(rectangleSize);
		SetColor(color);
		AddSnakeBodyComponent();
	}
	
	public Snake(int coordX, int coordY) {
		int rectangleSize = 10;
		
		SetCoordX(coordX);
		SetCoordY(coordY);
		SetRectangleSize(rectangleSize);
		SetColor(Color.BLUE);
		AddSnakeBodyComponent();
	}
	
	public void AddSnakeBodyComponent() {
		SnakeBody snakeBodyPart = new SnakeBody(GetCoordX(), GetCoordY(), GetRectangleSize(), GetColor());
		GetSnakeBody().add(snakeBodyPart);
	}
	
	public ArrayList<Integer> GetListOfSnakeCoordinatesX() {
		ArrayList<Integer> coordsX = new ArrayList<Integer>();
		
		for(int index = 0; index < GetRectangleSize(); index++) {
			coordsX.add(GetSnakeBody().get(index).GetCoordX());
		}
		
		return coordsX;
	}
	
	public ArrayList<Integer> GetListOfSnakeCoordinatesY() {
		ArrayList<Integer> coordsY = new ArrayList<Integer>();
		
		for(int index = 0; index < GetRectangleSize(); index++) {
			coordsY.add(GetSnakeBody().get(index).GetCoordY());
		}
		
		return coordsY;
	}
	
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
	
	private SnakeBody GetSnakeHead() {
		int headPosition = GetSnakeBody().size() - 1;
		
		return GetSnakeBody().get(headPosition);
	}
	
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
	
	public boolean CheckCollision() {
		if(CheckSelfCollision()) {
			return true;
		}
		
		return false;
	}
	
	public boolean CheckCollision(int coordX, int coordY) {
		for(int index = 0; index < GetSnakeBody().size(); index++) {
			if(GetSnakeBody().get(index).CheckCollision(coordX, coordY)) {	
				return true;
			}
		}
		
		return false;
	}

	public void DrawSnakeBody(Graphics component) {
		for(int index = 0; index < GetSnakeBody().size(); index++) {
			GetSnakeBody().get(index).DrawComponent(component);
		}
	}
}