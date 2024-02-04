package store;

import util.ImageLoader;

public class Blender extends MainObject {

	public Blender(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/blender.png");
	}

	@Override
	public void setImg(int imgState) {
		if (imgState == 0)
	        img = ImageLoader.loadImage("assets/blender.png"); 
	    else if (imgState == 1)
	        img = ImageLoader.loadImage("assets/blender-in.png");
	    else if (imgState == 2)
	        img = ImageLoader.loadImage("assets/blender-done.png");
		
	}

}
