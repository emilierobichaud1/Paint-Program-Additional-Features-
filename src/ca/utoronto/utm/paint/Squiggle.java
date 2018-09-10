package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Squiggle extends Shape{
	private ArrayList<Point> points;
	public Squiggle() {
		this.points = new ArrayList<Point>();
		this.setColor(ColorChooserButton.getSelectedColor());
		this.setFillStyle(FillStyleChooserButton.getSelectedStyle());
		this.setLineThickness(LineThicknessChooser.getThickness());
	}
	public void add(Point p) {
		this.points.add(p);
	}
	
	@Override
	public void execute(Graphics2D g2d) {
		if (points.size() > 1) {
			for (int i = 0; i < points.size() - 1; i++) {
				g2d.setColor(this.getColor());
				g2d.setStroke(new BasicStroke(this.getLineThickness()));
				g2d.drawLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
			}
		}

	}
}
