package store;

import util.ImageLoader;

public class Milk extends MainObject {

	public Milk(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/milk.png");
	}

	@Override
	public void setImg(int imgState) {
		// TODO Auto-generated method stub
		
	}

}
