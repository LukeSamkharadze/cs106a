package CalculateScores;

import java.util.HashMap;
import java.util.Map;

public class CalculateKind extends CalculateScore
{
	Map<Integer,Integer> kindScores = new HashMap<Integer, Integer>();
	
	int kind;
	
	public CalculateKind(int kind)
	{
		kindScores.put(5,50);
		
		this.kind = kind;
	}
	
	public int Calculate(int[] dices) 
	{
		for (int diceID = 0; diceID < dices.length; diceID++)
			if(HowManyTimesContain(dices, dices[diceID])>=kind)
				return GetScore(dices);
		
		return 0;
	}

	private int GetScore(int[] dices)
	{
		if(kindScores.get(kind) == null)
			return FindSum(dices);
		
		return kindScores.get(kind);
	}
}
