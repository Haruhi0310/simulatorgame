/*
 * StrawberryButton class:
 * 	- Subclass of Button.
 *  - Is used for creating strawberry sticker button for the user to click on and decorating the cup.
 * 	- Initialize the strawberry sticker button image to the img variable.
 * 	- Inherits method to draw the button, method to check if the button is clicked or not, 
 *    and getter methods to get x,y positions.
 */

package button;

import util.ImageLoader;

public class StrawberryButton extends Button {
	
	// constructor that include initialization for the pink sticker button image
	public StrawberryButton(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/button-s.png");
	}

}