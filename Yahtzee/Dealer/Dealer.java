package Dealer;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GOval;

public class Dealer
{
	GImage dealerImage = new GImage("woman-dealer2.png");
	Vector<GOval> circles;
	
	GLabel label = new GLabel("");
	
	boolean IsAppeared = false;
	
	public Dealer()
	{
		dealerImage.scale(0.5);
		label.setFont(new Font("Arial",Font.BOLD,20));
	}
	
	public void Speak(GCanvas canvas,String text)
	{
		if(IsAppeared == false)
		{
			Appear(canvas);
			canvas.add(label);
		}
		
		PutTextInOval(circles.lastElement(),text);
	}
	
	public void Disappear(GCanvas canvas)
	{
		if(IsAppeared==false)
			return;
		
		IsAppeared=false;
		
		TakeTextFromOval(circles.lastElement());
		canvas.remove(label);
		
		for(int circleID = circles.size()-1;circleID>=0;circleID--)
			for (int alpha = 255; alpha >= 0; alpha--) 
			{
				circles.elementAt(circleID).setColor(new Color(0,0,0,alpha));
				
				circles.elementAt(circleID).setFilled(true);
				circles.elementAt(circleID).setFillColor(new Color(255,255,255,alpha));
				
				if(alpha%3==0)
					try { Thread.sleep(1);} catch (InterruptedException e) {}
			}
		
		
		for (int xPixelMoved = 0; xPixelMoved < dealerImage.getWidth()+5; xPixelMoved++) 
		{
			dealerImage.move(1, 0);
			try { Thread.sleep(1);} catch (InterruptedException e) {}
		}
		
		canvas.remove(dealerImage);
		for(GOval circle : circles)
			canvas.remove(circle);
		circles.removeAllElements();
	}
	
	private void Appear(GCanvas canvas)
	{
		IsAppeared = true;
		
		dealerImage.setLocation(
				canvas.getWidth(),
				canvas.getHeight() - dealerImage.getHeight());
		
		canvas.add(dealerImage);
		
		for (int xPixelMoved = 0; xPixelMoved < dealerImage.getWidth(); xPixelMoved++)
		{
			dealerImage.move(-1, 0);
			try { Thread.sleep(1);} catch (InterruptedException e) {}
		}
		
		//======================================================================================//
		
		circles = CreateSpeakOvals(dealerImage);
		for(GOval circle : circles)
			canvas.add(circle);
		
		for(GOval circle : circles)
			for (int alpha = 0; alpha <= 255; alpha++) 
			{
				circle.setColor(new Color(0,0,0,alpha));
				
				circle.setFilled(true);
				circle.setFillColor(new Color(255,255,255,alpha));
				
				if(alpha%3==0)
					try { Thread.sleep(1);} catch (InterruptedException e) {}
			}
	}
	
	//=================================================================================================//

	private void PutTextInOval(GOval oval, String text)
	{
		TakeTextFromOval(oval);
		for (int charID = 0; charID < text.length(); charID++)
		{
			label.setLabel(label.getLabel()+text.charAt(charID));
			label.setLocation(
					oval.getX()+oval.getWidth()*0.5 - label.getWidth()*0.5,
					oval.getY()+oval.getHeight()*0.5 - label.getHeight()*0.5 + label.getHeight());
			try { Thread.sleep(50);} catch (InterruptedException e) {}
		}
	}
	
	private void TakeTextFromOval(GOval oval)
	{
		for (int charID = label.getLabel().length(); charID >= 0; charID--)
		{
			label.setLabel(label.getLabel().substring(0, charID));
			label.setLocation(
					oval.getX()+oval.getWidth()*0.5 - label.getWidth()*0.5,
					oval.getY()+oval.getHeight()*0.5 - label.getHeight()*0.5 + label.getHeight());
			try { Thread.sleep(25);} catch (InterruptedException e) {}
		}
	}
	
	private Vector<GOval> CreateSpeakOvals(GImage dealerImage)
	{
		Vector<GOval> circles = new Vector<GOval>();
		
		// First circle
		int firstCircleRadius = 3;
		circles.add(CreateSpeakOval(
				dealerImage.getX()+dealerImage.getWidth()*0.5-firstCircleRadius,
				dealerImage.getY()+dealerImage.getHeight()*0.66-firstCircleRadius,
				firstCircleRadius,
				firstCircleRadius));
		
		// Middle circles
		for (int circleID = 1; circleID < 4; circleID++)
			circles.add(CreateSpeakOval(
					circles.lastElement().getX()+circles.lastElement().getHeight()/2.,
					circles.lastElement().getY()+circles.lastElement().getHeight()/1.5,
					circles.lastElement().getWidth(),
					circles.lastElement().getHeight()));
		
		// Big circle
		circles.add(CreateSpeakOval(
				circles.lastElement().getX()+circles.lastElement().getHeight()/2.,
				circles.lastElement().getY()+circles.lastElement().getHeight()/1.5,
				circles.lastElement().getWidth()*4,
				circles.lastElement().getHeight()*1.25));
		
		return circles;
	}
	
	private GOval CreateSpeakOval(double x, double y,double x_radius,double y_radius)
	{
		GOval circle = new GOval(x-2*x_radius,y-2*y_radius,x_radius*2,y_radius*2);
		
		circle.setColor(new Color(0,0,0,0));
		
		circle.setFilled(true);
		circle.setFillColor(new Color(255,255,255,0));
				
		return circle;
	}
}