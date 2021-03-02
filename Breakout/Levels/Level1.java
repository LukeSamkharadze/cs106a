package Levels;

import java.util.Vector;

import Game.GameInfo;
import Objects.Brick;
import acm.graphics.GImage;
import acm.util.RandomGenerator;

public class Level1 extends Level
{
	private final int NBRICKS_PER_ROW = 10;
	private final int NBRICK_ROWS = 7;
	
	private final int BRICK_SEP = 5;
	private final int BRICK_Y_OFFSET = 60;
	
	public Vector<Brick> CreateBricks(GameInfo info)
	{
		double brickWidth = CalculateBrickWidth(info);
		
		Vector<Brick> bricks = new Vector<Brick>();
		
		for (int rowID = 0; rowID < NBRICK_ROWS; rowID++)
			for (int columnID = 0; columnID < NBRICKS_PER_ROW; columnID++)
			{
					GImage image = new GImage(
							info.BRICK_NAMES[1],
							BRICK_SEP + columnID * (BRICK_SEP+brickWidth) + RandomGenerator.getInstance().nextDouble(-2, 2),
							BRICK_Y_OFFSET + rowID * (BRICK_SEP+info.BRICK_HEIGHT) + RandomGenerator.getInstance().nextDouble(-2, 2));
					
					image.setSize(brickWidth,info.BRICK_HEIGHT);
					
					bricks.add(new Brick(image, null,1));
			}
		
		return bricks;
	}
	
	private double CalculateBrickWidth(GameInfo info)
	{
		return (info.WIDTH - (NBRICKS_PER_ROW+1) * BRICK_SEP) / NBRICKS_PER_ROW;
	}
}
