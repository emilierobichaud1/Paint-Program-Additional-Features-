package ca.utoronto.utm.paint;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	private int highlightedButton;
	public ShapeChooserPanel(View view) {		
		this.view=view;
		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Triangle", "Squiggle","Line", "Polyline", "Eraser"};
		this.setLayout(new GridLayout(buttonLabels.length + 3, 1));

		for (String label : buttonLabels) {
			JButton button = new JButton(label);
			try {
				button.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Icons(label)))));
			}
			catch (Exception ex) {
				System.out.println(ex);
			}
			this.add(button);
			validate();
			button.addActionListener(this);
		}
		ColorChooserButton color = new ColorChooserButton(Color.BLACK);
		this.add(color);
		FillStyleChooserButton fill = new FillStyleChooserButton();
		try {
			fill.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Icons("Fill")))));
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		this.add(fill);
		LineThicknessChooser thickness = new LineThicknessChooser(3);
		try {
			thickness.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Icons("Thickness")))));
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		this.add(thickness);
	}

	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		this.getComponentAt(0, this.highlightedButton).setBackground(null);
		button.setBackground(Color.BLUE);
		this.highlightedButton = button.getY();
		this.view.getPaintPanel().setMode(e.getActionCommand());
		
	}
	
	public String Icons (String label) {
		String Circle,Square,Rectangle,Triangle,Squiggle,Polyline,Eraser,Fill,Colour,Thickness;
		Circle = "Pictures/Circle.PNG";
		Rectangle = "Pictures/Rectangle.PNG";
		Square = "Pictures/Square.PNG";
		Triangle = "Pictures/Triangle1.PNG";
		Squiggle = "Pictures/Squiggle.PNG";
		Polyline = "Pictures/Polyline.PNG";
		Eraser = "Pictures/Eraser.PNG";
		Fill = "Pictures/Fill.PNG";
		Colour = "Pictures/Colour.PNG";
		Thickness = "Pictures/Thickness.PNG";
		if (label == "Circle") {
			return Circle;
		}
		else if (label == "Rectangle") {
			return Rectangle;
		}
		else if (label == "Square") {
			return Square;
		}
		else if (label == "Triangle") {
			return Triangle;
		}
		else if (label == "Squiggle") {
			return Squiggle;
		}
		else if (label == "Polyline") {
			return Polyline;
		}
		else if (label=="Eraser") {
			return Eraser;
		}
		else if (label == "Fill") {
			return Fill;
		}
		else if (label == "Colour") {
			return Colour;
		}
		else {
			return Thickness;
		}
	}
}
