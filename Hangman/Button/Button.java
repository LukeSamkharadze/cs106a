package Button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import Main.HangmanPro;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public abstract class Button
{
	public HangmanPro game;
	
	public GRect rect;
	public GLabel label;
	
	public boolean isEnabled = true;
	
	public MouseListener mouseListener;
	
 	public Button(HangmanPro game, double x,double y, double width, double height, String text)
	{
 		this.game = game;
 		
		rect = new GRect(x,y,width,height);
		rect.setFilled(true);
		rect.setFillColor(Color.WHITE);
		
		label = new GLabel(text);
		label.setFont(new Font("Arial",Font.BOLD,20));
		label.setLocation((x+x+width)/2.-label.getWidth()/2.,(y+y+height)/2.+label.getAscent()/2. );
	}
}
