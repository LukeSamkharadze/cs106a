package Visual;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;

public class Visual 
{
	public static void DisplayIntro(GraphicsProgram graphics)
	{
		GImage yathzeLogo = new GImage("yathze-logo.jpg");
		yathzeLogo.scale(0.1);
		
		ChangeToSpecificAlpha(yathzeLogo,0,new GImage("yathze-logo.jpg"));
		
		graphics.add(yathzeLogo);
		
		for (int alpha = 0; alpha < 255; alpha++) 
		{
			yathzeLogo.scale(1.007);
			yathzeLogo.setLocation(
					graphics.getWidth()/2.-yathzeLogo.getWidth()/2.,
					graphics.getHeight()/2.-yathzeLogo.getHeight()/2.);
			ChangeToSpecificAlpha(yathzeLogo,alpha,new GImage("yathze-logo.jpg"));
			try { Thread.sleep(1);} catch (InterruptedException e) {}
		}
		
		graphics.waitForClick();
		
		try { Thread.sleep(100);} catch (InterruptedException e) {}
		
		for (int alpha = 255; alpha >= 0; alpha--) 
		{
			yathzeLogo.scale(1/1.007);
			yathzeLogo.setLocation(
					graphics.getWidth()/2.-yathzeLogo.getWidth()/2.,
					graphics.getHeight()/2.-yathzeLogo.getHeight()/2.);
			ChangeToSpecificAlpha(yathzeLogo,alpha,new GImage("yathze-logo.jpg"));
		}
		
		try { Thread.sleep(100);} catch (InterruptedException e) {}
	}
	
	public static void ChangeToSpecificAlpha(GImage image, int alpha, GImage oldImage)
	{
		int[][] imagePixelArray = image.getPixelArray();
		int[][] oldImagePixelArray = null;
		
		if(oldImage!=null)
			oldImagePixelArray = oldImage.getPixelArray();
		
		for (int rowID = 0; rowID < imagePixelArray.length; rowID++)
			for (int collumnID = 0; collumnID < imagePixelArray[rowID].length; collumnID++)
				if(alpha <= ((oldImage==null)? 255 : GImage.getAlpha(oldImagePixelArray[rowID][collumnID])))
				imagePixelArray[rowID][collumnID] = GImage.createRGBPixel(
						GImage.getRed(imagePixelArray[rowID][collumnID]),
						GImage.getGreen(imagePixelArray[rowID][collumnID]),
						GImage.getBlue(imagePixelArray[rowID][collumnID]),
						alpha);
		
		GRectangle originalBounds = image.getBounds();
		
		image.setImage(new GImage(imagePixelArray).getImage());
		
		image.setBounds(originalBounds);
	}

	public static void DisplayText(GraphicsProgram graphics, String text,boolean inMiddle)
	{
		GLabel label = new GLabel(text);
		label.setFont(new Font("Arial",Font.BOLD,40));
		label.setLocation(
				(graphics.getWidth()/2. + ((inMiddle)?graphics.getWidth()/2.:graphics.getWidth()*2/3.))/2.-label.getWidth()/2.,
				(0 + graphics.getHeight()/2.)/2.+label.getAscent());
		
		label.setColor(new Color(200,200,200,0));
		graphics.add(label);
		
		for (int i = 0; i <= 255; i++)
		{
			label.setColor(new Color(200,200,200,i));
			try { Thread.sleep(5);} catch (InterruptedException e) {}
		}
	}
}