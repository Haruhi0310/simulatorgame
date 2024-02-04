/*
 * Button class:
 * 	- Superclass for all of the button subclasses.
 * 	- Hold common fields and methods for the subclasses.
 * 	- Hold method to check if the button is being clicked or not.
 * 	- Hold getter methods to get x,y position of the button.
 *  - Hold method to draw the buttons.
 */

package button;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Button {
	protected double xPos; // field for x position
	protected double yPos; // field for y position
	protected double sca; // scale of the image
	protected BufferedImage img; // field for loading the image

	public Button(double x, double y, double s) {
		xPos = x;
		yPos = y;
		sca = s;
	}

	public void drawButton(Graphics2D g2) { // method to draw the buttons
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null); // draw the image with (x,y) as the center

		g2.setTransform(transform);
	}

	public boolean clicked(double x, double y) { // check if the button is being clicked or not
		boolean clicked = false;

		if (x > (xPos - ((double) img.getWidth()) / 2 * sca) && x < (xPos + ((double) img.getWidth()) / 2 * sca)
				&& y > (yPos - ((double) img.getHeight()) / 2 * sca)
				&& y < (yPos + ((double) img.getHeight()) / 2 * sca))
			clicked = true;

		return clicked;
	}

	public double getXPos() { // get x position of the button
		return xPos;
	}

	public double getYPos() { // get y position of the button
		return yPos;
	}

}
