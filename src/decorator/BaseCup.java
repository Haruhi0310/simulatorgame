/*
 * BaseCup class:
 *  - This class is used for decorator patterns (decorating the cup with 2 features: the stickers & the lid decorator).
 *  - Implements CupInterface.
 * 	- Holds the x and y positions of the base cup (without stickers & decorators) with coffee and toppings.
 *  - Has method to draw the cup and another method to check if it is being clicked or not.
 */

package decorator;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class BaseCup implements CupInterface {
	private BufferedImage img;
	private int xPos, yPos;
	private double scale;
	
	public BaseCup(int x, int y, double s) {
		img = ImageLoader.loadImage("assets/cup-straw.png");
		xPos = x;
		yPos = y;
		scale = s;
	}

	@Override
	public void decoration(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.drawImage(img,  -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
		
	}

	@Override
	public boolean clicked(double x, double y) {
		boolean clicked = false;
		
		if (x > (xPos - ((double) img.getWidth()) / 2 * scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}

}