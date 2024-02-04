package store;

import util.ImageLoader;
import util.Util;

public class Cup extends MainObject {

	public Cup(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/cup.png");
	}
	
	@Override
	public void setImg(int imgState) {
		if (imgState == 0)
	        img = ImageLoader.loadImage("assets/cup.png"); 
	    else if (imgState == 1)
	        img = ImageLoader.loadImage("assets/cup-done.png");
	    else if (imgState == 2)
	        img = ImageLoader.loadImage("assets/cup-straw.png");
	    else if (imgState == 3)
	        img = ImageLoader.loadImage("assets/cup-shake.png");
	}
	
	// method to shake the cup 
	public void move() {
		xPos += Util.random(-1, 1);
		yPos += Util.random(-1, 1);
	}

}
