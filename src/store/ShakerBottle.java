package store;

import util.ImageLoader;

public class ShakerBottle extends MainObject{

	public ShakerBottle(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/measure.png");
	}

	@Override
	public void setImg(int imgState) {
		if (imgState == 0)
	        img = ImageLoader.loadImage("assets/measure.png"); 
	    else if (imgState == 1)
	        img = ImageLoader.loadImage("assets/measure-open.png");
	    else if (imgState == 2)
	        img = ImageLoader.loadImage("assets/measure-espresso.png");
	    else if (imgState == 3)
	        img = ImageLoader.loadImage("assets/measure-milk.png");
	    else if (imgState == 4)
	        img = ImageLoader.loadImage("assets/measure-strawberries.png");
	    else if (imgState == 5)
	        img = ImageLoader.loadImage("assets/measure-caramel.png");
	    else if (imgState == 6)
	        img = ImageLoader.loadImage("assets/measure-chocolate.png");
		
	}

}
