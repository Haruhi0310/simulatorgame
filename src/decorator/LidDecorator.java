/*
 * LidDecorator class:
 *  - Subclass of CupDecorator.
 *  - Inherits fields for x,y positions, scale and base cup.
 *  - Used for decorator patterns (decorating the cup with cup stickers and lid decorator).
 *  - Has method to draw the lid decorator as the decoration on the lid of the base coffee cup
 *    (cup without decoration).
 */

package decorator;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class LidDecorator extends CupDecorator {

	public LidDecorator(CupInterface baseCup, int x, int y, double s) {
		super(baseCup, x, y, s);
			img = ImageLoader.loadImage("assets/sticker.png");
	}
	
	@Override
	public void decoration(Graphics2D g2) {
		super.decoration(g2);
		lidDecorator(g2);
	}
	
	public void lidDecorator(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
	}
}