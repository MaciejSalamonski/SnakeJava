package GameComponents;
import java.awt.Color;

/*
* Frog class.
* The class is used to create Frog objects.
* Frog objects move around the map in a random direction.
* Frogs are the food of the snake.
* Frog class contains two constructors and Move method.
*/

public class Frog extends Components {
	
	/*
	 * First constructor of a Frog class.
	 * This constructor initializes four parameters.
	 * coordX - X coordinate of a frog.
	 * coordY - Y coordinate of a frog.
	 * rectangleSize - The size of a frog on the board.
	 * frogColor - The color of a frog on the board.
	 */
	
	public Frog(int coordX, int coordY, int rectangleSize, Color frogColor) {
		super(coordX, coordY, rectangleSize, frogColor);
	}
	
	/*
	 * Second constructor of a Frog class.
	 * This constructor initializes the same parameters as the first constructor.
	 * The main difference is that the frogColor parameter is set to GREEN 
	 * (for example) by default. 
	 */
	
	public Frog(int coordX, int coordY, int rectangleSize) {
		super(coordX, coordY, rectangleSize, Color.GREEN);
	}
	
	/*
	 * Method Move.
	 * This method is responsible for moving the frog in a specific direction.
	 * It takes two parameters:
	 * coordX - X coordinate of a frog
	 * coordY - Y coordinate of a frog
	 * Method move calls setters that set the coordinates.
	 */
	
	public void Move(int coordX, int coordY) {
		SetCoordX(coordX);
		SetCoordY(coordY);
	}
}