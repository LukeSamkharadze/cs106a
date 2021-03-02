package Main;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Vector;


public class Breakout extends GraphicsProgram
{
	public class GameSetup 
	{
		public Ball CreateBall()
		{
			GOval oval = new GOval(
					WIDTH/2. - BALL_RADIUS,
					HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET - 2 * BALL_RADIUS-15,
					BALL_RADIUS*2,
					BALL_RADIUS*2);
			
			oval.setFillColor(Color.BLACK);
			oval.setFilled(true);
			
			Speed speed = new Speed(6,-6);
			
			return new Ball(oval,speed);
		}
		
		public void SpawnBall()
		{
			ball.collision.setLocation(
					WIDTH/2. - BALL_RADIUS*2,
					HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET - BALL_RADIUS*2-15);
			
			ball.speed.vX=6;
			ball.speed.vY=-6;
		}
		
		public Paddle CreatePaddle( )
		{
			GRect rect = new GRect(
					WIDTH/2. - PADDLE_WIDTH/2.,
					HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET,
					PADDLE_WIDTH,
					PADDLE_HEIGHT);
			
			rect.setFillColor(Color.BLACK);
			rect.setFilled(true);
			
			return new Paddle(rect);
		}
		
		public Vector<Brick> CreateBricks()
		{
			Vector<Brick> bricks = new Vector<Brick>();
			
			double sameColorRowBricks = NBRICK_ROWS / (double)colors.length;
			
			for (int rowID = 0, colorID=0, sameColorCounter=0; rowID < NBRICK_ROWS; rowID++,sameColorCounter++)
			{
				if(sameColorCounter>=sameColorRowBricks)
				{
					sameColorCounter-=sameColorRowBricks;
					colorID++;
				}
				
				for (int columnID = 0; columnID < NBRICKS_PER_ROW; columnID++)
				{
							GRect rect = new GRect(
									BRICK_SEP + columnID * (BRICK_SEP+BRICK_WIDTH),
									BRICK_Y_OFFSET + rowID * (BRICK_SEP+BRICK_HEIGHT),
									BRICK_WIDTH,
									BRICK_HEIGHT);
						
						rect.setColor(colors[colorID]);
						rect.setFillColor(colors[colorID]);
						
						rect.setFilled(true);
						
						Brick brick = new Brick(rect);
						
						bricks.add(brick);
					}
			}
			
			return bricks;
		}

	}
	
	public class GameEngine
	{
		private boolean paddleFlag = true;
		
		public boolean Move()
		{
			Speed normalizedSpeed = Normalize(ball.speed);
			
			if(normalizedSpeed.vX!=0)
			{
				for (
						double xDistance = 0;
						xDistance < Math.abs(ball.speed.vX)-Math.abs(normalizedSpeed.vX); 
						xDistance += Math.abs(normalizedSpeed.vX))
				{
					if(MoveDistance(normalizedSpeed.vX, normalizedSpeed.vY)==false)
							return false;
					
					normalizedSpeed = Normalize(ball.speed);
				}
			}
			else
			{
				for (
						double yDistance = 0;
						yDistance < Math.abs(ball.speed.vY)-Math.abs(normalizedSpeed.vY); 
						yDistance += Math.abs(normalizedSpeed.vY))
				{
					if(MoveDistance(normalizedSpeed.vX, normalizedSpeed.vY) == false)
						return false;
					
					normalizedSpeed = Normalize(ball.speed);
				}
			}
			return true;
		}
		
		public void MoveWithLeftovers()
		{
			Speed normalizedSpeed = Normalize(ball.speed);
			
			//double leftoverVX=Math.abs(ball.speed.vX) - Math.abs(normalizedSpeed.vX);
			
			for (double xDistance = 0;//Math.abs(normalizedSpeed.vX);
					xDistance < Math.abs(ball.speed.vX)-Math.abs(normalizedSpeed.vX); 
					xDistance += Math.abs(normalizedSpeed.vX))
			{
				MoveDistance(normalizedSpeed.vX, normalizedSpeed.vY);
				
				normalizedSpeed = Normalize(ball.speed);
				//leftoverVX = leftoverVX - xDistance;
			}
			
			//double leftoverScale = Math.abs(normalizedSpeed.vX) / leftoverVX;
			//double leftoverVY = normalizedSpeed.vY / leftoverScale;
			//MoveDistance(, , bricks, paddle, ball, leftoverVX*Math.signum(normalizedSpeed.vX), leftoverVY);
		}
		
		private boolean MoveDistance(double vX, double vY)
		{
			ball.collision.move(vX,vY);
			
			if(CheckWalls()==false)
				return false;
			
			CheckBricks();
			
			if(paddleFlag)
				CheckPaddle();
			
			return true;
		}
		
		private void CheckBricks()
		{
			Vector<Brick> bricksCollided = new Vector<Brick>();
			
			for (Brick brick : bricks)
				if(IsOverlapping(brick, ball))
					bricksCollided.add(brick);
			
			if(bricksCollided.size()==0)
				return;
			
			Brick brickCollided = bricksCollided.firstElement();
			
			for (int brickID = 1; brickID < bricksCollided.size(); brickID++)
				if(Distance(brickCollided,ball) > Distance(bricksCollided.elementAt(brickID),ball))
					brickCollided = bricksCollided.elementAt(brickID);
			
			CollideBrick(brickCollided);
			paddleFlag=true;
			
			remove(brickCollided.collision);
			bricks.remove(brickCollided);
		}
		
		private void CheckPaddle()
		{
			if(IsOverlapping(paddle,ball))
			{
				CollidePaddle();
				paddleFlag=false;
			}
		}
		
