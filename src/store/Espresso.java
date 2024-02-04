package store;

import util.ImageLoader;

public class Espresso extends MainObject {

	public Espresso(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/espresso.png");
	}

	@Override
	public void setImg(int imgState) {
		// TODO Auto-generated method stub
		
	}

}
