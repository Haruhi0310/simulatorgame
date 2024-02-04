/*
 * CupSticker class:
 *  - Subclass of CupDecorator.
 *  - Inherits fields for x,y positions, scale and base cup.
 *  - Used for decorator patterns (decorating the cup with cup stickers and lid decorator).
 *  - Has method to draw the cup stickers as the decorator on top of base coffee cup 
 *    (cup without decoration).
 */

package decorator;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class CupSticker extends CupDecorator {

	public CupSticker(CupInterface baseCup, int x, int y, double s, int stickerType) {
		super(baseCup, x, y, s);
		if (stickerType == 1) 
			img = ImageLoader.loadImage("assets/sticker-s.png");
		else if (stickerType == 2)
			img = ImageLoader.loadImage("assets/heart.png");
	}
	
	@Override
	public void decoration(Graphics2D g2) {
		super.decoration(g2);
		cupSticker(g2);
	}
	
	public void cupSticker(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
	}
}