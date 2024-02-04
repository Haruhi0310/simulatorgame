package main;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import button.HeartButton;
import button.LidDecorButton;
import button.RestartButton;
import button.StartButton;
import button.StrawberryButton;
import decorator.BaseCup;
import decorator.CupInterface;
import decorator.CupSticker;
import decorator.LidDecorator;
import main.CoffeePanel.MyMouseListener;
import main.CoffeePanel.MyMouseMotionListener;
import store.ShakerBottle;
import store.Smoke;
import store.Spot;
import store.Espresso;
import store.Instruction;
import store.MainObjectConcreteFactory;
import store.MainObjectFactory;
import store.Milk;
import store.Blender;
import store.Cup;
import store.Store;
import store.Strawberry;
import store.Scoup;
import store.Caramel;
import store.Chocolate;
import store.Straw;
import store.DecorBoard;
import store.DecorBoardMain;
import store.DecorBoardNail;
import processing.core.*;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import util.MinimHelper;
//import minim.MinimHelper;

public class CoffeePanel extends JPanel implements ActionListener {
	public final static int W_WIDTH = 1200;
	public final static int W_HEIGHT = 760;

	// variables for holding mouse position
	private double mouseX;
	private double mouseY;

	private int state = -2;

	private Store store;
	
	private MainObjectFactory objProducer;
	
	//buttons fields for stickers, start and restart buttons
	private StartButton startButton;
	private RestartButton restartButton;
	private HeartButton heartButton;
	private StrawberryButton strawberryButton;
	private LidDecorButton lidDecorButton;
	
	private ShakerBottle shaker;
	private Spot spot;
	private Espresso espresso;
	private Blender blender;
	private Instruction instructBox;
	private Milk milk;
	private Strawberry strawberry;
	private Cup cup;
	private Scoup scoup;
	private Caramel caramel;
	private Chocolate chocolate;
	private Straw straw;
	
	
	private Smoke smoke;
	
	private ArrayList<DecorBoard> decorBoardCompos; // Declare ArrayList for inclusion polymorphism

	private CupInterface baseCup; // field for the base cup and decorator patterns

	
	//variables for smoke timer
	private int bakeTimer = 0;
	private boolean halfDone = false;
	
	//variables for cup shaking timer
	private int cupTimer = 0;
	private boolean cupDone = false;
	
	private int boardSwTimer = 120; // timer for looping the board swinging sound
	private boolean boardSwStart = false; // boolean for looping the board swinging sound
	
	//sounds
	private Minim minim;
	private AudioPlayer bgMusic, click, fridgeClose, fridgeOpen, hit, shaking, coffeePour, taskDone, stiringStraw,
	scoop, getIce, win, kitchenAmbient, boardSwing, lid, drop;
	
	private int kitchenAmTimer = 0; // timer for looping the kitchen ambient sound (water pouring, washing, etc)
	private boolean kitchenAmStart = false; // boolean for looping the kitchen ambient sound (water pouring,
											// washing, etc)
	
	private JFrame frame;
	

	private Timer timer;
	
