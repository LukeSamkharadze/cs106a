import acm.io.*;
import acm.program.*;
import acm.util.*;

import java.util.HashMap;
import java.util.Map;

import CalculateScores.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants
{
	public static void main(String[] args) 
	{
		new Yahtzee().start(args);
	}

	public void init()
	{
		calculateScores.put(ONES, new CalculateNumber(1));
		calculateScores.put(TWOS, new CalculateNumber(2));
		calculateScores.put(THREES, new CalculateNumber(3));
		calculateScores.put(FOURS, new CalculateNumber(4));
		calculateScores.put(FIVES, new CalculateNumber(5));
		calculateScores.put(SIXES, new CalculateNumber(6));
		calculateScores.put(THREE_OF_A_KIND, new CalculateKind(3));
		calculateScores.put(FOUR_OF_A_KIND, new CalculateKind(4));
		calculateScores.put(FULL_HOUSE, new CalculateHouse());
		calculateScores.put(SMALL_STRAIGHT, new CalculateStraight(4));
		calculateScores.put(LARGE_STRAIGHT, new CalculateStraight(5));
		calculateScores.put(YAHTZEE, new CalculateKind(5));
		calculateScores.put(CHANCE, new CalculateChance());
	}
	
	public void run()
	{
		IODialog dialog = getDialog();
		
		nPlayers = dialog.readInt("Enter number of players");
		while(nPlayers > MAX_PLAYERS)
			nPlayers = dialog.readInt("Enter number of players (MAX 4)");
		
		playerNames = new String[nPlayers];

		for (int i = 1; i <= nPlayers; i++)
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		
		Start();
	}

	//============================================================================================//
	
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

	//==================================================//
	
	private int[] dices = new int[N_DICE];

	private Map<Integer, CalculateScore> calculateScores = new HashMap<Integer,CalculateScore>();
	private Map<Integer,int[]> playerCategoryScores = new HashMap<Integer, int[]>();
	
	private int[] upperScoreCategories = { 1, 2, 3, 4, 5, 6 };
	private int[] lowerScoreCategories = { 9, 10, 11, 12, 13, 14, 15 };
	
	//============================================================================================//
	
	private void Start() 
	{
		Setup();
		Play();
		Ending();
	}
	
	private void Setup()
	{
		for (int playerID = 0; playerID < nPlayers; playerID++) 
		{
			playerCategoryScores.put(playerID,new int[18]);
			for (int arrayID = 0; arrayID < 18; arrayID++)
				playerCategoryScores.get(playerID)[arrayID] = -1;
		}
	}
	
	private void Play()
	{
		for(int gameCounter = 0; gameCounter < 13; gameCounter++)
		{
			for (int playerID = 0; playerID < nPlayers; playerID++)
			{
				display.printMessage(playerNames[playerID] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
				
				PlayDice(playerID);
				
				display.printMessage("Select a category for this roll");
				
				int selectedCategory;
				do
				{
					selectedCategory = display.waitForPlayerToSelectCategory();
					display.printMessage("Select a different category for this roll");
				}
				while(playerCategoryScores.get(0)[selectedCategory]!=-1);
				
				display.printMessage("");
				
				PlayCategory(playerID,selectedCategory);
			}
		}
	}
	
	private void Ending()
	{
		for (int playerID = 0; playerID < nPlayers; playerID++)
			UpdateScoreboard(playerID);
		
		int winnerPlayerID = 0;
		for (int playerID = 1; playerID < nPlayers; playerID++) 
		{
			if(playerCategoryScores.get(playerID)[TOTAL] == playerCategoryScores.get(winnerPlayerID)[TOTAL])
			{
				display.printMessage("ITS DRAW SUCKAAAA");
				return;
			}
			if(playerCategoryScores.get(playerID)[TOTAL] > playerCategoryScores.get(winnerPlayerID)[TOTAL])
				winnerPlayerID = playerID;
		}
		
		display.printMessage(
				"Congratulations, " + playerNames[winnerPlayerID] +
				", you're the winner with a total score of" +
				playerCategoryScores.get(winnerPlayerID)[TOTAL] + "!");
	}
	
	//============================================================================================//
	
	private void PlayDice(int playerID)
	{
		display.waitForPlayerToClickRoll(playerID+1);
		GenerateNewDiceNumbers();
		display.displayDice(dices);
		
		for (int rollID = 0; rollID < 2; rollID++)
		{
			display.waitForPlayerToSelectDice();
			
			if(IsAnySelected() == false)
				break;
			
			GenerateNewSelectedDiceNumbers();
			display.displayDice(dices);
		}
	}
	
	private void PlayCategory(int playerID, int selectedCategory)
	{
		WriteAndSaveScore(playerID, selectedCategory, calculateScores.get(selectedCategory).Calculate(dices));
	}
	
	private void UpdateScoreboard(int playerID)
	{
		// Cache
		int upperScore = FindScoreSum(playerID,upperScoreCategories);
		int lowerScore = FindScoreSum(playerID,lowerScoreCategories);
		// Upper
		WriteAndSaveScore(playerID, UPPER_SCORE ,upperScore);
		// Bonus
		if(upperScore > 63)
			WriteAndSaveScore(playerID, UPPER_SCORE, 35);
		// Lower
		WriteAndSaveScore(playerID, LOWER_SCORE,lowerScore);
		// Total
		WriteAndSaveScore(playerID, TOTAL, upperScore + lowerScore);
	}
	
	//============================================================================================//
	
	private void WriteAndSaveScore(int playerID,int categoryID, int score)
	{
		display.updateScorecard(categoryID, playerID+1, score);
		playerCategoryScores.get(playerID)[categoryID] = score;
	}
	
	private void GenerateNewDiceNumbers()
	{
		for (int i = 0; i < dices.length; i++)
			dices[i] = rgen.nextInt(1, 6);
	}
	
	private void GenerateNewSelectedDiceNumbers()
	{
		for (int i = 0; i < dices.length; i++)
			if(display.isDieSelected(i))
				dices[i] = rgen.nextInt(1, 6);
	}
	
	private boolean IsAnySelected()
	{
		for (int i = 0; i < dices.length; i++) 
			if(display.isDieSelected(i))
				return true;
		
		return false;
	}

	int FindScoreSum(int playerID,int[] categoryIDs)
	{
		int sum = 0;
		for (int categoryIDID = 0; categoryIDID < categoryIDs.length; categoryIDID++) 
			sum+=playerCategoryScores.get(playerID)[categoryIDs[categoryIDID]];
			
		return sum;
	}
}
