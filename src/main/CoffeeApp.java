package main;

import javax.swing.JFrame;

import main.CoffeeApp;
import main.CoffeePanel;
import processing.core.PApplet;

public class CoffeeApp extends JFrame {

	private CoffeePanel storeP;
	
	public CoffeeApp(String title) {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(100, 60);
		storeP = new CoffeePanel(this);
		this.add(storeP);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
	}
	
	public static void main(String[] args) {
		new CoffeeApp("Starbox Coffee Shop");
	}

}