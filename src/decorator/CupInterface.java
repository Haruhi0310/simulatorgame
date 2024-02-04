/* CupInterface:
 *  - Used for decorator patterns (decorating the cup with cup stickers and lid decorators).
 *  - Holds methods for drawing the decorators / stickers and method to check if the cup 
 *    is being clicked or not.
 */

package decorator;

import java.awt.Graphics2D;

public interface CupInterface {
	public void decoration(Graphics2D g2);
	public boolean clicked(double x, double y);
}

