/*
 * DecorBoardNail class:
 * 	- Subclass of DecorBoard.
 * 	- Initializes the nail of the decor board image to the img variable.
 *  - Override its parent class's method of drawBoard.
 */

package store;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import util.ImageLoader;

public class DecorBoardNail extends DecorBoard {
	
	public DecorBoardNail(double x, double y, double s) {
		super(x, y, s);
		decorBoardImg = ImageLoader.loadImage("assets/board-nail.png");
	}
	
	public void drawBoard(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);
		g2.drawImage(decorBoardImg, 0, 0, null);
		g2.setTransform(transform);
	}


}