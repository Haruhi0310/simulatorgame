package store;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Instruction {
	private double xPos;
	private double yPos;
	private double w;
	private double h;
	
	public Instruction(double x, double y, double w, double h) {
		xPos = x;
		yPos = y;
		this.w = w;
		this.h = h;
	}
	
	public void drawInstruction(Graphics2D g2, int iState) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.setColor(Color.GRAY);
		g2.fill(new Rectangle2D.Double(xPos, yPos, w, h));
		g2.setColor(Color.WHITE);
		g2.fill(new Rectangle2D.Double(xPos + 2, yPos +2, w - 4, h - 4));
		
		String instruct = null;
		String instruct1 = null;
		String instruct2 = null;
		
		if (iState == -1) {
			instruct = "Step 1. Let's grab the measure cup first! First of all,";
			instruct1 = "put the measure cup on the blue spot on the table";
			instruct2 = "by clicking and dragging it to the table.";
			
		} else if (iState == 0) {
			instruct = "Step 2. ";
			instruct1 = "Coffee must have espresso! Now pour espresso by";
			instruct2 = "dragging the cup to the espresso machine.";
			
		} else if (iState == 1) {
			instruct = "Step 3. ";
			instruct1 = "Put the cup on the table again. The second ingredient";
			instruct2 = "is milk. Click 'Milk' pack on the wall.";
			
		} else if (iState == 2) {
			instruct = "Step 4. ";
			instruct1 = "Add straawberries as a topping!";
			instruct2= "Click 'Strawberry' bin to open the lid.";
			
		} else if (iState == 3) {
			instruct = "Step 5. ";
//			instruct1 = "All the ingredients are now assembled.";
//			instruct2 = "Drag the cup to the blender machine.";
			instruct1 = "Click the ladle on the wall and drag it to the";
			instruct2 = "Strawberry bin to scoup it.";
		
		} else if (iState == 4) {
			instruct = "Step 6. ";
//			instruct1 = "Time to shake it up! Quickly click on the blender.";
			instruct1 = "Drag the ladle to the measure cup to add strawberries.";
		
		} else if (iState == 5) {
			instruct = "Step 7. ";
//			instruct1 = "Let's wait a bit while the coffee is being shaken!";
			instruct1 = "Now, add caramel by clicking the caramel syrup.";
			
		} else if (iState == 6) {
			instruct = "Step 8. ";
//			instruct1 = "Well done! Your coffee base is now ready. Drag the";
//			instruct2 = "coffee cup to the blender and finish your drink!";
			instruct1 = "Click the ladle on the wall again and drag it to the";
			instruct2 = "chocolate bowl to scoup it.";
		} else if (iState == 7) {
			instruct = "Step 9. ";
//			instruct1 = "Now Your drink is ready!!!";
//			instruct2 = "If you wanna restart the game, clike 'Restart' button.";
			instruct1 = "Drag the ladle to the measure cup to add chocolates.";
		} else if (iState == 8) {
			instruct = "Step 10. ";
			instruct1 = "All the ingredients are now assembled.";
			instruct2 = "Drag the cup to the blender machine.";
			
		} else if (iState == 9) {
			instruct = "Step 11. ";
			instruct1 = "Time to shake it up! Quickly click on the blender.";
			
		} else if (iState == 10) {
			instruct = "Step 12. ";
			instruct1 = "Let's wait a bit while the coffee is being shaken!";
			
		} else if (iState == 11) {
			instruct = "Step 13. ";
			instruct1 = "Well done! Your coffee base is now ready. Drag the";
			instruct2 = "coffee cup to the blender.";
			
		} else if (iState == 12) {
			instruct = "Step 14.  ";
			instruct1 = "We still need a straw.";
			instruct2= "Click the 'straw' cup to add it!";
			
		} else if (iState == 13) {
			instruct = "Step 15. One last step to finish your drink!";
			instruct1 = "Shake the cup to mix the toppings by clicking the cup";
			instruct2 = "and wait for a while.";
			
		} else if (iState == 14) {
			instruct = "Step 15. One last step to finish your drink!";
			instruct1 = "Shake the cup to mix the toppings by clicking the cup";
			instruct2 = "and wait for a while.";
			
		} else if (iState == 15) {
			instruct = "Step 16. ";
			instruct1 = "Well done! Your coffee is now ready! It's time";
			instruct2= "for decoration! Click the cup to go next step.";
			
		} else if (iState == 16) {
			instruct = "Step 17. ";
			instruct1 = "Click on the stickers you like to make your cup";
			instruct2= "stunning! Then click the cup to enjoy your drink :)";
			
		}
		
		//else if (iState == 7) {
			//instruct = "Step 8. After the topping, ice, straw, lid, your bubble tea is completed.";
			//instruct1 = "Click on the shaker (for now) to finish your drink!";
		//} 
		
		
		Font font = new Font("Courier", Font.BOLD, 18);
		g2.setFont(font);
		g2.setColor(Color.DARK_GRAY);

		g2.drawString(instruct, (int) xPos + 20, (int) yPos + 36);
		if (instruct1 != null) g2.drawString(instruct1, (int) xPos + 20, (int) yPos + 66);
		if (instruct2 != null) g2.drawString(instruct2, (int) xPos + 20, (int) yPos + 96);
		g2.setTransform(at);
	}

}