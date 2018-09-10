package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;

public class Eraser extends Squiggle {
	private ArrayList<Point> points;
	
	public Eraser() {
		super();
		this.setColor(Color.white);
		this.setLineThickness(LineThicknessChooser.getThickness() + 3);
	}

}
