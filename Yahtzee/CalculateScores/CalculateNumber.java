package CalculateScores;

public class CalculateNumber extends CalculateScore
{
	private int number;
	
	public CalculateNumber(int number)
	{
		this.number = number;
	}
	
	public int Calculate(int[] dices) 
	{
		return number * HowManyTimesContain(dices, number);
	}
}