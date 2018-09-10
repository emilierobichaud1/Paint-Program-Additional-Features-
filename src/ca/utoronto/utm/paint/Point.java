package ca.utoronto.utm.paint;

import java.awt.Graphics2D;

public class Point extends Shape{
	int x, y;
	Point(int x, int y){
		this.x=x; this.y=y;
		this.setColor(ColorChooserButton.getSelectedColor());
		this.setFillStyle(FillStyleChooserButton.getSelectedStyle());
		this.setLineThickness(LineThicknessChooser.getThickness());
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