	CoffeePanel(JFrame frame) {
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));
		objProducer = new MainObjectConcreteFactory();
		store = new Store("assets/store.png");
		
		//buttons
		startButton = (StartButton) objProducer.createButton("Start Button");
		restartButton = (RestartButton) objProducer.createButton("Restart Button");
		heartButton = (HeartButton) objProducer.createButton("Heart Button");
		strawberryButton = (StrawberryButton) objProducer.createButton("Strawberry Button");
		lidDecorButton = (LidDecorButton) objProducer.createButton("Lid Decor Button");
		
		//objects
		cup = (Cup) objProducer.createMainObject("Cup");
		shaker = (ShakerBottle) objProducer.createMainObject("Shaker");
		spot = (Spot) objProducer.createMainObject("Spot");
		espresso = (Espresso) objProducer.createMainObject("Espresso");
		blender = (Blender) objProducer.createMainObject("Blender");
		milk = (Milk) objProducer.createMainObject("Milk");
		strawberry = (Strawberry) objProducer.createMainObject("Strawberry");
		scoup = (Scoup) objProducer.createMainObject("Scoup");
		caramel = (Caramel) objProducer.createMainObject("Caramel");
		chocolate = (Chocolate) objProducer.createMainObject("Chocolate");
		straw = (Straw) objProducer.createMainObject("Straw");
		
		instructBox = new Instruction(10, 15, 648, 125);//
		smoke = new Smoke((float) blender.getX() - 50, (float) blender.getY()-50, 50, 230);//
		
		this.decorBoardCompos = new ArrayList<DecorBoard>(); // inclusion polymorphism
		decorBoardCompos.add(new DecorBoardMain(W_WIDTH / 2 + 535, 32, 1)); // inclusion polymorphism
		decorBoardCompos.add(new DecorBoardNail(W_WIDTH / 2 + 530, 30, 1)); // inclusion polymorphism
		
		minim = new Minim(new MinimHelper());
		bgMusic = minim.loadFile("background.mp3"); // background music
		bgMusic.loop();
		
		click = minim.loadFile("clickbutton.wav"); // sound for clicking button
		shaking = minim.loadFile("shaking.mp3"); // shaking the bottle sound
		coffeePour = minim.loadFile("pouring.wav"); // sound for pouring coffee
		scoop = minim.loadFile("scoop.mp3"); // sound for scooping the strawberry, chocolate
		lid = minim.loadFile("lid.wav");
		drop = minim.loadFile("drop.mp3");
		kitchenAmbient = minim.loadFile("kitchen.wav"); // ambient kitchen sound
		taskDone = minim.loadFile("finishtask.wav");
		boardSwing = minim.loadFile("boardswing.wav"); // board swinging sound
		
		
		timer = new Timer(30, this);
		timer.start();
		
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		
		MyMouseMotionListener mml = new MyMouseMotionListener();
		addMouseMotionListener(mml);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//Start
		if (state == -2) {  
		store.drawStore(g2);
		startButton.drawButton(g2);
		}
		
		if (state == -1) { //measure close
		store.drawStore(g2);
		drawFullBoard(g2);
		straw.drawObject(g2);
		spot.drawObject(g2);
		shaker.drawObject(g2);
		espresso.drawObject(g2);
		blender.drawObject(g2);
		instructBox.drawInstruction(g2, -1);
		milk.drawObject(g2);
		strawberry.drawObject(g2);
		cup.drawObject(g2);
		scoup.drawObject(g2);
		caramel.drawObject(g2);
		chocolate.drawObject(g2);
		}
		
		if (state == 0) { //measure open
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 0);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			scoup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			}
		
		if (state == 1) { // coffee
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 1);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			scoup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			}
		
		if (state == 2) { //milk
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 2);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			scoup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			}
		
		if (state == 3) { //strawberry cup
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 3);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 4) { //strawberry
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 4);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 5) { //strawberry
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 5);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			//smoke.drawSmoke(g2);
			cup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 6) { //caramel 
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 6);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 7) { //chocolate scoup
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 7);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			//smoke.drawSmoke(g2);
			//restartButton.drawButton(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 8) { //chocolate
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 8);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			//smoke.drawSmoke(g2);
			//restartButton.drawButton(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 9) { //chocolate
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 9);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			//smoke.drawSmoke(g2);
			//restartButton.drawButton(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 10) { //chocolate
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 10);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			smoke.drawSmoke(g2);
			//restartButton.drawButton(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 11) { //chocolate
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 11);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			shaker.drawObject(g2);
			cup.drawObject(g2);
			//smoke.drawSmoke(g2);
			//restartButton.drawButton(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 12) { //cup-done
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 12);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			//shaker.drawObject(g2);
			cup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 13) { //cup-straw
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 13);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			//shaker.drawObject(g2);
			cup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 14) { //cup-shaking
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 14);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			//shaker.drawObject(g2);
			cup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}
		
		if (state == 15) { //click the cup to start the decoration
			store.drawStore(g2);
			drawFullBoard(g2);
			spot.drawObject(g2);
			straw.drawObject(g2);
			espresso.drawObject(g2);
			blender.drawObject(g2);
			instructBox.drawInstruction(g2, 15);
			milk.drawObject(g2);
			strawberry.drawObject(g2);
			//shaker.drawObject(g2);
			cup.drawObject(g2);
			caramel.drawObject(g2);
			chocolate.drawObject(g2);
			scoup.drawObject(g2);
			}

		if (state == 16) { // adding stickers to the cup and decorator for the lid
			store.drawStore(g2);
			instructBox.drawInstruction(g2, 16);
			heartButton.drawButton(g2);
			strawberryButton.drawButton(g2);
			lidDecorButton.drawButton(g2);
			baseCup.decoration(g2);
			//restartButton.drawButton(g2);
			
			}
		
		if (state == 17) { // endScreen
			store.drawStore(g2);
			//instructBox.drawInstruction(g2, 7);
//			heartButton.drawButton(g2);
//			strawberryButton.drawButton(g2);
//			lidDecorButton.drawButton(g2);
//			baseCup.decoration(g2);
			restartButton.drawButton(g2);
			
			}
		


	}
	
	// method for drawing the decor board (inclusion polymorphism)
	public void drawFullBoard(Graphics2D g2) {
		for (int i = 0; i < decorBoardCompos.size(); i++) {
			decorBoardCompos.get(i).drawBoard(g2);
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// animation for the board to swing (inclusion polymorphism)
		for (int i = 0; i < decorBoardCompos.size(); i++) {
			DecorBoard db = decorBoardCompos.get(i);

			if (db instanceof DecorBoardMain) {
				DecorBoardMain dbm = (DecorBoardMain) db;
				dbm.move();
			}
		}
		
		
		
		//timer for smoke of the blender
		if (state == 10) {
			smoke.setWidth(bakeTimer / 3);
			smoke.setHeight(bakeTimer / 4);
			if (!halfDone) {
				bakeTimer++;
				if (bakeTimer >= 180) {
					halfDone = true;
					bakeTimer = 180;
				}
			} else {
				bakeTimer--;
				if (bakeTimer <= 0) {
					//shaking.pause();
					state = 11;
					blender.setImg(2);
				}
			}
		}
		
		// timer for bubble tea cup to be shaken
		if (state == 14) {
			cupTimer++;
			cup.move();
			if (cupTimer >= 120) {
				cupDone = true;
				cupTimer = 120;
//				stiringStraw.pause();
//				taskDone.play(0);
				shaking.pause();
			}

			if (cupDone) {
				taskDone.play(0);
				state = 15;
				cup.setImg(3);
			}
		}
		
		// timer for looped kitchen ambient
		if (state != 17) {
			if (kitchenAmTimer <= 300)
				kitchenAmTimer++;
			if (kitchenAmTimer == 300) {
				kitchenAmStart = true;
				kitchenAmTimer = 0;
			}
			if (kitchenAmStart) {
				kitchenAmbient.play(0);
				kitchenAmStart = false;
			}

		}
		
		if (state != 17) { //sound for the swinging board
			if (boardSwTimer <= 180)
				boardSwTimer++;
			if (boardSwTimer == 180) {
				boardSwStart = true;
				boardSwTimer = 0;
			}
			if (boardSwStart) {
				boardSwing.play(0);
				boardSwStart = false;
			}

		}
		
		
		repaint();

	}

	public class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			
			if (state == -2 && startButton.clicked(mouseX, mouseY)) {
				store = new Store("assets/storebackground.png");
				state = -1;
				click.play(0);
			}
			
			else if (state == 1 && milk.clicked(mouseX, mouseY)) {
				drop.play(0);
				shaker.setImg(3);
				state = 2;
			}
			
			else if (state == 2 && strawberry.clicked(mouseX, mouseY)) {
				lid.play(0);
				strawberry.setImg(1);
				state = 3;
			}
			
			else if (state == 5 && caramel.clicked(mouseX, mouseY)) {
				drop.play(0);
				shaker.setImg(5);
				state = 6;
			}
			
			else if (state == 9 && blender.clicked(mouseX, mouseY)) {
				state = 10;
			}
			
			else if (state == 12 && straw.clicked(mouseX, mouseY)) {
				cup.setImg(2);
				state = 13;
			}
			
			else if (state == 13 && cup.clicked(mouseX, mouseY)) {
				shaking.play(0);
				//cup.setImg(2);
				state = 14;
			}
			
			// users adding stickers to the cup which was created by using decorator design
			else if (state == 15 && cup.clicked(mouseX, mouseY)) {
				click.play(0);
				store = new Store("assets/stickerbackground.png");
				baseCup = new BaseCup(230, 400, 0.8);
				state = 16;
				
				// kitchenAmbient.pause();
				// boardSwing.pause();
			}
			
			// users choose heart sticker by clicking its' button
			if (state == 16 && heartButton.clicked(mouseX, mouseY)) {
				click.play(0);
				baseCup = new CupSticker(baseCup, 285, 380, 0.4, 2);
			}

			// users choose strawberry sticker by clicking its' button
			if (state == 16 && strawberryButton.clicked(mouseX, mouseY)) {
				click.play(0);
				baseCup = new CupSticker(baseCup, 190, 580, 0.6, 1);
			}

			// users choose lid decoration by clicking its' button
			if (state == 16 && lidDecorButton.clicked(mouseX, mouseY)) {
				click.play(0);
				baseCup = new LidDecorator(baseCup, 290, 250, 0.7);
			}
			
			// finish the drink by clicking the decorated cup
			else if (state == 16 && baseCup.clicked(mouseX, mouseY)) {
				bgMusic.pause();
				//win.play(1000);
				taskDone.play(0);
				state = 17;
				store = new Store("assets/endscreen.png");
			}

			if (state == 17 && restartButton.clicked(mouseX, mouseY)) {
				click.play(0);
				bgMusic.pause();
				//win.pause();
				if(frame != null)
					frame.dispose();
					frame = new CoffeeApp("Starbox Coffee Shop");
			}
			
			
		}
	}
	
	public class MyMouseMotionListener extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			if (state == -1) {
				if (shaker.clicked(e.getX(), e.getY())) {
					shaker.setX(e.getX());
					shaker.setY(e.getY());
				}
				
				if (shaker.hit(spot)) {
					state = 0; //0
					///
					shaker.setImg(1);
					shaker.setX(spot.getX());
					shaker.setY(spot.getY());
					///System.out.println("hit");
				}
			}
			
			if (state == 0) {
				if (shaker.clicked(e.getX(), e.getY())) {
					shaker.setX(e.getX());
					shaker.setY(e.getY());
				}

				if (shaker.hit(espresso)) {
					coffeePour.play(1500);
					state = 1;
					shaker.setImg(2);
				}
			}
			
			if (state == 1) {
				if (shaker.clicked(e.getX(), e.getY())) {
					shaker.setX(e.getX());
					shaker.setY(e.getY());
				}
			}
			
			if (state == 3) {
				if (scoup.clicked(e.getX(), e.getY())) {
					scoup.setX(e.getX());
					scoup.setY(e.getY());
					scoup.setImg(1);
				}
				
				if (scoup.hit(strawberry)) {
					scoop.play(0);
					scoup.setImg(2);
					state = 4;
					
				}
			}
				
			
			if (state == 4) {
				if (scoup.clicked(e.getX(), e.getY())) {
					scoup.setX(e.getX());
					scoup.setY(e.getY());
				}

				if (scoup.hit(shaker)) {
					drop.play(0);
					shaker.setImg(4);
					state = 5;
					scoup.setImg(0);
					scoup.setX(W_WIDTH / 2 + 110);
					scoup.setY(W_HEIGHT / 2 - 210);
				}
			}
			
			if (state == 6) {
				if (scoup.clicked(e.getX(), e.getY())) {
					scoup.setX(e.getX());
					scoup.setY(e.getY());
					scoup.setImg(1);
				}
				
				if (scoup.hit(chocolate)) {
					scoop.play(0);
					scoup.setImg(3);
					state = 7;
					
				}
			}
			
			if (state == 7) {
				if (scoup.clicked(e.getX(), e.getY())) {
					scoup.setX(e.getX());
					scoup.setY(e.getY());
				}

				if (scoup.hit(shaker)) {
					drop.play(0);
					shaker.setImg(6);
					state = 8;
					scoup.setImg(0);
					scoup.setX(W_WIDTH / 2 + 110);
					scoup.setY(W_HEIGHT / 2 - 210);
				}
			}
			
			if (state == 8) {
				if (shaker.clicked(e.getX(), e.getY())) {
					shaker.setX(e.getX());
					shaker.setY(e.getY());
				}
				
				if (shaker.hit(blender)) {
				state = 9;
				coffeePour.play(1500);
				blender.setImg(1);
				shaker.setImg(1);
				shaker.setX(spot.getX() + 20);
				shaker.setY(spot.getY() - 20);
				}
			}
			
			
			if (state == 11) {
				if (cup.clicked(e.getX(), e.getY())) {
					cup.setX(e.getX());
					cup.setY(e.getY());
				}
				
				if (cup.hit(blender)) {
					state = 12;
					coffeePour.play(1500);
					cup.setImg(1);
					cup.setX(spot.getX() + 20);
					cup.setY(spot.getY() - 50);
					blender.setImg(0);
				}
			}
//			
			
				
			}
		}
}
