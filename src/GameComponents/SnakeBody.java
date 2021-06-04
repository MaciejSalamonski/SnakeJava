package GameComponents;

import java.awt.Color;

/*
 * SnakeBody class
 * 
 * This class is used to create snake body parts.
 * SnakeBody class contains two constructors.
 */

public class SnakeBody extends Components {

	/*
	 * First constructor of a SnakeBody class
	 * 
	 * This constructor initializes four parameters.
	 * 
	 * coordX - X coordinate of a part of snake body.
	 * coordY - Y coordinate of a part of snake body.
	 * rectangleSize - The size of a part of a snake body on the board.
	 * snakeBodyColor - The color of a part of a snake body on the board.
	 */
	
	public SnakeBody(int coordX, int coordY, int rectangleSize, Color snakeBodyColor) {
		super(coordX, coordY, rectangleSize, snakeBodyColor);
	}
	
	/*
	 * Second constructor of a SnakeBody class
	 * 
	 * This constructor initializes the same parameters as the first constructor.
	 * The main difference is that the snakeBodyColor parameter is set to BLUE 
	 * (for example) by default. 
	 */
	
	public SnakeBody(int coordX, int coordY, int rectangleSize) {
		super(coordX, coordY, rectangleSize, Color.BLUE);
	}
}