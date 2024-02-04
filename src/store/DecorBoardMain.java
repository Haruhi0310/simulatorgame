/*
 * DecorBoardMain class:
 * 	- Subclass of DecorBoard.
 * 	- Initializes the main decor board image to the img variable.
 *  - Override its parent class's method of drawBoard and transform it's x,y positions to the center point of the image
 */

package store;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class DecorBoardMain extends DecorBoard {
	
	private float swingAngle; // angles for swinging effect
	private float swingRotateAngle; //  rotating angles for swinging effect

	public DecorBoardMain(double x, double y, double s) {
		super(x, y, s);
		swingAngle = 0;
		swingRotateAngle = (float) 0.009;
		decorBoardImg = ImageLoader.loadImage("assets/board.png");
	}

	// method to draw and transform the board with (x,y) as the top center point of the image
	@Override
	public void drawBoard(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);
		g2.rotate(swingAngle);
		g2.drawImage(decorBoardImg, -decorBoardImg.getWidth() / 2, 0, null);
		g2.setTransform(transform);
	}
	
	// method to move and make the board part swing
	public void move() {
		swingAngle = swingAngle + swingRotateAngle;
	    if(Math.abs(swingAngle) > Math.PI/22) swingRotateAngle *= -1;
	}

}