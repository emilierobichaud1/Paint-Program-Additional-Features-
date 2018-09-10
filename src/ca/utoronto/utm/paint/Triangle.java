package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Triangle extends Shape {
	private Point corner1;
	private Point corner2;
	private Point corner3;
	
	public Triangle(Point corner1, Point corner2, Point corner3) {
		this.corner1 = corner1;
		this.corner2 = corner2;
		this.corner3 = corner3;
		this.setColor(ColorChooserButton.getSelectedColor());
		this.setFillStyle(FillStyleChooserButton.getSelectedStyle());
		this.setLineThickness(LineThicknessChooser.getThickness());
	}
	
	public Point getCorner1() {
		return corner1;
	}
	public Point getCorner2() {
		return corner2;
	}
	public Point getCorner3() {
		return corner3;
	}
	public void setCorner1(Point newcorner) {
		this.corner1=newcorner;
	}
	public void setCorner2(Point newcorner) {
		this.corner2=newcorner;
	}
	public void setCorner3(Point newcorner) {
		this.corner3=newcorner;
	}
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		int[] xCoordinates = new int[] {corner1.getX(), corner2.getX(), corner3.getX()};
		int[] yCoordinates = new int[] {corner1.getY(), corner2.getY(), corner3.getY()};
		if (this.getFillStyle() == "Fill") {
			g2d.fillPolygon(xCoordinates, yCoordinates, 3);
		}
		else {
			g2d.setStroke(new BasicStroke(this.getLineThickness()));
			g2d.drawPolygon(xCoordinates, yCoordinates, 3);
		}
	}

}
