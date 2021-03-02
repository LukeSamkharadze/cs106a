package Drawer;

import java.util.Vector;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class MariDrawer extends Drawer
{
	private GraphicsProgram graphics;
	
	private double scaleX = 1;
	private double scaleY = 1;
	
	private Vector<GObject> objectsCreated = new Vector<GObject>();
	
	public MariDrawer(GraphicsProgram graphics)
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
		GImage image = new GImage("mari-gallow.png");
		image.setSize(200*scaleX, 300*scaleY);
		
		image.setVisible(false);
		graphics.add(image);
		image.sendToBack();
		image.setVisible(true);
		
		objectsCreated.add(image);
	}

	//==============================//
	
	// 754
	// 472
	
	private void DrawHead()
	{
		GImage image = new GImage("mari-head.png");
		image.setBounds(120 * scaleX, 60*scaleY, 75 * scaleX, 75 * scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}
	
	private void DrawShoulders()
	{
		GImage image = new GImage("mari-shoulders.jpg");
		image.setBounds(130* scaleX, 135*scaleY, 50* scaleX, 10*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}

	private void DrawBody()
	{
		GImage image = new GImage("mari-body.png");
		image.setBounds(130* scaleX, 145*scaleY, 50* scaleX, 50*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}
	
	private void DrawFirstHand()
	{
		GImage image = new GImage("mari-hand-1.png");
		image.setBounds(90* scaleX, 135*scaleY, 40* scaleX, 80*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}

	private void DrawSecondHand()
	{
		GImage image = new GImage("mari-hand-2.png");
		image.setBounds(180* scaleX, 135*scaleY, 40* scaleX, 80*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}
	
	private void DrawHips()
	{
		GImage image = new GImage("mari-hips.png");
		image.setBounds(125* scaleX, 195*scaleY, 60* scaleX, 15*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}

	private void DrawFirstLeg()
	{
		GImage image = new GImage("mari-leg-1.png");
		image.setBounds(119* scaleX, 210*scaleY, 35* scaleX, 40*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}
	
	private void DrawSecondLeg()
	{
		GImage image = new GImage("mari-leg-2.png");
		image.setBounds(157* scaleX, 210*scaleY, 35* scaleX, 40*scaleY);
		
		graphics.add(image);
		objectsCreated.add(image);
	}
}
