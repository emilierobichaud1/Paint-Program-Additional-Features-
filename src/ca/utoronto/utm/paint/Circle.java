package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Shape{
	private Point centre;
	private int radius;
	
	public Circle(Point centre, int radius){
		this.centre = centre;
		this.radius = radius;
		this.setColor(ColorChooserButton.getSelectedColor()); ;
		this.setFillStyle(FillStyleChooserButton.getSelectedStyle());
		this.setLineThickness(LineThicknessChooser.getThickness());
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	@Override
	public void execute(Graphics2D g2d) {
		int x = this.getCentre().getX();
		int y = this.getCentre().getY();
		g2d.setColor(this.getColor());
		int diameter = 2*(this.getRadius());
		if (this.getFillStyle() == "Fill") {
			g2d.fillOval(x-radius, y-radius, diameter, diameter);
		}
		else {
			g2d.setStroke(new BasicStroke(this.getLineThickness()));
			g2d.drawOval(x-radius, y-radius, diameter, diameter);
		}
	}
}
