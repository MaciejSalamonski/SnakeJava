package GameComponents;
import java.awt.Color;

public class Apple extends Components {
	public Apple(int coordX, int coordY, int rectangleSize, Color appleColor) {
		super(coordX, coordY, rectangleSize, appleColor);
	}
	
	public Apple(int coordX, int coordY, int rectangleSize) {
		super(coordX, coordY, rectangleSize, Color.red);
	}
}