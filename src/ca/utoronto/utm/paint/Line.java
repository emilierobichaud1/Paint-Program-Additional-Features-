package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Line extends Shape {
	private Point p1, p2;
	private static Point start;
	public Line(Point p1) {
		this.setP1(p1);
		this.setColor(ColorChooserButton.getSelectedColor());
		this.setFillStyle(FillStyleChooserButton.getSelectedStyle());
		this.setLineThickness(LineThicknessChooser.getThickness());
	}
	public Point getP2() {
		return p2;
	}
	public void setP2(Point p2) {
		this.p2 = p2;
	}
	public Point getP1() {
		return p1;
	}
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	public static Point getStart() {
		return Line.start;
	}
	public static void setStart(Point point) {
		Line.start = point;
	}
	
	
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(this.getLineThickness()));
		g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		
	}

