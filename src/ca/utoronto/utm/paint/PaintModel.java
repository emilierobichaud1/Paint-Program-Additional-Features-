package ca.utoronto.utm.paint;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
	private ArrayList<Shape> drawnShapes = new ArrayList<Shape>();
	private ArrayList<Shape> shapeFeedback = new ArrayList<Shape>();
	private ArrayList<Shape> undos = new ArrayList<Shape>();
	public void executeDrawn(Graphics2D g2d) {
		for (Shape command : drawnShapes) {
			command.execute(g2d);
		}
	}
	
	public void executeFeedback(Graphics2D g2d) {
		for (Shape command : shapeFeedback) {
			command.execute(g2d);
		}
		this.shapeFeedback = new ArrayList<Shape>();

	}
	
	public void addPermanent(Shape command) {
		this.drawnShapes.add(command);
		this.undos = new ArrayList<Shape>();
		this.setChanged();
		this.notifyObservers();
		//this.undos = new ArrayList<Shape>();

	}
	
	public void addFeedback(Shape command) {
		this.shapeFeedback.add(command);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addUndo () {
		this.undos.add(this.drawnShapes.get(this.drawnShapes.size()-1));
		this.drawnShapes.remove(this.drawnShapes.size()-1);
		this.shapeFeedback = new ArrayList<Shape>();
		this.setChanged();
		this.notifyObservers();
	}
	public void addRedo () {
		if(this.undos.size() > 0) {
			this.drawnShapes.add(this.undos.get(this.undos.size() -1));
			this.undos.remove(this.undos.size() - 1);
			this.setChanged();
			this.notifyObservers();
		}
	}
}
