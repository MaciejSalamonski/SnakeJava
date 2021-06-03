package GameComponents;
import java.awt.Color;

public class Frog extends Components {

	public Frog(int coordX, int coordY, int rectangleSize, Color color) {
		super(coordX, coordY, rectangleSize, color);
	}
	
	public Frog(int coordX, int coordY, int rectangleSize) {
		super(coordX, coordY, rectangleSize, Color.GREEN);
	}
	
	public void Move(int coordX, int coordY) {
		SetCoordX(coordX);
		SetCoordY(coordY);
	}
}