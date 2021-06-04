package GameComponents;

import java.awt.Color;

/*
* Walls class
* 
* The class is used to create walls objects on the board.
* Walls are an obstacle for the snake and an additional
* challenge for the player.
*/

public class Walls extends Components {

	/*
	 * First constructor of a Walls class
	 * 
	 * This constructor initializes four parameters
	 * 
	 * coordX - X coordinate of a wall.
	 * coordY - Y coordinate of a wall.
	 * rectangleSize - The size of a wall on the board.
	 * wallColor - The color of a wall on the board.
	 */
	
	public Walls(int coordX, int coordY, int rectangleSize, Color wallColor) {
		super(coordX, coordY, rectangleSize, wallColor);
	}
	
	/*
	 * Second constructor of a Walls class
	 * 
	 * This constructor initializes the same parameters as the first constructor.
	 * The main difference is that the wallColor parameter is set to GRAY 
	 * (for example) by default. 
	 */
	
	public Walls(int coordX, int coordY, int rectangleSize) {
		super(coordX, coordY, rectangleSize, Color.GRAY);
	}
}