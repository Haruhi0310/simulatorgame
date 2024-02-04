/*
 * DecorBoard class:
 * 	- Superclass of DecorBoardMain and decorBoardNail.
 *  - Holds common fields for the decor board components such as x, y position, scale and image.
 *  - Holds an abstract method to draw the board.
 */

package store;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class DecorBoard {
	
	protected double xPos;
	protected double yPos;
	protected double sca;
	protected BufferedImage decorBoardImg;
	
	public DecorBoard(double x, double y, double s) {
		xPos = x;
		yPos = y;
		sca = s;
	}
	
	public abstract void drawBoard(Graphics2D g2);

}