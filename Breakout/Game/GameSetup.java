package Game;
import java.util.Vector;

import Game.GameInfo;
import Levels.Level;
import Levels.Level1;
import Objects.Ball;
import Objects.Brick;
import Objects.Paddle;
import Other.Speed;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class GameSetup 
{
	public Ball CreateBall(GraphicsProgram graphics,Paddle paddle, GameInfo info)
	{
		GImage image = new GImage(info.BALL_NAME);
		Ball ball = new Ball(image,null);
		
		SpawnBall(graphics, info,paddle, ball,false);
		
		return ball;
	}
	
	public void SpawnBall(GraphicsProgram graphics, GameInfo info,Paddle paddle,Ball ball,boolean waitForClick)
	{
		if(waitForClick)
			graphics.waitForClick();
		
		ball.image.setBounds(
				(paddle.image.getX() + paddle.image.getWidth() / 2.) - info.BALL_DIAMETER/2.,
				paddle.image.getY() - info.BALL_DIAMETER - 15,
				info.BALL_DIAMETER,
				info.BALL_DIAMETER);
		
		graphics.add(ball.image);
		
		ball.speed = GenerateRandomSpeed(info);
	}
	
	private Speed GenerateRandomSpeed(GameInfo info)
	{
		return new Speed(
				info.APPLICATION_WIDTH*RandomGenerator.getInstance().nextDouble(-5, 5)/800,
				info.APPLICATION_HEIGHT*-5/600);
	}
	
	public Paddle CreatePaddle(GameInfo info)
	{
		GImage image = new GImage(
				info.PADDLE_NAME,
				info.WIDTH/2. - info.PADDLE_WIDTH/2.,
				info.HEIGHT - info.PADDLE_HEIGHT - info.PADDLE_Y_OFFSET);
		
		image.setSize(info.PADDLE_WIDTH,info.PADDLE_HEIGHT);
		
		return new Paddle(image,null);
	}
	
	public Vector<Brick> CreateBricks(GameInfo info,Level level)
	{
		return level.CreateBricks(info);
	}
	
	public Brick CreateBrick(GraphicsProgram graphics,GameInfo info,int lives, double x,double y)
	{
		int NBRICKS_PER_ROW = 10;
		int BRICK_HEIGHT = 25;
		int BRICK_SEP = 5;
		int BRICK_WIDTH = (info.WIDTH - (NBRICKS_PER_ROW+1) * BRICK_SEP) / NBRICKS_PER_ROW;
		
		GImage brickImage = new GImage(info.BRICK_NAMES[lives]);
		
		brickImage.setBounds(x-BRICK_WIDTH/2., y-BRICK_HEIGHT/2., BRICK_WIDTH, BRICK_HEIGHT);
		
		Brick brick = new Brick(brickImage, null, lives);
		
		graphics.add(brick.image);
		
		return brick;
	}

	public void SetupWindow(GameInfo info,GraphicsProgram graphics)
	{
		graphics.setSize(info.APPLICATION_WIDTH+16,info.APPLICATION_HEIGHT+59);
	}
}