package GameComponents;
import java.awt.Color;
import java.awt.Graphics;

public class Components implements ComponentsInterface {
	private int coordX;
	private int coordY;
	private int width;
	private int height;
	private Color color;
	
	public Components(int coordX, int coordY, int rectangleSize, Color color) {
		this.coordX = coordX;
		this.coordY = coordY;
		width = rectangleSize;
		height = rectangleSize;
		this.color = color;
	}
	
	public int getCoordX() { return coordX; }
	public int getCoordY() { return coordY; }
	public void setCoordX(int coordX) { this.coordX = coordX; }
	public void setCoordY(int coordY) {this.coordY = coordY; }
	
	public void GameFrame() {}
	
	public void DrawComponent(Graphics component) {
		component.setColor(this.color);
		component.fillRect(coordX * width, coordY * height, width, height);
	}
	
	public boolean CheckCollision(int coordX, int coordY) {
		if(this.coordX == coordX && this.coordY == coordY) {
			return true;
		}
		
		return false;
	}
}