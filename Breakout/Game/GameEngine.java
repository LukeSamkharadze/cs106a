package Game;
import java.util.Vector;

import Game.GameInfo;
import Main.BreakoutPro;
import Objects.Ball;
import Objects.Brick;
import Objects.GameObject;
import Objects.Paddle;
import Other.Audio;
import Other.Life;
import Other.Speed;
import Powers.Power;
import acm.graphics.GImage;
import acm.graphics.GRectangle;
import acm.util.RandomGenerator;

public class GameEngine
{
	long startTime = System.currentTimeMillis();

	double elapsedSeconds;
	
	private Vector<Power> powersActivated = new Vector<Power>();
	
	private boolean powerFlag = true;
	
	public boolean counting = false;
	
	public boolean UpdateWithLeftovers(BreakoutPro game,GameInfo info,GameSetup setup,GameObjects objects,Ball ball,Audio audio,Life life)
	{
		Speed normalizedSpeed = Normalize(ball.speed);
		
		if(normalizedSpeed.vX!=0)
		{
			double leftoverVX = Math.abs(ball.speed.vX) - Math.abs(normalizedSpeed.vX);
			
			if(leftoverVX<0)
				leftoverVX=1+leftoverVX;
			
			for (;leftoverVX >= Math.abs(normalizedSpeed.vX);leftoverVX -= Math.abs(normalizedSpeed.vX))
			{
				if(MoveDistance(game, info, setup, objects, ball, audio, life, normalizedSpeed.vX, normalizedSpeed.vY) == false)
						return false;
				normalizedSpeed = Normalize(ball.speed);
			}
			
			double leftoverScale = Math.abs(normalizedSpeed.vX) / leftoverVX;
			double leftoverVY = normalizedSpeed.vY / leftoverScale;
			
			if(MoveDistance(game, info, setup, objects, ball, audio, life, leftoverVX*Math.signum(normalizedSpeed.vX), leftoverVY) == false)
				return false;
		}
		else
		{
			double leftoverVY = Math.abs(ball.speed.vY) - Math.abs(normalizedSpeed.vY);
			
			if(leftoverVY<0)
				leftoverVY=1+leftoverVY;
			
			for (;leftoverVY >= Math.abs(normalizedSpeed.vY);leftoverVY -= Math.abs(normalizedSpeed.vY))
			{
				if(MoveDistance(game, info, setup, objects, ball, audio, life, normalizedSpeed.vX, normalizedSpeed.vY) == false)
					return false;
				
				normalizedSpeed = Normalize(ball.speed);
			}
			
			double leftoverScale = Math.abs(normalizedSpeed.vY) / leftoverVY;
			double leftoverVX = normalizedSpeed.vX / leftoverScale;
			
			if(MoveDistance(game, info, setup, objects, ball, audio, life, leftoverVX, leftoverVY * Math.signum(normalizedSpeed.vY)) == false)
				return false;
		}
		
		return true;
	}
	
	private boolean MoveDistance(BreakoutPro game,GameInfo info,GameSetup setup,GameObjects objects,Ball ball,Audio audio,Life life,double vX, double vY)
	{
		ball.image.move(vX,vY);
		
		CheckBricks(game, info, setup, objects, ball, audio, life);
		
		if(ball.paddleFlag)
			CheckPaddle(game, info, objects.paddle, ball);
		
		return CheckWalls(info,ball);
	}
	
	//=============================
	
	private void CheckBricks(BreakoutPro game,GameInfo info,GameSetup setup,GameObjects objects,Ball ball,Audio audio,Life life)
	{
		Vector<Brick> bricksCollided = new Vector<Brick>();
		
		try
		{
		for (Brick brick : objects.bricks)
			if(IsOverlapping(brick, ball))
				bricksCollided.add(brick);
		} catch(Exception e) {}
		
		if(bricksCollided.size()==0)
			return;
		
		// Hit brick
		Brick brickCollided = bricksCollided.firstElement();
		
		for (int brickID = 1; brickID < bricksCollided.size(); brickID++)
			if(Distance(brickCollided,ball) > Distance(bricksCollided.elementAt(brickID),ball))
				brickCollided = bricksCollided.elementAt(brickID);
		
		ball.paddleFlag=true;
		
		CollideBrick(brickCollided,ball);
		audio.PlayCollisionSound();
		
		FallBrick(game,info,brickCollided.image.getBounds(),brickCollided.lives);
		
		if(brickCollided.DecreaseLive(game, info) < 1)
			objects.bricks.remove(brickCollided);
		
		if(RandomGenerator.getInstance().nextDouble()< 0.1 && powerFlag)
			FallPower(game,info,setup,objects,ball,audio,life,
					objects.powers.elementAt(RandomGenerator.getInstance().nextInt(objects.powers.size())),
					brickCollided.image.getBounds());
	}

	private void CheckPaddle(BreakoutPro game,GameInfo info,Paddle paddle,Ball ball)
	{
		if(IsOverlapping(paddle, ball))
		{
			CollidePaddle(paddle,ball);
			ball.paddleFlag=false;
		}
	}
	
	private boolean CheckWalls(GameInfo info, Ball ball)
	{
		if(ball.image.getX()<0)
		{
			ball.image.move(0-ball.image.getX(), 0);
			ball.speed.vX*=-1;
		}
		else if(ball.image.getY()<0)
		{
			ball.image.move(0, 0-ball.image.getY());
			ball.speed.vY*=-1;
			ball.paddleFlag=true;
		}
		else if(ball.image.getX()+ball.image.getWidth()>info.WIDTH)
		{
			ball.image.move(-(ball.image.getX()+ball.image.getWidth()-info.WIDTH), 0);
			ball.speed.vX*=-1;
		}
		else if(ball.image.getY()+ball.image.getHeight()>info.HEIGHT)
		{
			ball.paddleFlag=true;
			return false;
		}
		return true;
	}
	
