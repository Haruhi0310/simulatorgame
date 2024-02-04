/* StartButton class:
 * 	- Subclass of Button.
 * 	- Initialize the start button image to the img variable.
 * 	- Inherits method to draw the button, method to check if the button is clicked or not. 
 *    and getter methods to get x,y positions.
 */

package button;

import util.ImageLoader;

public class StartButton extends Button {

	// constructor that include initialization for the start button image
	public StartButton(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/startbutton.png");
	}

}
