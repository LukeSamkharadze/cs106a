package Drawer;
import java.util.Vector;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class ScaryDrawer extends Drawer
{
	private GraphicsProgram graphics;
	
	private Vector<GObject> objectsCreated = new Vector<GObject>();
	
	private double scaleX = 1;
	private double scaleY = 1;
	
	public ScaryDrawer(GraphicsProgram graphics)
	{
		this.graphics = graphics;
	}
	
	public void Reset() 
	{
		scaleX = graphics.getWidth()/754.;
		scaleY = graphics.getHeight()/472.;
		
		for (int objectCreatedID = 0; objectCreatedID < objectsCreated.size(); objectCreatedID++)
			graphics.remove(objectsCreated.elementAt(objectCreatedID));

		objectsCreated.removeAllElements();
		
		DrawGallow();
	}

	public void AddNewPart(int wrongGuessCounter) 
	{
		switch (wrongGuessCounter) 
		{
			case 1: DrawHead(); break;
			case 2: DrawShoulders(); break;
			case 3: DrawBody(); break;
			case 4: DrawFirstHand(); break;
			case 5: DrawSecondHand(); break;
			case 6: DrawHips(); break;
			case 7: DrawFirstLeg(); break;
			case 8: DrawSecondLeg(); break;
		}
	}
	
	//==============================//
	
	private void DrawGallow()
	{
		GImage image = new GImage("scary-gallow.png");
		image.setSize(200*scaleX, 300*scaleY);
		
		image.setVisible(false);
		graphics.add(image);
		image.sendToBack();
		image.setVisible(true);
		
		objectsCreated.add(image);
	}

	//==============================//
	
	private void DrawHead()
	{
		GImage image = new GImage("scary-head.jpg");
		image.setBounds(135*scaleX, 85*scaleY, 40*scaleX, 40*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}
	
	private void DrawShoulders()
	{
		GImage image = new GImage("scary-shoulders.jpg");
		image.setBounds(105*scaleX, 125*scaleY, 90*scaleX, 25*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}

	private void DrawBody()
	{
		GImage image = new GImage("scary-body.jpg");
		image.setBounds(120*scaleX, 150*scaleY, 60*scaleX, 75*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}
	
	private void DrawFirstHand()
	{
		GImage image = new GImage("scary-hand-1.jpg");
		image.setBounds(100*scaleX, 150*scaleY, 20*scaleX, 60*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}

	private void DrawSecondHand()
	{
		GImage image = new GImage("scary-hand-2.jpg");
		image.setBounds(180*scaleX, 150*scaleY, 20*scaleX, 60*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}
	
	private void DrawHips()
	{
		GImage image = new GImage("scary-hips.jpg");
		image.setBounds(120*scaleX, 225*scaleY, 60*scaleX, 15*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}

	private void DrawFirstLeg()
	{
		GImage image = new GImage("scary-leg-1.jpg");
		image.setBounds(120*scaleX, 240*scaleY, 30*scaleX, 30*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}
	
	private void DrawSecondLeg()
	{
		GImage image = new GImage("scary-leg-2.jpg");
		image.setBounds(150*scaleX, 240*scaleY, 30*scaleX, 30*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}
}