package Game;

import java.util.Vector;

import Objects.Ball;
import Objects.Brick;
import Objects.Paddle;
import Powers.Power;

public class GameObjects
{
	public Ball ball;
	public Paddle paddle;
	
	public Vector<Ball> powerBalls = new Vector<Ball>();
	
	public Vector<Brick> bricks = new Vector<Brick>();
	
	public Vector<Power> powers = new Vector<Power>();
}