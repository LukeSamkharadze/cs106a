import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import stanford.karel.SuperKarel;

public class LostSnakeKarel extends SuperKarel 
{
	int[][] snake;
	int snakeLength;
	int lastDirection = 0;
	
	public int xWorldLength;
	public int yWorldLength;
	
	public void run() 
	{
		GetWorldInfo();
		
		snakeLength = xWorldLength;
		
		Setup();
		StartMoving();
	}
	
	private void Setup()
	{
		snake = new int[snakeLength][];
		
		for(int i = 0; i < snakeLength; i++)
		{
			snake[i] = new int[] {i % xWorldLength,0};
		}
		
		Draw();
	}
	
	private void StartMoving()
	{
		Map<Integer, int[]> dxy = new HashMap<Integer, int[]>();
		
		dxy.put(0, new int[]{1,0});
		dxy.put(1, new int[]{0,1});
		dxy.put(2, new int[]{-1,0});
		dxy.put(3, new int[]{0,-1});
		
		Random rand = new Random();
		
		for(;;pause(50))
		{
			Clear();
			
			int newDirection = GetRandomDirection(rand);
			
			lastDirection = newDirection;
			
			for(int i = snakeLength-1; i >= 1; i--)
			{
				snake[i][0] = snake[i-1][0];
				snake[i][1] = snake[i-1][1];
			}
			
			MoveHead(dxy.get(newDirection)[0], dxy.get(newDirection)[1]);
			
			Draw();
		}
	}
	
	private void Clear()
	{
		for(int i = 0; i < snakeLength; i++)
		{
			PickBeeper(snake[i][0], snake[i][1]);
		}
	}
	
	private void Draw()
	{
		for(int i = snakeLength-1; i >= 0; i--)
		{
			PutBeeper(snake[i][0], snake[i][1]);
		}
	}
	
	// =============================================================================//
	
	private int GetRandomDirection(Random rand)
	{
		int randInt = rand.nextInt(4);
		randInt = (rand.nextBoolean()) ? randInt : lastDirection;
		
		while(Math.abs(randInt - lastDirection) == 2)
		{
			randInt = rand.nextInt(4);
			randInt = (rand.nextBoolean()) ? randInt : lastDirection;
		}
		
		return randInt;
	}
	
	private void MoveHead(int dx, int dy)
	{
		if(snake[0][0] + dx < 0)
			snake[0][0] = xWorldLength + dx;
		else
			snake[0][0] = (snake[0][0] + dx) % xWorldLength;
		
		if(snake[0][1] + dy < 0)
			snake[0][1] = yWorldLength + dy;
		else
			snake[0][1] = (snake[0][1] + dy) % yWorldLength;
	}
	
	public void GotoCordinates(int x, int y) 
	{
		TurnSouth();
		MoveTillWall();
		TurnWest();
		MoveTillWall();

		TurnEast();
		for (int steps = 0; steps < x; steps++)
		{
			move();
		}
		
		TurnNorth();
		for (int steps = 0; steps < y; steps++)
		{
			move();
		}
	}

	public void PutBeeper(int x, int y) 
	{
		GotoCordinates(x, y);
		putBeeper();
	}

	public void PickBeeper(int x, int y) 
	{
		GotoCordinates(x, y);
		pickBeeper();
	}
	
	// =============================================================================//
	
	public void GetWorldInfo() 
	{
		xWorldLength = GetWorldX();
		yWorldLength = GetWorldY();
	}

	public int GetWorldX() 
	{
		TurnEast();
		return GetStepsTillWall() + 1;
	}

	public int GetWorldY() 
	{
		TurnNorth();
		return GetStepsTillWall() + 1;
	}

	public int GetStepsTillWall() 
	{
		for (int steps = 0;; steps++)
		{
			if (frontIsClear() == true)
			{
				move();
			}
			else
			{
				return steps;
			}
		}
	}

	// =============================================================================//

	protected void MoveTillWall() 
	{
		while (frontIsClear())
			move();
	}

	protected void TurnNorth() 
	{
		while (facingNorth() == false)
			turnLeft();
	}

	protected void TurnSouth() 
	{
		while (facingSouth() == false)
			turnLeft();
	}

	protected void TurnEast() 
	{
		while (facingEast() == false)
			turnLeft();
	}

	protected void TurnWest() 
	{
		while (facingWest() == false)
			turnLeft();
	}
}
