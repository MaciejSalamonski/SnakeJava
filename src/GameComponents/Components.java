package GameComponents;
import java.awt.Color;
import java.awt.Graphics;

public class Components implements ComponentsInterface {
	private Color color_;
	private int coordX_;
	private int coordY_;
	private int width_;
	private int height_;
	
	public Color GetColor() { return color_; }
	public int GetCoordX() { return coordX_; }
	public int GetCoordY() { return coordY_; }
	public int GetWidth() { return width_; }
	public int GetHeight() { return height_; }
	
	public void SetColor(Color color) { color_ = color; }
	public void SetCoordX(int coordX) { coordX_ = coordX; }
	public void SetCoordY(int coordY) { coordY_ = coordY; }
	public void SetWidth(int width) { width_ = width; }
	public void SetHeight(int height) { height_ = height; }
	
	public Components(int coordX, int coordY, int rectangleSize, Color color) {
		SetCoordX(coordX);
		SetCoordY(coordY);
		SetWidth(rectangleSize);
		SetHeight(rectangleSize);
		SetColor(color);
	}
	
	public void GameFrame() {}
	
	public void DrawComponent(Graphics component) {
		component.setColor(GetColor());
		component.fillRect(GetCoordX() * GetWidth(), GetCoordY() * GetHeight(), GetWidth(), GetHeight());
	}
	
	public boolean CheckCollision(int coordX, int coordY) {
		if(GetCoordX() == coordX && GetCoordY() == coordY) {	
			return true;
		}
		
		return false;
	}
}