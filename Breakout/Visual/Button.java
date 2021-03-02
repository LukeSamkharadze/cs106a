package Visual;

import Main.BreakoutPro;
import Objects.GameObject;
import Other.Speed;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class Button extends GameObject
{
	public BreakoutPro game;
	
	public static boolean isClicked = false;
	
	public Button(GImage image, Speed speed)
	{
		super(image, speed);
	}
	
	public Button(BreakoutPro game,GImage image, Speed speed)
	{
		super(image, speed);
		this.game = game;
	}
	
}
