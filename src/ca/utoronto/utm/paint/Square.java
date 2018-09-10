package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Square extends Shape{
	private int width;
	private Point corner;
	
	public Square(Point corner, int width) {
		this.width = width;
		this.corner = corner;
		this.setColor(ColorChooserButton.getSelectedColor());
		this.setFillStyle(FillStyleChooserButton.getSelectedStyle());
		this.setLineThickness(LineThicknessChooser.getThickness());
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setCorner(Point corner) {
		this.corner = corner;
	}
	public Point getCorner() {
		return this.corner;
	}
	
	public void setWidth(int x) {
		this.width = x;
	}
	
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		if (this.getFillStyle() == "Fill") {
			g2d.fillRect(this.getCorner().getX(), this.getCorner().getY(), width, width);
		}
		else {
			g2d.setStroke(new BasicStroke(this.getLineThickness()));
			g2d.drawRect(this.getCorner().getX(), this.getCorner().getY(), width, width);
		}
	}
}
