package store;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public abstract class MainObject  {
	protected double xPos;
	protected double yPos;
	protected double sca;
	protected BufferedImage img;
	
	// constructor
	public MainObject(double x, double y, double s) {
		xPos = x;
		yPos = y;
		sca = s;
	}

	public void drawObject(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		if (x > (xPos - ((double) img.getWidth()) / 2 * sca) && x < (xPos + ((double) img.getWidth())/2*sca) && y > (yPos - ((double) img.getHeight())/2*sca) && y < (yPos + ((double) img.getHeight())/2*sca)) 
			clicked = true;
		
		return clicked;
	}
	
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}
	
	public void setY(double y) {
		yPos = y;
	}
	
	public void setX(double x) {
		xPos = x;

	}
	
	public boolean hit(MainObject obj) {
		double x  = obj.getX();
		double y = obj.getY();
		if (Math.abs(xPos - x) < 60 && Math.abs(yPos - y) < 60)
			return true;
		return false;
	}
	
	public abstract void setImg(int imgState);

}