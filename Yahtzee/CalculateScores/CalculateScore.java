package CalculateScores;

public abstract class CalculateScore 
{
	public abstract int Calculate(int[] dices);
	
	protected int HowManyTimesContain(int[] dices, int dice)
	{
		int counter = 0;
		for (int diceID = 0; diceID < dices.length; diceID++)
			if(dices[diceID]==dice)
				counter++;
		
		return counter;
	}
	
	protected int FindSum(int[] dices)
	{
		int sum = 0;
		for (int diceID = 0; diceID < dices.length; diceID++)
			sum+=dices[diceID];
		
		return sum;
	}
}
