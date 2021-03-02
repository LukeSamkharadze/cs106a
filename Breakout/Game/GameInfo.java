package Game;

import Levels.Level;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.Level4;
import Levels.Level5_Heart;
import Levels.Level6_Mari;

public class GameInfo 
{
	public final Level[] LEVELS = new Level[] { new Level1(), new Level2(), new Level3(), new Level4(), new Level5_Heart(),new Level6_Mari()};
	
	public final String[] BRICK_NAMES = new String[] { null, "dolara1.png", "dolara2.png","dolara3.png"};
	
	public final String PADDLE_NAME = "paddle.png";
	public final String BALL_NAME = "ball.jpg";
	public final String HEART_NAME = "heart.jpg";
	
	public final int APPLICATION_WIDTH = 800;
	public final int APPLICATION_HEIGHT = 600;

	public final int WIDTH = APPLICATION_WIDTH*600/800;
	public final int HEIGHT = APPLICATION_HEIGHT;

	public final int BRICK_HEIGHT = 25;
	public final int BRICK_WIDTH = 40;
	
	public final int PADDLE_WIDTH = APPLICATION_WIDTH*100/800;
	public final int PADDLE_HEIGHT = APPLICATION_HEIGHT*100/600;
	public final int PADDLE_Y_OFFSET = APPLICATION_HEIGHT*0/600;

	public final int BALL_DIAMETER = (APPLICATION_WIDTH*APPLICATION_HEIGHT)*20/(800*600);
	
	public final int NTURNS = 3;
	
	public final int HEART_WIDTH = APPLICATION_WIDTH*35/800;
	public final int HEART_HEIGHT = APPLICATION_HEIGHT*35/600;
	public final int HEART_GAP = APPLICATION_WIDTH*10/800;
	public final int HEART_X_OFFSET = APPLICATION_WIDTH*10/800;
	public final int HEART_Y_OFFSET = APPLICATION_HEIGHT*0/600;
	
	public final int POWER_WIDTH = APPLICATION_WIDTH*30/800;
	public final int POWER_HEIGHT = APPLICATION_HEIGHT*30/600;
	
	public int lives = 0;
}