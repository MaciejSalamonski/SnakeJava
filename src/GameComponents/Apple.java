package GameComponents;

import java.awt.Color;

/*
 * Apple class
 * 
 * The class is used to create Apple objects.
 * Apple class contains two constructors.
 */

public class Apple extends Components {
	
	/*
	 * First constructor of an Apple class
	 * 
	 * This constructor initializes four parameters.
	 * 
	 * coordX - X coordinate of an apple.
	 * coordY - Y coordinate of an apple.
	 * rectangleSize - The size of an apple on the board.
	 * appleColor - The color of an apple on the board.
	 */
	
	public Apple(int coordX, int coordY, int rectangleSize, Color appleColor) {
		super(coordX, coordY, rectangleSize, appleColor);
	}
	
	/*
	 * Second constructor of an Apple class
	 * 
	 * This constructor initializes the same parameters as the first constructor.
	 * The main difference is that the appleColor parameter is set to RED 
	 * (for example) by default. 
	 */
	
	public Apple(int coordX, int coordY, int rectangleSize) {
		super(coordX, coordY, rectangleSize, Color.RED);
	}
}