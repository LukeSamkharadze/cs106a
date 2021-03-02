package Levels;

import java.util.Vector;

import Game.GameInfo;
import Objects.Brick;

public abstract class Level 
{
	public abstract Vector<Brick> CreateBricks(GameInfo info);
}
