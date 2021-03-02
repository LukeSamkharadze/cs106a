package Visual;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Main.BreakoutPro;
import Other.Speed;
import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class StartCustomGame extends Button
{
	public StartCustomGame(BreakoutPro game,GImage image, Speed speed)
	{
		super(game,image, speed);
	}

	public MouseListener listener = new MouseListener()
	{
		public void mouseReleased(MouseEvent e) {}
		
		public void mousePressed(MouseEvent e) {}
		
		public void mouseExited(MouseEvent e) 
		{
			GRectangle bounds = image.getBounds();
			game.remove(image);
			
			image = new GImage("start-custom1.png");
			image.setBounds(bounds);
			game.add(image);
			
			image.addMouseListener(this);
		}
		
		public void mouseEntered(MouseEvent e)
		{
			GRectangle bounds = image.getBounds();
			game.remove(image);
			
			image = new GImage("start-custom2.png");
			image.setBounds(bounds);
			game.add(image);
			
			image.addMouseListener(this);
		}
		
		public void mouseClicked(MouseEvent e)
		{
			game.isBuilding=false;
			
			game.audio.PlayMouseClickSound();
			
			image.move(1000, 1000);
			game.remove(image);
			
			new Thread(() -> 
			{
				game.StartCustom();
			}).start();
		}
	};
}
