package Powers;

import Game.GameEngine;
import Game.GameInfo;
import Game.GameObjects;
import Game.GameSetup;
import Main.BreakoutPro;
import Objects.Ball;
import Objects.GameObject;
import Other.Audio;
import Other.Life;
import Other.Speed;
import acm.graphics.GImage;

public abstract class Power extends GameObject
{
	public abstract void Activate(BreakoutPro game,GameInfo info,GameEngine engine,GameSetup setup,GameObjects objects,Ball ball,Audio audio,Life life,Power power);
	
	public Power(GImage image, Speed speed)
	{
		super(image, speed);
	}
}