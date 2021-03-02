package Objects;

import Other.Speed;
import acm.graphics.GImage;

public abstract class GameObject 
{
	public GImage image;
	public Speed speed;
	
	public GameObject(GImage image, Speed speed)
	{
		this.speed=speed;
		this.image=image;
	}
}
