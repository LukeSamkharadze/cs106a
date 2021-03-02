package Powers;

import Game.GameEngine;
import Game.GameInfo;
import Game.GameObjects;
import Game.GameSetup;
import Main.BreakoutPro;
import Objects.Ball;
import Other.Audio;
import Other.Life;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class NewLifePower extends Power
{
	private static final String IMAGE_NAME = "power.png";
	
	public NewLifePower(GameInfo info)
	{
		super(null,null);
		GImage image = new GImage(IMAGE_NAME);
		
		image.setSize(info.POWER_WIDTH, info.POWER_HEIGHT);
		
		this.image = image;
		this.speed = null;
	}
	
	public void Activate(BreakoutPro game,GameInfo info,GameEngine engine,GameSetup setup,GameObjects objects,Ball ball,Audio audio,Life life,Power power)
	{
		life.IncreaseLive(game, info);
	}
}
