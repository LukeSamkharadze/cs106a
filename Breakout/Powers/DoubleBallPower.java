package Powers;

import Game.GameEngine;
import Game.GameInfo;
import Game.GameObjects;
import Game.GameSetup;
import Main.BreakoutPro;
import Objects.Ball;
import Other.Audio;
import Other.Life;
import Other.Speed;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class DoubleBallPower extends Power
{
	private static final String IMAGE_NAME = "power.png";
	private static final String NEW_BALL_NAME = "newball.jpg";
	
	public DoubleBallPower(GameInfo info)
	{
		super(null,null);
		GImage image = new GImage(IMAGE_NAME);
		
		image.setSize(info.POWER_WIDTH, info.POWER_HEIGHT);
		
		this.image = image;
		this.speed = null;
	}
	
	public void Activate(BreakoutPro game,GameInfo info,GameEngine engine,GameSetup setup,GameObjects objects,Ball ball,Audio audio,Life life,Power power)
	{
		GImage image = new GImage(NEW_BALL_NAME);
		
		Ball powerBall = new Ball(image,null);
		
		objects.powerBalls.add(powerBall);
		
		game.add(powerBall.image);
		setup.SpawnBall(game, info,objects.paddle, powerBall, false);
		
		new Thread(() -> 
		{
			try 
			{
				for(;;Thread.sleep(10))
				{
					if(
							engine.UpdateWithLeftovers(game, info, setup, objects, powerBall, audio, life) == false ||
							objects.powerBalls.contains(powerBall) == false ||
							game.isWaiting == true)
					{
						objects.powerBalls.remove(powerBall);
						game.remove(powerBall.image);
						break;
					}
				}
			} catch (InterruptedException e) {}
		}).start();
	}
}
