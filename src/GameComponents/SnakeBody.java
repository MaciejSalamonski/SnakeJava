package GameComponents;
import java.awt.Color;

public class SnakeBody extends Components {

	public SnakeBody(int coordX, int coordY, int rectangleSize, Color color) {
		super(coordX, coordY, rectangleSize, color);
	}
	
	public SnakeBody(int coordX, int coordY, int rectangleSize) {
		super(coordX, coordY, rectangleSize, Color.blue);
	}
}