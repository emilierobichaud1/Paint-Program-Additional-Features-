package ca.utoronto.utm.paint;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {
	private int i=0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	
	private int xPressed;
	private int yPressed;

	private String mode; // modifies how we interpret input (could be better?)
	private Circle circle; // the circle we are building
	private Rectangle rectangle;
	private Square square;
	private Triangle triangle;
	private Squiggle squiggle;
	private Eraser eraser;
	private Line line;
	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//this.mode="Circle"; // bad code here?
		
		this.model = model;
		this.model.addObserver(this);
		
		this.view=view;
	}

	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
		
        super.paintComponent(g); //paint background
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		// Origin is at the top left of the window 50 over, 75 down
       
		i=i+1;
		model.executeDrawn(g2d);
		model.executeFeedback(g2d);
		g2d.dispose();
	}

	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	/**
	 *  Controller aspect of this
	 */
	public void setMode(String mode){
		this.mode=mode;}

	// MouseMotionListener below
	@Override
	public void mouseMoved(MouseEvent e) {

		if (this.mode == "Polyline") {
			if (this.line != null) {
				this.line.setP2(new Point(e.getX(), e.getY()));
				this.model.addFeedback(this.line);
			}
		}
		else if(this.mode == "Line") {
			if(this.line != null) {
				this.line.setP2(new Point(e.getX(), e.getY()));
				this.model.addFeedback(this.line);
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.mode=="Squiggle"){
			this.squiggle.add(new Point(e.getX(), e.getY()));
			model.addFeedback(this.squiggle);}
			
		else if(this.mode=="Eraser"){
			this.eraser.add(new Point(e.getX(), e.getY()));
			model.addFeedback(this.eraser);
			}
			
			
		else if(this.mode=="Circle"){
			int xSquared = (e.getX()-xPressed)*(e.getX()-xPressed);
			int ySquared = (e.getY()-yPressed)*(e.getY()-yPressed);
			int radius = (int)Math.sqrt(xSquared+ySquared);
			this.circle.setRadius(radius);
			model.addFeedback(this.circle);
			
		}
		else if(this.mode=="Rectangle") {
			int width = -(xPressed-e.getX());
			int height = -(yPressed-e.getY());
			if (height < 0 && width >= 0) {
				height = Math.abs((height));
				this.rectangle.setHeight(height);
				this.rectangle.setWidth(width);
				this.rectangle.setCorner(new Point(xPressed, yPressed - height));
				model.addFeedback(this.rectangle);
				
			}
			if (height >=0 && width >=0) {
				this.rectangle.setHeight(height);
				this.rectangle.setWidth(width);
				model.addFeedback(this.rectangle);
		}
			else if (height>=0 && width<0) {
				width = Math.abs(width);
				this.rectangle.setHeight(height);
				this.rectangle.setWidth(width);
				this.rectangle.setCorner(new Point(xPressed-width, yPressed));
				model.addFeedback(this.rectangle);
			}
			else {
				width = Math.abs(width);
				height = Math.abs(height);
				this.rectangle.setHeight(height);
				this.rectangle.setWidth(width);
				this.rectangle.setCorner(new Point(xPressed - width, yPressed - height));
				model.addFeedback(this.rectangle);
			}
			}
		else if(this.mode=="Square") {
			int width = -(xPressed-e.getX());
			if (e.getY() < yPressed && width >= 0) {
				this.square.setCorner(new Point(xPressed, (yPressed - width)));
			}
			else if(e.getY() >= yPressed && width >= 0) {
				this.square.setCorner(new Point(xPressed, yPressed));
			}
			else if (e.getY() >= yPressed && width <0) {
				width = Math.abs(width);
				this.square.setCorner(new Point((xPressed - width), yPressed));
			}
			else if(e.getY() < yPressed && width < 0) {
				width = Math.abs(width);
				this.square.setCorner(new Point((xPressed - width), yPressed - width));
			}
			this.square.setWidth(width);
			model.addFeedback(this.square);
		}
		
		else if (this.mode=="Triangle") {
			if(e.getY() > yPressed) {
				if(e.getX() > xPressed) {
					this.triangle.setCorner1(new Point(xPressed, yPressed));
					this.triangle.setCorner2(new Point (xPressed - Math.abs((xPressed-e.getX())), e.getY()));
					this.triangle.setCorner3(new Point (e.getX(), e.getY()));
					}
				else if(e.getX() < xPressed) {
					this.triangle.setCorner1(new Point(xPressed, yPressed));
					this.triangle.setCorner3(new Point (xPressed + Math.abs((xPressed-e.getX())), e.getY()));
					this.triangle.setCorner2(new Point (e.getX(), e.getY()));
					}}
			else if(e.getY() < yPressed) {
				if(e.getX() > xPressed) {
					this.triangle.setCorner2(new Point(xPressed - Math.abs((xPressed-e.getX())), yPressed));
					this.triangle.setCorner1(new Point (((triangle.getCorner2().getX() + triangle.getCorner3().getX()) / 2), 
							e.getY()));
					this.triangle.setCorner3(new Point (e.getX(), yPressed));
					}
				else if(e.getX() < xPressed) {
					this.triangle.setCorner2(new Point(xPressed + Math.abs((xPressed-e.getX())), yPressed));
					this.triangle.setCorner1(new Point (((triangle.getCorner2().getX() + triangle.getCorner3().getX()) / 2), 
							e.getY()));
					this.triangle.setCorner3(new Point (e.getX(), yPressed));
					}
				}
			model.addFeedback(this.triangle);
			}
		}

		

	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {

		if (this.mode == "Polyline") {
			if (this.line == null) {
				this.line = new Line(new Point(e.getX(), e.getY()));
				Line.setStart(new Point(e.getX(), e.getY()));
			}
			else if(Line.getStart().getX() -5 <= this.line.getP2().getX() && Line.getStart().getX() + 5 >= this.line.getP2().getX() && Line.getStart().getY() - 5 <= this.line.getP2().getY() && Line.getStart().getY() + 5 >= this.line.getP2().getY()) {
				this.line.setP2(Line.getStart());
				this.model.addPermanent(this.line);
				this.line = null;
			}
			else if (this.line != null){
				this.line.setP2(new Point(e.getX(), e.getY()));
				this.model.addPermanent(this.line);
				this.line = new Line(new Point (e.getX(), e.getY()));
			}
		}
		else if(this.mode == "Line") {
			if (this.line == null) {
				this.line = new Line(new Point(e.getX(), e.getY()));
			}
			else if(this.line != null) {
				this.line.setP2(new Point(e.getX(), e.getY()));
				this.model.addPermanent(this.line);
				this.line = null;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(this.mode=="Squiggle"){
			this.squiggle = new Squiggle();
			this.squiggle.add(new Point(e.getX(), e.getY()));
			this.model.addPermanent(this.squiggle);
			
		} 
		if(this.mode=="Eraser"){
			this.eraser = new Eraser();
			this.eraser.add(new Point(e.getX(), e.getY()));
			this.model.addPermanent(this.eraser);
			
		}
		
		else if(this.mode=="Circle"){
			xPressed = e.getX();
			yPressed = e.getY();
			Point centre = new Point(xPressed,yPressed);
			this.circle=new Circle(centre, 0);
		}
		else if(this.mode=="Rectangle") {
			xPressed = e.getX();
			yPressed = e.getY();
			Point corner = new Point(e.getX(), e.getY());
			this.rectangle = new Rectangle(corner, 0, 0);
			
		}
		else if(this.mode=="Square") {
			xPressed = e.getX();
			yPressed = e.getY();
			Point corner = new Point(e.getX(), e.getY());
			this.square = new Square(corner, 0);
		}
		
		else if(this.mode=="Triangle") {
			xPressed = e.getX();
			yPressed = e.getY();
			Point corner = new Point(e.getX(), e.getY());
			this.triangle = new Triangle(corner, corner, corner);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.mode=="Circle"){
			if(this.circle!=null){
				int xsquared = (this.circle.getCentre().getX()-e.getX())*(this.circle.getCentre().getX()-e.getX());
				int ysquared = (this.circle.getCentre().getY()-e.getY())*(this.circle.getCentre().getY()-e.getY());
				int radius = Math.abs((int)Math.sqrt(xsquared+ysquared));
				this.circle.setRadius(radius);
				Point centre = new Point(xPressed, yPressed);
				this.circle.setCentre(centre);
				this.model.addPermanent(this.circle);
				this.circle=null;
			}
		}
		else if(this.mode=="Rectangle") {
			if(this.rectangle!=null) {
				this.model.addPermanent(this.rectangle);
				this.rectangle=null;
			}}
		else if(this.mode=="Square") {
			if(this.square!=null) {
				this.model.addPermanent(this.square);
				this.square=null;
			}
		}
		else if(this.mode=="Triangle") {
			if(this.triangle!=null) {
				this.model.addPermanent(this.triangle);
				this.triangle=null;
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if(this.mode=="Line"){
			this.line = null;
			
		}
		else if(this.mode == "Polyline") {
			this.line = null;
		}
	}
}
