package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

public class FillStyleChooserButton extends JButton{
	
	private static String fillStyle;
	
    public FillStyleChooserButton() {
        setSelectedStyle("noFill");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String newStyle;
            	if (fillStyle == "noFill") {
            		newStyle = "Fill";
            	}
            	else {
            		newStyle = "noFill";
            	}
            	setSelectedStyle(newStyle);
            }
        });
    }
    
    public void setSelectedStyle(String s) {
    	this.fillStyle = s;
    	this.setJButtonLabel(s);
    }
    public static String getSelectedStyle() {
    	return FillStyleChooserButton.fillStyle;
    }
    
    public void setJButtonLabel(String label) {
    	this.setText(label);
    }
}
