package store;

import util.ImageLoader;

public class Caramel extends MainObject {

	public Caramel(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/caramel.png");
	}

	@Override
	public void setImg(int imgState) {
		// TODO Auto-generated method stub
		
	}

}

