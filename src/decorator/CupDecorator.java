/*
 * CupDecorator class:
 *  - Implements CupInterface.
 *  - Superclass of CupSticker and LidDecorator.
 *  - Hold common fields for x,y positions, scale and base cup.
 *  - This class is used for decorator patterns (decorating the cup with cup stickers and lid decorators).
 *  - Hold and use methods from the base cup for drawing those 2 decorations and checking 
 *    if the cup is being clicked or not.
 */

package decorator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class CupDecorator implements CupInterface {
	
	protected int xPos, yPos;
	protected double scale;
	protected BufferedImage img;
	CupInterface baseCup;
	
	public CupDecorator(CupInterface baseCup, int x, int y, double s) {
		this.baseCup = baseCup;
		xPos = x;
		yPos = y;
		scale = s;
	}
	
	@Override
	public void decoration(Graphics2D g2) {	// draw the base cup or cup stickers and lid decorators
		baseCup.decoration(g2);
	}
	
	@Override
	public boolean clicked(double x, double y) {
		return baseCup.clicked(x, y);
	}
	
	
	

}
