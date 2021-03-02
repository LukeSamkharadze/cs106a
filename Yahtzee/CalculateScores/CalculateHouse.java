package CalculateScores;

public class CalculateHouse extends CalculateScore
{
	public int Calculate(int[] dices) 
	{
		for (int diceID = 0; diceID < dices.length; diceID++)
			if(HowManyTimesContain(dices, dices[diceID]) == 3)
				for (int diceID_ = 0; diceID_ < dices.length; diceID_++)
					if(dices[diceID_]!=dices[diceID])
						if(HowManyTimesContain(dices, dices[diceID_]) == 2)
							return 25;
		
		return 0;
	}
}
