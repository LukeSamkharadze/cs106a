package Objects;

import Other.Speed;
import acm.graphics.GImage;

public class Ball extends GameObject
{
	public boolean paddleFlag = true;
	
	public Ball(GImage image, Speed speed)
	{
		super(image, speed);
	}
	
}