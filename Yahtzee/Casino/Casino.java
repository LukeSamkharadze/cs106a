package Casino;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Visual.Visual;
import acm.graphics.GImage;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;

public class Casino 
{
	GImage casino;
	GImage casinoDoor;
	
	boolean isDoorPressed = false;
	
	public void Appear(GraphicsProgram graphics)
	{
		casino = new GImage("casino-entrance.jpg");
		casino.setSize(graphics.getWidth(), graphics.getHeight());
		
		Visual.ChangeToSpecificAlpha(casino, 0, null);
		
		graphics.add(casino);
		
		for (int alpha = 0; alpha <= 255; alpha++) 
		{
			Visual.ChangeToSpecificAlpha(casino, alpha, null);
			try { Thread.sleep(5);} catch (InterruptedException e) {}
		}
		
		graphics.setBackground(new Color(0, 140, 0));
		
		AddDoor(graphics);
		
		while(true)
		{
			try { Thread.sleep(10);} catch (InterruptedException e) {}
			
			if(isDoorPressed)
				break;
		}
		
		Disappear(graphics);
	}
	
	public void KickOut(GraphicsProgram graphics)
	{
		graphics.removeAll();
		graphics.add(casino);
		
		for (int i = 0; i <= 255; i++)
		{
			Visual.ChangeToSpecificAlpha(casino, (1*i<255)?1*i:255, null);
			Scale(casino, 1/1.005);
			try { Thread.sleep(15);} catch (InterruptedException e) {}
		}
	}
	
	private void AddDoor(GraphicsProgram graphics)
	{
		casinoDoor = new GImage("casino-door.jpg");
		casinoDoor.setLocation(238,200);
		casinoDoor.scale(0.95,0.77);
		
		casinoDoor.addMouseListener(mouseListener);
		
		graphics.add(casinoDoor);
	}
	
	private void Disappear(GraphicsProgram graphics)
	{
		graphics.remove(casinoDoor);
		for (int i = 255; i >= 0; i--)
		{
			Visual.ChangeToSpecificAlpha(casino, (255-1*(255-i) > 0)?(255-1*(255-i)):0, null);
			Scale(casino, 1.005);
			try { Thread.sleep(20);} catch (InterruptedException e) {}
		}
		
		graphics.remove(casino);
	}
	
	//=======================================================================================//
	
	private void WhitenCasinoDoor()
	{
		int[][] imagePixelArray = casinoDoor.getPixelArray();
		
		for (int rowID = 0; rowID < imagePixelArray.length; rowID++)
			for (int collumnID = 0; collumnID < imagePixelArray[rowID].length; collumnID++)
				imagePixelArray[rowID][collumnID] = GImage.createRGBPixel(
						(int)(GImage.getRed(imagePixelArray[rowID][collumnID])*3/4.+255*0.25),
						(int)(GImage.getGreen(imagePixelArray[rowID][collumnID])*3/4.+255*0.25),
						(int)(GImage.getBlue(imagePixelArray[rowID][collumnID])*3/4.+255*0.25));
		
		GRectangle originalBounds = casinoDoor.getBounds();
		
		casinoDoor.setImage(new GImage(imagePixelArray).getImage());

		casinoDoor.setBounds(originalBounds);
	}
	
	private void UndoCasinoDoor()
	{
		GRectangle originalBounds = casinoDoor.getBounds();
		
		casinoDoor.setImage(new GImage("casino-door.jpg").getImage());
		
		casinoDoor.setBounds(originalBounds);
	}

	private void Scale(GImage image,double scaleValue)
	{
		double oldCenterX = image.getX()+image.getWidth()/2.;
		double oldCenterY = image.getY()+image.getHeight()/2.;
		
		image.scale(scaleValue);
		image.setLocation(oldCenterX-image.getWidth()/2.,oldCenterY-image.getHeight()/2.);
	}
	
	MouseListener mouseListener = new MouseListener()
	{
		public void mouseReleased(MouseEvent e)
		{
			
		}
		
		public void mousePressed(MouseEvent e) 
		{
			
		}
		
		public void mouseExited(MouseEvent e) 
		{
			UndoCasinoDoor();
			Scale(casinoDoor,1/1.25);
		}
		
		public void mouseEntered(MouseEvent e)
		{
			WhitenCasinoDoor();
			Scale(casinoDoor,1.25);
		}
		
		public void mouseClicked(MouseEvent e)
		{
			UndoCasinoDoor();
			Scale(casinoDoor,1/1.25);
			isDoorPressed = true;
		}
	};
}