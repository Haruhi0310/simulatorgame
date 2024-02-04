package store;

import util.ImageLoader;

public class Chocolate extends MainObject {

	public Chocolate(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/chocolate.png");
	}

	@Override
	public void setImg(int imgState) {
		// TODO Auto-generated method stub
		
	}

}
