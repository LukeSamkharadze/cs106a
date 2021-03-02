package Button;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Main.HangmanPro;
import Other.Audio;

public class MenuButton extends Button
{
	public MenuButton(HangmanPro game, double x, double y, double width, double height, String text) 
	{
		super(game, x, y, width, height, text);
		
		mouseListener = new MouseListener()
		{
			Color prevColor;
			
			public void mouseReleased(MouseEvent e)
			{
				if(isEnabled == false)
					return;
				
				rect.setFillColor(prevColor);
			}
			
			public void mousePressed(MouseEvent e) 
			{
				if(isEnabled == false)
					return;
				
				rect.setFillColor(new Color(
						(int)(rect.getFillColor().getRed() - (rect.getFillColor().getRed())/1.5),
						(int)(rect.getFillColor().getGreen() - (rect.getFillColor().getGreen())/1.5),
						(int)(rect.getFillColor().getBlue() - (rect.getFillColor().getBlue())/1.5)));
			}
			
			public void mouseExited(MouseEvent e) 
			{
				rect.setFillColor(prevColor);
			}
			
			public void mouseEntered(MouseEvent e)
			{
				prevColor = rect.getFillColor();
				
				rect.setFillColor(new Color(
						rect.getFillColor().getRed() - (rect.getFillColor().getRed())/2,
						rect.getFillColor().getGreen() - (rect.getFillColor().getGreen())/2,
						rect.getFillColor().getBlue() - (rect.getFillColor().getBlue())/2));
			}
			public void mouseClicked(MouseEvent e)
			{
				if(isEnabled == false)
					return;
				
				Audio.PlayMouseClickSound();
				
				try 
				{			
					  new Thread(() -> { game.GoToMenu(); }).start();
				} 
				catch (Exception ex) { }
			}
		};
	}
}