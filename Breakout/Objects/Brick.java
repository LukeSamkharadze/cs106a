package Objects;

import Game.GameInfo;
import Other.Speed;

import acm.graphics.GImage;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;

public class Brick extends GameObject
{
	public int lives;
	
	public Brick(GImage image, Speed speed, int lives)
	{
		super(image, speed);
		this.lives=lives;
	}
	
	public int DecreaseLive(GraphicsProgram graphicsProgram,GameInfo gameInfo)
	{
		graphicsProgram.remove(image);
		
		if(--lives > 0)
		{
			GRectangle imageBounds = image.getBounds();
			
			image = new GImage(gameInfo.BRICK_NAMES[lives]);
			
			image.setBounds(imageBounds);
			
			graphicsProgram.add(image);
		}
		
		return lives;
	}

	public void IncreaseLive(GraphicsProgram graphicsProgram,GameInfo gameInfo)
	{	
		if(lives+1 < gameInfo.BRICK_NAMES.length)
		{
			++lives;
			
			graphicsProgram.remove(image);
			
			GRectangle imageBounds = image.getBounds();
			
			image = new GImage(gameInfo.BRICK_NAMES[lives]);
			
			image.setBounds(imageBounds);
			
			graphicsProgram.add(image);
		}
	}

}