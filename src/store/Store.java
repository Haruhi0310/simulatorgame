package store;

import java.awt.*;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.CoffeePanel;
import util.ImageLoader;

public class Store {
	private BufferedImage img;

	public Store(String file) {
		img = ImageLoader.loadImage(file);
	}

	public void drawStore(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(0, 0);
		g2.scale(1, 1);
		g2.drawImage(img, 0, 0, CoffeePanel.W_WIDTH, CoffeePanel.W_HEIGHT, null);
		g2.setTransform(at);
	}

}