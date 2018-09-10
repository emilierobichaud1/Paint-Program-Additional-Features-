package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;
/*
 * this class is unused. Tried to delete from the master but couldnt get it to go away.
 */
public class Polyline extends Shape{
	private Point start, end;
	private ArrayList<Point> points;
	
	public Polyline (Point start) {
		this.start=start;
		this.end = start;
		this.points = new ArrayList<Point>();
		this.points.add(start);
		this.setColor(ColorChooserButton.getSelectedColor());
		this.setFillStyle(FillStyleChooserButton.getSelectedStyle());
		this.setLineThickness(LineThicknessChooser.getThickness());
	}

	public void add(Point point) {
		this.points.add(point);
		this.end = point;
	}

	public Point getStart(){
		return this.start;
	}
	
	public Point getEnd() {
		return this.end;
	}
	
	public ArrayList<Point> getPoints(){
		return this.points;
	}
	
	@Override
	public void execute(Graphics2D g2d) {
		int[] xvalues = new int[points.size()];
		int[] yvalues = new int[this.points.size()];
		
		for (Point point : points) {
			int i = 0;
			xvalues[i] = point.getX();
			yvalues[i] = point.getY();
			i++;
		}
		
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(this.getLineThickness()));
		g2d.drawPolyline(xvalues, yvalues, points.size());
		/**
		if (this.points.size() > 1) {
			for(int i = 0; i<points.size() - 1; i++) {
				g2d.setColor(this.getColor());
				g2d.setStroke(new BasicStroke(this.getLineThickness()));
				g2d.drawLine(this.points.get(i).getX(), this.points.get(i).getY(), this.points.get(i + 1).getX(), this.points.get(i + 1).getY());
			}

		}
		**/

	}
}