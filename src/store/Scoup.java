package store;

import util.ImageLoader;

public class Scoup extends MainObject{

	public Scoup(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/scoup.png");
	}

	@Override
	public void setImg(int imgState) {
		if (imgState == 0)
	        img = ImageLoader.loadImage("assets/scoup.png"); 
	    else if (imgState == 1)
	        img = ImageLoader.loadImage("assets/scoup-off.png");
	    else if (imgState == 2)
	        img = ImageLoader.loadImage("assets/scoup-strawberry.png");
	    else if (imgState == 3)
	        img = ImageLoader.loadImage("assets/scoup-chocolate.png");
	   
	}

}