	//==============================
	
	private void FallBrick(BreakoutPro game,GameInfo info,GRectangle bounds, int lives)
	{
		new Thread(() -> 
		{
			GImage image = new GImage(info.BRICK_NAMES[lives]);
			image.setBounds(bounds);
			game.add(image);
			while(true)
			{
				image.move(RandomGenerator.getInstance().nextDouble(-2,2), RandomGenerator.getInstance().nextDouble(0,5));
				
				try 
				{
					Thread.sleep(10);
				} catch (InterruptedException e) {}
				
				if(image.getY()>game.getHeight())
					if(RandomGenerator.getInstance().nextDouble()<=0.99)
					image.setLocation(
							RandomGenerator.getInstance().nextDouble(info.WIDTH, info.APPLICATION_WIDTH-image.getWidth()),
							-image.getHeight());
					else
					{
						game.remove(image);
						break;
					}
			}
		}).start();
	}
	
	private void FallPower(BreakoutPro game,GameInfo info,GameSetup setup,GameObjects objects,Ball ball,Audio audio,Life life,Power power,GRectangle bounds)
	{
		powerFlag=false;
		power.image.setLocation(
				(bounds.getX()+bounds.getWidth()/2.)-power.image.getWidth()/2.,
				(bounds.getY()+bounds.getHeight()/2.)-power.image.getHeight()/2.);
		
		game.add(power.image);
		
		new Thread(() -> 
		{
			try 
			{
				for(;;Thread.sleep(10))
				{
					power.image.move(RandomGenerator.getInstance().nextDouble(-1,1), RandomGenerator.getInstance().nextDouble(0,5));
					
					if(IsOverlapping(power, objects.paddle) && game.isWaiting==false)
					{
						power.Activate(game, info, this, setup, objects, ball, audio, life, power);
						powersActivated.add(power);
						game.remove(power.image);
						break;
					}
					if(power.image.getY()>game.getHeight())
					{
						game.remove(power.image);
						break;
					}
				}
			} catch (InterruptedException e) {}
			powerFlag=true;
		}).start();
	}
	
	//===============================
	
	private void CollideBrick(Brick brick,Ball ball)
	{
		if(ball.image.getX() + ball.image.getWidth() < brick.image.getX() + 1 || 
				ball.image.getX() > brick.image.getX()+ brick.image.getWidth() - 1)
		{
			if(ball.image.getX() > brick.image.getX())
				ball.image.move(brick.image.getX()+brick.image.getWidth()-ball.image.getX()+1, 0);
			else
				ball.image.move(-(ball.image.getX()+ball.image.getWidth()-brick.image.getX())-1, 0);
			ball.speed.vX*=-1;
		}
		else
		{
			if(ball.image.getY() > brick.image.getY())
				ball.image.move(0, brick.image.getY() + brick.image.getHeight() - ball.image.getY()+1);
			else
				ball.image.move(0, -(ball.image.getY() + ball.image.getHeight() - brick.image.getY())-1);
			ball.speed.vY*=-1;
		}
	}
	
	private void CollidePaddle(Paddle paddle,Ball ball)
	{
		if(ball.image.getX() + ball.image.getWidth() < paddle.image.getX() + 1 || 
				ball.image.getX() > paddle.image.getX()+ paddle.image.getWidth() - 1)
			ball.speed.vY*=-1;
		else
		{
			double distance = (ball.image.getX()+ball.image.getWidth()/2.)-(paddle.image.getX()+paddle.image.getWidth()/2.);
			
			ball.speed.vX = ball.speed.vX + Math.abs(ball.speed.vX) * distance/(paddle.image.getWidth()/2.)/2. + RandomGenerator.getInstance().nextDouble(-3,3);
			ball.speed.vY*=-1;
			
			if(Math.abs(ball.speed.vX)>10)
				ball.speed.vX=Math.signum(ball.speed.vX)*10;
		}
		ball.speed.vY-=0.1;
	}
	
	//===============================
	
	private Speed Normalize(Speed speed)
	{
		return new Speed(speed.vX / Math.sqrt(Math.pow(speed.vX, 2)+Math.pow(speed.vY, 2)),
				speed.vY / Math.sqrt(Math.pow(speed.vX, 2)+Math.pow(speed.vY, 2)));
	}
	
	private boolean IsOverlapping(GameObject object1,GameObject object2)
	{
		if(object2.image.getX() > object1.image.getX()+object1.image.getWidth() ||
				object2.image.getX()+object2.image.getWidth() < object1.image.getX())
			return false;
		
		if(object2.image.getY() > object1.image.getY() + object1.image.getHeight() ||
		object2.image.getY()+object2.image.getHeight() < object1.image.getY())
			return false;
			
		return true;
	}

	private double Distance(GameObject object1,GameObject object2)
	{
		return Math.sqrt(Math.pow(object1.image.getX()+object1.image.getWidth()/2. - object2.image.getX() + object2.image.getWidth()/2.,2) +
				Math.pow(object1.image.getY()+object1.image.getHeight()/2. - object2.image.getY() + object2.image.getHeight()/2.,2));
	}
} 