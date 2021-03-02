package Main;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

import Game.GameSetup;
import Game.GameEngine;
import Game.GameInfo;
import Game.GameObjects;

import Levels.Level;
import Objects.Ball;
import Objects.Brick;
import Other.Audio;
import Other.Life;
import Powers.DoubleBallPower;
import Powers.LengthPower;
import Powers.NewLifePower;
import Visual.Visual;

public class BreakoutPro extends GraphicsProgram
{
	public GameInfo info = new GameInfo();
	public GameSetup setup = new GameSetup();
	public GameEngine engine = new GameEngine();
	public GameObjects objects = new GameObjects();
	
	public Visual visual = new Visual();
	public Audio audio = new Audio();
	public Life life = new Life();
	
	public boolean isWaiting = false;
	public boolean isBuilding = false;
	public boolean isInMenu = true;
	
	public boolean lost = true;
	
	public void run()
	{
		addMouseListeners();
		setup.SetupWindow(info,this);
		
		audio.PlayStartMusic();
				
		isInMenu=true;
		visual.DisplayIntro(this, info);
	}
	
	//==============
	
	public void StartCampaign()
	{
		visual.RemoveEverythingExceptVisualBricks(this);
		
		SetupGameObjects();
		AddObjectsToGame();
		
		isInMenu=false;
		if(PlayCampaign())
			visual.DisplayWin(this, info);
		else
			visual.DisplayLose(this, info);
	}
	
	public boolean PlayCampaign()
	{
		for (Level level : info.LEVELS) 
		{
			SetupLevel(level);
			
			if(Play() == false)
				return false;
		}
		return true;
	}
	
	//==============
	
	public void StartBuilding()
	{
		visual.RemoveEverythingExceptVisualBricks(this);
		
		SetupGameObjects();
		AddObjectsToGame();
		
		isInMenu=false;
		isBuilding = true;
		visual.DisplayPlayCustomButton(this,info);
	}
	
	public void StartCustom()
	{
		isBuilding = false;
		
		if(Play())
			visual.DisplayWin(this, info);
		else
			visual.DisplayLose(this, info);
	}
		
	//==============
	
	private boolean Play()
	{
		isWaiting=true;
		waitForClick();
		isWaiting=false;
		
		for(;info.lives > 0 && objects.bricks.size() > 0; pause(10))
			if(engine.UpdateWithLeftovers(this, info, setup, objects,objects.ball, audio, life)==false)
			{
				objects.powerBalls.removeAllElements();
				
				if(life.DecreaseLive(this,info)  == 0)
					return false;
				
				isWaiting=true;
				setup.SpawnBall(this,info, objects.paddle,objects.ball, true);
				this.waitForClick();
				isWaiting=false;
			}
		return true;
	}
	
	private void SetupLevel(Level level)
	{
		objects.powerBalls.removeAllElements();
		
		for (Brick brick : objects.bricks)
			remove(brick.image);
		
		objects.bricks.removeAllElements();
		
		objects.bricks = setup.CreateBricks(info,level);
		
		for (Brick brick : objects.bricks)
			add(brick.image);

		for (Ball ball : objects.powerBalls)
			remove(ball.image);
			
		setup.SpawnBall(this, info,objects.paddle,objects.ball, false);
	}
	
	private void SetupGameObjects()
	{	
		objects.paddle = setup.CreatePaddle(info);
		objects.ball = setup.CreateBall(this,objects.paddle,info);
		objects.bricks.removeAllElements();
		
		objects.powers.add(new LengthPower(info));
		objects.powers.add(new DoubleBallPower(info));
		objects.powers.add(new NewLifePower(info));
	}
	
	private void AddObjectsToGame()
	{
		visual.DrawLines(this, info);
		visual.DrawVault(this, info);
		
		life.DrawHearts(this, info);
		
		add(objects.ball.image);
		
		for (Brick brick : objects.bricks)
			add(brick.image);
		
		add(objects.paddle.image);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(isBuilding && e.getX()<info.WIDTH)
		{
			for (Brick brick : objects.bricks) 
			{
				if(brick.image == getElementAt(e.getX(),e.getY()))
				{
					brick.IncreaseLive(this, info);
					return;
				}
			}
			
			objects.bricks.add(setup.CreateBrick(this, info, 1, e.getX(), e.getY()));
		}
	}
	
	public void mouseMoved(MouseEvent e)
	{
		if(isBuilding==false && isInMenu == false)
		{
			if(e.getX() - objects.paddle.image.getWidth()/2. > 0 &&
			   e.getX() + objects.paddle.image.getWidth()/2. < info.WIDTH)
				objects.paddle.image.setLocation(
						e.getX()-objects.paddle.image.getWidth()/2.,
						objects.paddle.image.getY());
			if(isWaiting)
				objects.ball.image.setLocation(
						(objects.paddle.image.getX()+objects.paddle.image.getWidth()/2.)-objects.ball.image.getWidth()/2.,
						objects.paddle.image.getY() - objects.ball.image.getHeight()- 15);
		}
	}
}