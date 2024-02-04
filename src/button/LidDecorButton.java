/*
 * LidDecorButton class:
 * 	- Subclass of Button.
 *  - Is used for creating lid decorator button for the user to click on and decorating the cup.
 * 	- Initialize the lid decorator button image to the img variable.
 * 	- Inherits method to draw the button, method to check if the button is clicked or not 
 *    and getter methods to get x,y positions.
 */

package button;

import util.ImageLoader;

public class LidDecorButton extends Button {
	
	// constructor that include initialization for the lid decor button image
	public LidDecorButton(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/button-y.png");
	}

}