package Other;

import java.util.Vector;

import Game.GameInfo;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class Life
{
	private Vector<GImage> hearts = new Vector<GImage>();
	
	public void DrawHearts(GraphicsProgram graphics, GameInfo info)
	{
		for (GImage heart : hearts)
		 graphics.remove(heart);
		
		hearts.removeAllElements();
		for (int heartID = 0; heartID < info.NTURNS; heartID++) 
			IncreaseLive(graphics, info);
	}
	
	public int DecreaseLive(GraphicsProgram graphics, GameInfo info)
	{
		if(hearts.size()>0)
		{
			graphics.remove(hearts.lastElement());
			hearts.remove(hearts.lastElement());
			info.lives--;
		}
		return info.lives;
	}
	
	public void IncreaseLive(GraphicsProgram graphics, GameInfo info)
	{
		GImage image = new GImage(info.HEART_NAME);
		
		image.setBounds(
				info.HEART_X_OFFSET + info.HEART_X_OFFSET + hearts.size()* (info.HEART_WIDTH + info.HEART_GAP),
				info.HEART_Y_OFFSET,
				info.HEART_WIDTH,
				info.HEART_HEIGHT);
		
		hearts.add(image);
		graphics.add(image);
		
		info.lives++;
	}
}