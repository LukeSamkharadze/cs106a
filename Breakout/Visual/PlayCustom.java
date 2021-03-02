package Visual;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Main.BreakoutPro;
import Other.Speed;
import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class PlayCustom extends Button
{
	public PlayCustom(BreakoutPro game,GImage image, Speed speed)
	{
		super(game,image, speed);
	}

	public MouseListener listener = new MouseListener()
	{
		public void mouseReleased(MouseEvent e) {}
		
		public void mousePressed(MouseEvent e) {}
		
		public void mouseExited(MouseEvent e) 
		{
			if(isClicked)
				return;
			
			GRectangle bounds = image.getBounds();
			game.remove(image);
			
			image = new GImage("play-custom1.png");
			image.setBounds(bounds);
			game.add(image);
			
			image.addMouseListener(this);
		}
		
		public void mouseEntered(MouseEvent e)
		{			
			if(isClicked)
			return;
			
			GRectangle bounds = image.getBounds();
			game.remove(image);
			
			image = new GImage("play-custom2.png");
			image.setBounds(bounds);
			game.add(image);
			
			image.addMouseListener(this);
		}
		
		public void mouseClicked(MouseEvent e)
		{
			if(isClicked)
				return;
			isClicked = true;
			
			game.audio.PlayMouseClickSound();
			
			new Thread(() -> 
			{
				game.StartBuilding();
			}).start();
		}
	};
}