		private boolean CheckWalls()
		{
			if(ball.collision.getX()<0)
				ball.speed.vX*=-1;
			else if(ball.collision.getY()<0)
			{
				paddleFlag = true;
				ball.speed.vY*=-1;
			}
			else if(ball.collision.getX()+ball.collision.getWidth()>WIDTH)
				ball.speed.vX*=-1;
			else if(ball.collision.getY()+ball.collision.getHeight()>HEIGHT)
				return false;
			return true;
		}

		private void CollidePaddle()
		{
			if(ball.collision.getX() + ball.collision.getWidth() < paddle.collision.getX() + 1 || 
					ball.collision.getX() > paddle.collision.getX()+ paddle.collision.getWidth() - 1)
				ball.speed.vX*=-1;
			else
				ball.speed.vY*=-1;
		}

		private void CollideBrick(Brick brick)
		{
			if(ball.collision.getX() + ball.collision.getWidth() < brick.collision.getX() + 1 || 
					ball.collision.getX() > brick.collision.getX()+ brick.collision.getWidth() - 1)
			{
				if(ball.collision.getX() > brick.collision.getX())
					ball.collision.move(brick.collision.getX()+brick.collision.getWidth()-ball.collision.getX()+1, 0);
				else
					ball.collision.move(-(ball.collision.getX()+ball.collision.getWidth()-brick.collision.getX())-1, 0);
				ball.speed.vX*=-1;
			}
			else
			{
				if(ball.collision.getY() > brick.collision.getY())
					ball.collision.move(0, brick.collision.getY() + brick.collision.getHeight() - ball.collision.getY()+1);
				else
					ball.collision.move(0, -(ball.collision.getY() + ball.collision.getHeight() - brick.collision.getY())-1);
				ball.speed.vY*=-1;
			}
		}
	
		//=========================//
		
		private Speed Normalize(Speed speed)
		{
			return new Speed(speed.vX / Math.sqrt(Math.pow(speed.vX, 2)+Math.pow(speed.vY, 2)),
					speed.vY / Math.sqrt(Math.pow(speed.vX, 2)+Math.pow(speed.vY, 2)));
		}
		
		private boolean IsOverlapping(Brick brick,Ball ball)
		{
			if(ball.collision.getX() > brick.collision.getX()+brick.collision.getWidth() ||
					ball.collision.getX()+ball.collision.getWidth() < brick.collision.getX())
				return false;
			
			if(ball.collision.getY() > brick.collision.getY() + brick.collision.getHeight() ||
			ball.collision.getY()+ball.collision.getHeight() < brick.collision.getY())
				return false;
				
			return true;
		}

		private double Distance(Brick brick,Ball ball)
		{
			return Math.sqrt(Math.pow(brick.collision.getX()+brick.collision.getWidth()/2. - ball.collision.getX() + ball.collision.getWidth()/2.,2) +
					Math.pow(brick.collision.getY()+brick.collision.getHeight()/2. - ball.collision.getY() + ball.collision.getHeight()/2.,2));
		}
	}

	public class Speed 
	{
		public double vX;
		public double vY;
		
		public Speed(double vX, double vY) 
		{
			this.vX=vX;
			this.vY=vY;
		}
	}
	
	public class Ball
	{
		public GOval collision;
		public Speed speed;
		
		public Ball(GOval collision, Speed speed) 
		{
			this.collision = collision;
			this.speed = speed;
		}
	}
	
	public class Brick
	{
		public GRect collision;
		
		public Brick(GRect collision) 
		{
			this.collision = collision;
		}
	}
	
	public class Paddle extends Brick
	{
		public Paddle(GRect collision)
		{
			super(collision);
		}
	}
	
	//=================================//
	
	public final Color[] colors = new Color[] { Color.RED,Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN };
	
	public final int APPLICATION_WIDTH = 400;
	public final int APPLICATION_HEIGHT = 600;

	public final int WIDTH = APPLICATION_WIDTH;
	public final int HEIGHT = APPLICATION_HEIGHT;

	public final int PADDLE_WIDTH = 50;
	public final int PADDLE_HEIGHT = 10;
	public final int PADDLE_Y_OFFSET = 30;
	
	public final int NBRICKS_PER_ROW = 10;
	public final int NBRICK_ROWS = 10;
	public final int BRICK_SEP = 4;
	public final int BRICK_Y_OFFSET = 70;
	public final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW+1) * BRICK_SEP) / NBRICKS_PER_ROW;
	public final int BRICK_HEIGHT = 8;

	public final int BALL_RADIUS = 10;

	public final int NTURNS = 3;
	
	//=================================//
	
	private GameSetup gameSetup = new GameSetup();
	private GameEngine gameEngine = new GameEngine();
	
	private Ball ball = gameSetup.CreateBall();
	private Paddle 	paddle = gameSetup.CreatePaddle();
	
	private Vector<Brick> bricks = gameSetup.CreateBricks();
	
	private int lives = NTURNS;
	
	//=================================//
	
	public void run()
	{
		// you know what to do
		//setSize(APPLICATION_WIDTH+10, APPLICATION_HEIGHT+60);
		addMouseListeners();
		
		AddGame();
		StartGame();
	}
	
	//=================================//
	
	private void AddGame()
	{
		add(ball.collision);
		
		for (Brick brick : bricks)
			add(brick.collision);
		
		add(paddle.collision);
	}
	
	private void StartGame()
	{
		for(;lives>0;pause(10))
			if(gameEngine.Move()==false)
			{
				pause(1000);
				gameSetup.SpawnBall();
				lives--;
			}
	}
	
	public void mouseMoved(MouseEvent e)
	{
		if(e.getX() - paddle.collision.getWidth()/2. > 0 && e.getX() + paddle.collision.getWidth()/2. < WIDTH)
			paddle.collision.setLocation(e.getX()-paddle.collision.getWidth()/2., paddle.collision.getY());
	}
}