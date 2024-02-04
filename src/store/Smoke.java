package store;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import processing.core.PApplet;
import util.Util;

public class Smoke {
	private float xPos, yPos;
	private int width, height;

	private float xstart;
	private float xnoise;
	private float ynoise;
	private PApplet pa;

	public Smoke(float x , float y, int w, int h) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		xstart = Util.random(5);
		xnoise = xstart;
		ynoise = Util.random(8);
	    pa = new PApplet();
	}
	
	public void drawSmoke(Graphics2D g2) {
		float noiseFactor;
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);

		for(int y=0; y <=getHeight(); y += 5) {
			ynoise += 0.2;
			xnoise = xstart;
			for(int x= 0; x<=getWidth(); x+=1) {
				xnoise+= 0.1;
				noiseFactor = pa.noise(xnoise,ynoise);

				AffineTransform at1 = g2.getTransform();
				g2.translate(x, y);
				g2.rotate(noiseFactor*Util.radians(500));
				float edgeSize = noiseFactor * 35;
				//int grey = (int) (150 + (noiseFactor*105));
				int alph = (int) (150 +(noiseFactor*105));
				g2.setColor(new Color(254, 244, 220, alph));
				//g2.setColor(new Color(grey, grey, grey));
				g2.fill(new Ellipse2D.Float(-edgeSize/2, -edgeSize/6, edgeSize, edgeSize*2*noiseFactor));
				g2.setTransform(at1);
			}

		}
		g2.setTransform(at);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
