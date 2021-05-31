package GameComponents;
import java.awt.Color;

public class Walls extends Components {

	public Walls(int coordX, int coordY, int rectangleSize, Color color) {
		super(coordX, coordY, rectangleSize, color);
	}
	
	public Walls(int coordX, int coordY, int rectangleSize) {
		super(coordX, coordY, rectangleSize, Color.gray);
	}
}