package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LineThicknessChooser extends JButton{
	public static int thickness;
	
	public LineThicknessChooser(int Len) {
		this.thickness = Len;
		setThicknessLabel();
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cycleThickness();
				setThicknessLabel();
			}
		});
	}
	
	public void cycleThickness() {
	   if (this.thickness<9) {
		   this.thickness=this.thickness+1;
	   }
	   else {
		   this.thickness=0;
	   }
	}
	
	public void setThicknessLabel() {
		this.setText("Line Thickness: "+this.thickness);
	}
	
	public static int getThickness() {
		return LineThicknessChooser.thickness;
	 }
	    
	
}
