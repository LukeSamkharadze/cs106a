package CalculateScores;

import java.util.HashMap;
import java.util.Map;

public class CalculateStraight extends CalculateScore
{
	private Map<Integer,Integer> straightScores = new HashMap<Integer, Integer>();
	
	private int straight;

	public CalculateStraight(int straight)
	{
		straightScores.put(4,30);
		straightScores.put(5,40);
		
		this.straight = straight;
	}
	
	public int Calculate(int[] dices) 
	{
		for (int diceID = 0; diceID < dices.length; diceID++)
			if(ContainsStraight(dices, dices[diceID]))
				return straightScores.get(straight);
		
		return 0;
	}
	
	private boolean ContainsStraight(int[] dices, int dice)
	{
		for (int i = 1; i < straight; i++)
			if(Contains(dices, dice+i) == false)
				return false;
		
		return true;
	}
	
	private boolean Contains(int[] dices, int dice)
	{
		for (int diceID = 0; diceID < dices.length; diceID++)
			if(dices[diceID] == dice)
				return true;
		
		return false;
	}
}
