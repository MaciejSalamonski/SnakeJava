package SnakeLogic;

import GameComponents.SnakeBody;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {

	public SnakeBody snakeBodyPart;
	public ArrayList<SnakeBody> snakeBody = new ArrayList<SnakeBody>();
	public int coordX;
	public int coordY;
	public int rectangleSize;
	private Color color;
	private boolean rightDirection = false;
	private boolean leftDirection = false;
	private boolean upDirection = false;
	private boolean downDirection = false;

	public Snake(int coordX, int coordY, Color color) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.rectangleSize = 10;
		this.color = color;
		AddSnakeBodyComponent();
	}
	
	public void AddSnakeBodyComponent() {
		SnakeBody snakeBodyPart = new SnakeBody(coordX, coordY, rectangleSize, color);
		this.snakeBody.add(snakeBodyPart);
	}
	
	public void DrawSnakeBody(Graphics component) {
		for(int index = 0; index < this.snakeBody.size(); index++) {
			this.snakeBody.get(index).DrawComponent(component);
		}
	}
	
	public void MoveSnake() {
		if(this.upDirection) {
			this.coordY--;
		} else if(this.downDirection) {
			this.coordY++;
		} else if(this.leftDirection) {
			this.coordX--;
		} else if(this.rightDirection) {
			this.coordX++;
		}
		
		SnakeBody snakeBodyPart = new SnakeBody(this.coordX, this.coordY, this.rectangleSize, this.color);
		this.snakeBody.add(snakeBodyPart);
		
		
		if(this.snakeBody.size() > 1) {
			this.snakeBody.remove(0);
		}
	}
	
	public void TurnSnake(String direction) {
		if(direction == "Up" && !this.downDirection) {
			this.rightDirection = false;
			this.leftDirection = false;
			this.upDirection = true;
		}
		
		if(direction == "Down" && !this.upDirection) {
			this.rightDirection = false;
			this.leftDirection = false;
			this.downDirection = true;
		}
		
		if(direction == "Left" && !this.rightDirection) {
			this.upDirection = false;
			this.downDirection = false;
			this.leftDirection = true;
		}
		
		if(direction == "Right" && !this.leftDirection) {
			this.upDirection = false;
			this.downDirection = false;
			this.rightDirection = true;
		}
	}
	
	private SnakeBody GetSnakeHead() {
		int headPosition = this.snakeBody.size() - 1;
		
		return this.snakeBody.get(headPosition);
	}
	
	public boolean CheckCollision() {
		if(CheckSelfCollision()) {
			return true;
		}
		
		return false;
	}
	
	public boolean CheckCollision(int coordX, int coordY) {
		for(int index = 0; index < snakeBody.size(); index++) {
			if(snakeBody.get(index).CheckCollision(coordX, coordY)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean CheckSelfCollision() {
		int minumumSnakeBodySizeToSelfCollision = 4;
		
		if(this.snakeBody.size() > minumumSnakeBodySizeToSelfCollision) {
			for(int index = 0; index < snakeBody.size() - 2; index++) {
				if(snakeBody.get(index).CheckCollision(GetSnakeHead().getCoordX(), GetSnakeHead().getCoordY())) {
					return true;
				}
			}
			
			return false;
		}
		
		return false;
	}
	
	public ArrayList<Integer> GetListOfSnakeCoordinatesX() {
		ArrayList<Integer> coordsX = new ArrayList<Integer>();
		
		for(int index = 0; index < this.rectangleSize; index++) {
			coordsX.add(this.snakeBody.get(index).getCoordX());
		}
		
		return coordsX;
	}
	
	public ArrayList<Integer> GetListOfSnakeCoordinatesY() {
		ArrayList<Integer> coordsY = new ArrayList<Integer>();
		
		for(int index = 0; index < this.rectangleSize; index++) {
			coordsY.add(this.snakeBody.get(index).getCoordY());
		}
		
		return coordsY;
	}
}