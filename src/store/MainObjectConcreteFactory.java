/*
 * MainObjectConcreteFactory class:
 *  - Subclass of MainObjectFactory
 *  - Overrides methods from the abstract superclass.
 *  - Concrete factory to initialize the main objects and buttons.
 */

package store;

import static main.CoffeePanel.W_WIDTH;
import static main.CoffeePanel.W_HEIGHT;

import button.Button;
import button.LidDecorButton;
import button.HeartButton;
import button.RestartButton;
import button.StartButton;
import button.StrawberryButton;


public class MainObjectConcreteFactory extends MainObjectFactory{

	@Override
	public MainObject createMainObject(String type) {
		
		MainObject mainObj = null;
		
		if (type == "Cup")
			mainObj = new Cup(W_WIDTH /2+92, W_HEIGHT / 2 + 105, 0.43);
		
		else if (type == "Shaker")
			mainObj = new ShakerBottle(W_WIDTH/2 + 445, W_HEIGHT/2 +90, 0.75);
		
		else if (type == "Spot")
			mainObj = new Spot(W_WIDTH/2 , W_HEIGHT/2 +280, 1);
		
		else if (type == "Espresso")
			mainObj = new Espresso(W_WIDTH/2 - 430, W_HEIGHT/2 + 5, 0.8);
		
		else if (type == "Blender")
			mainObj = new Blender(W_WIDTH/2 -200, W_HEIGHT/2 + 5, 0.5);
		
		else if (type == "Milk")
			mainObj = new Milk(W_WIDTH/2 + 245, W_HEIGHT/2 - 243, 1);
		
		else if (type == "Strawberry")
			mainObj = new Strawberry(W_WIDTH/2 + 320 , W_HEIGHT/2 -90 , 1);
		
		else if (type == "Scoup")
			mainObj = new Scoup(W_WIDTH/2 +110, W_HEIGHT/2 -210, 0.4);
		
		else if (type == "Caramel")
			mainObj = new Caramel(W_WIDTH/2 +355, W_HEIGHT/2 -242, 0.5);
		
		else if (type == "Chocolate")
			mainObj = new Chocolate(W_WIDTH/2 +196, W_HEIGHT/2 -82, 0.5);
		
		else if (type == "Straw")
			mainObj = new Straw(W_WIDTH/2 +292, W_HEIGHT/2 +92, 0.4);
		
		return mainObj;
	}
	
	@Override
	public Button createButton(String type) {
		
		Button button = null;
		
		if (type == "Start Button")
			button = new StartButton(W_WIDTH /2 + 370, W_HEIGHT / 2 + 160, 1);
		
		else if (type == "Restart Button")
			button = new RestartButton(W_WIDTH /2 + 420, W_HEIGHT / 2 + 180, 0.7);
		
		else if (type == "Heart Button")
			button = new HeartButton(W_WIDTH / 2 + 340, W_HEIGHT / 2 + 80, 1);
		
		else if (type == "Strawberry Button")
			button = new StrawberryButton(W_WIDTH / 2 + 200, W_HEIGHT / 2 - 10, 1);
		
		else if (type == "Lid Decor Button")
			button = new LidDecorButton(W_WIDTH / 2 + 55, W_HEIGHT / 2 + 66, 1);
	
		return button;
	}


}