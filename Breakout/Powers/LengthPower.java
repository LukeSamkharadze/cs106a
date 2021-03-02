package Powers;

import Game.GameEngine;
import Game.GameInfo;
import Game.GameObjects;
import Game.GameSetup;
import Main.BreakoutPro;
import Objects.Ball;
import Objects.Paddle;
import Other.Audio;
import Other.Life;
import Other.Speed;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class LengthPower extends Power
{
	private static final String IMAGE_NAME = "power.png";
	
	public LengthPower(GameInfo info)
	{
		super(null,null);
		GImage image = new GImage(IMAGE_NAME);
		
		image.setSize(info.POWER_WIDTH, info.POWER_HEIGHT);
		
		this.image = image;
		this.speed = null;
	}
	
	public void Activate(BreakoutPro game,GameInfo info,GameEngine engine,GameSetup setup,GameObjects objects,Ball ball,Audio audio,Life life,Power power)
	{
		new Thread(() -> 
		{
			try 
			{
				objects.paddle.image.setBounds(
						objects.paddle.image.getX()-objects.paddle.image.getWidth()/2.,
						objects.paddle.image.getY(),
						objects.paddle.image.getWidth()*2,
						objects.paddle.image.getHeight());
				
				Thread.sleep(10000);
				
				objects.paddle.image.setBounds(
						objects.paddle.image.getX()+objects.paddle.image.getWidth()/4.,
						objects.paddle.image.getY(),
						objects.paddle.image.getWidth()/2.,
						objects.paddle.image.getHeight());
				
			} catch (InterruptedException e) {}
		}).start();
	}
}
