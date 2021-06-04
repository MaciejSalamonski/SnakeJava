package GameComponents;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Component class
 * 
 * This class implements the interface
 * used in these classes: Apple, Frog, SnakeBody, Walls.
 */

public class Components implements ComponentsInterface {
	
	/*
	 * Class variables
	 * 
	 * color_ - color of component
	 * coordX_ - X coordinate of a component
	 * coordY_ - Y coordinate of a component
	 * width_ - width of a component
	 * height_ - height of a component
	 */
	
	private Color color_;
	private int coordX_;
	private int coordY_;
	private int width_;
	private int height_;
	
	/*
	 * Getters
	 */
	
	public Color GetColor() { return color_; }
	public int GetCoordX() { return coordX_; }
	public int GetCoordY() { return coordY_; }
	public int GetWidth() { return width_; }
	public int GetHeight() { return height_; }
	
	/*
	 * Setters  
	 */
	
	public void SetColor(Color color) { color_ = color; }
	public void SetCoordX(int coordX) { coordX_ = coordX; }
	public void SetCoordY(int coordY) { coordY_ = coordY; }
	public void SetWidth(int width) { width_ = width; }
	public void SetHeight(int height) { height_ = height; }
	
	/*
	 * Components constructor
	 * 
	 * It it responsible for the appropriate setting of class variables.
	 */
	
	public Components(int coordX, int coordY, int rectangleSize, Color color) {
		SetCoordX(coordX);
		SetCoordY(coordY);
		SetWidth(rectangleSize);
		SetHeight(rectangleSize);
		SetColor(color);
	}
	
	/*
	 * DrawComponent method
	 * 
	 * It implements an interface method.
	 * This method is responsible for drawing a component 
	 * (for example an Apple) of the appropriate color and size.
	 */
	
	public void DrawComponent(Graphics component) {
		component.setColor(GetColor());
		component.fillRect(GetCoordX() * GetWidth(), GetCoordY() * GetHeight(), GetWidth(), GetHeight());
	}
	
	/*
	 * CheckCollision method
	 * 
	 * This method is responsible for checking whether the collision occurred at a specific point.
	 */
	
	public boolean CheckCollision(int coordX, int coordY) {
		if(GetCoordX() == coordX && GetCoordY() == coordY) {	
			return true;
		}
		
		return false;
	}
}