package store;

import util.ImageLoader;

public class Strawberry extends MainObject {

	public Strawberry(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/strawberry.png");
	}

	@Override
	public void setImg(int imgState) {
		if (imgState == 0)
	        img = ImageLoader.loadImage("assets/strawberry.png"); 
	    else if (imgState == 1)
	        img = ImageLoader.loadImage("assets/strawberry-off.png");
		
	}

}
