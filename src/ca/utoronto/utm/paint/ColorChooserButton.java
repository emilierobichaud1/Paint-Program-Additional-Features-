package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;


/**
 * ColorChooserButton is a class that extends JButton. It toggles the visibility of 
 * the JColorChooser and uses it to set the color of the shape or squiggle that is being drawn.
 * @author jeremynevado
 *
 */
public class ColorChooserButton extends JButton {

	// current is a static variable that shares what color is currently selected by the JColorChooser
    private static Color current;

    /**
     * Constructs a ColorChooserButton and adds an ActionListener to the Button so current updates whenever 
     * a new Color is chosen with the JColorChooser.
     * @param c Default color of ColorChooserButton.
     */
    public ColorChooserButton(Color c) {
        setSelectedColor(c); 
        this.setLabel("Color Chooser");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Color newColor = JColorChooser.showDialog(null, "Choose a color", current);
                setSelectedColor(newColor);
            }
        });
    }
    
    /**
     * A method used to find what the currently selected color.
     * @return returns current (The currently selected color).
     */
    public static Color getSelectedColor() {
        return current;
    }

    /**
     * Whenever a different Color is selected with the JColorChooser it updates current to newColor
     * and updates the Icon of the button to match
     * @param newColor
     * @param notify
     */
    public void setSelectedColor(Color newColor) {

        if (newColor == null) return;

        current = newColor;
        setIcon(createIcon(current, 16, 16));
        repaint();
    }
    
    /**
     * Creates an Icon for the ColorChooserButton.
     * @param main The Color of the image on the ColorChooserButton
     * @param integer width width of the Icon
     * @param integer height height of the Icon
     * @return returns the completed Icon
     */
    public static  ImageIcon createIcon(Color main, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(main);
        graphics.fillRect(0, 0, width, height);
        graphics.setXORMode(Color.DARK_GRAY);
        graphics.drawRect(0, 0, width-1, height-1);
        image.flush();
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }
}
