package store;

import util.ImageLoader;

public class Straw extends MainObject {

	public Straw(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/straws.png");
	}

	@Override
	public void setImg(int imgState) {
		// TODO Auto-generated method stub
		
	}

}
