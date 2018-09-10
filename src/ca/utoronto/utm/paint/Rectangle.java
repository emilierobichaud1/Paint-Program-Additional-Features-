package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Rectangle extends Shape {
	private int width;
	private int height;
	private Point corner;
	
	public Rectangle(Point corner, int width, int height) {
		this.width = width;
		this.height = height;
		this.corner = corner;
		this.setColor(ColorChooserButton.getSelectedColor());
		this.setFillStyle(FillStyleChooserButton.getSelectedStyle());
		this.setLineThickness(LineThicknessChooser.getThickness());
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Point getCorner() {
		return this.corner;
	}
	
	public void setCorner(Point newCorner) {
		this.corner = newCorner;
	}
	
	public void setWidth(int x) {
		this.width = x;
	}
	
	public void setHeight(int y) {
		this.height = y;
	}
	
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		if (this.getFillStyle() == "Fill") {
			g2d.fillRect(this.getCorner().getX(), this.getCorner().getY(), width, height);
		}
		else {
			g2d.setStroke(new BasicStroke(this.getLineThickness()));
			g2d.drawRect(this.getCorner().getX(), this.getCorner().getY(), width, height);
		}
	}
}