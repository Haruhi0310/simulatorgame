package store;

import util.ImageLoader;

public class Spot extends MainObject {

	public Spot(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/spot.png");
	}

	@Override
	public void setImg(int imgState) {
		// TODO Auto-generated method stub
		
	}

}
