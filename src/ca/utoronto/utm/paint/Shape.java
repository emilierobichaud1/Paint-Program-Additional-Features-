package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public class Shape {
	private Color color;
	private String fillStyle;
	private int thickness;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getFillStyle() {
		return fillStyle;
	}

	public void setFillStyle(String fillStyle) {
		this.fillStyle = fillStyle;
	}
	public int getLineThickness () {
		return this.thickness;
	}
	public void setLineThickness (int thick) {
		this.thickness = thick;
	}
	public void execute(Graphics2D g2d) {
		
	}
}
