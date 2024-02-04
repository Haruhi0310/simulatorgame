/* RestartButton class:
 * 	- Subclass of Button.
 * 	- Initialize the restart button image to the img variable.
 * 	- Inherits method to draw the button, method to check if the button is clicked or not 
 *    and getter methods to get x,y positions.
 */

package button;

import util.ImageLoader;

public class RestartButton extends Button {

	// constructor that include initialization for the restart button image
	public RestartButton(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/restartButton.png");
	}

}
