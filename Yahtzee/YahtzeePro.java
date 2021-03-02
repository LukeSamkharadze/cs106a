import acm.io.*;
import acm.program.*;
import acm.util.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import CalculateScores.*;
import Casino.Casino;
import Dealer.Dealer;
import Dealer.DealerSituations;
import Visual.Visual;
import Audio.*;

public class YahtzeePro extends GraphicsProgram implements YahtzeeConstants
{
	public static void main(String[] args) 
	{
		new YahtzeePro().start(args);
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
		removeAll();
		Visual.DisplayIntro(this);
		
		IODialog dialog = getDialog();
		
		nPlayers = 2;
		
		playerNames = new String[nPlayers];

		playerNames[1] = "MARIKUNA";
		
		casino.Appear(this);
		Audio.PlayBackgroundMusic();
		DealerSituations.Greet(this, dealer,playerNames,dialog);
		Start();
	}

	//============================================================================================//
	
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	private int[] dices = new int[N_DICE];

	private Map<Integer, CalculateScore> calculateScores = new HashMap<Integer,CalculateScore>();
	private Map<Integer,int[]> playerCategoryScores = new HashMap<Integer, int[]>();
	
	private int[] upperScoreCategories = { 1, 2, 3, 4, 5, 6 };
	private int[] lowerScoreCategories = { 9, 10, 11, 12, 13, 14, 15 };
	
	private Casino casino = new Casino();
	private Dealer dealer = new Dealer();
	
	private boolean IsCaughtCheating = false;
	
	//============================================================================================//
	
	private void Start() 
	{
		Setup();
		Play();
		Ending();
	}
	
	private void Setup()
	{
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		
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
			PlayDice(0);
			
			int selectedCategory = display.waitForPlayerToSelectCategory();
			while(playerCategoryScores.get(0)[selectedCategory]!=-1)
			{
				if(IsCaughtCheating == false)
				{
					DealerSituations.Cheating1(this, dealer);
					IsCaughtCheating=true;
				}
				else
				{
					DealerSituations.Cheating2(this, dealer);
					Audio.isTurnedOn = false;
					casino.KickOut(this);
					Visual.DisplayText(this,"UNFORTUNETLY, YOU LOST",true);
				}
				selectedCategory = display.waitForPlayerToSelectCategory();
			}
			
			int scoreGot = calculateScores.get(selectedCategory).Calculate(dices);
			DisplayAndSaveScore(0,selectedCategory,scoreGot);
			if(scoreGot==50)
				DealerSituations.YAHTZE(this, dealer);
			else if(scoreGot >= 30)
				DealerSituations.NiceMove(this, dealer);
			else if(scoreGot == 0)
				DealerSituations.BadMove(this, dealer);
			
			PlayDealer();
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
				DealerSituations.Draw(this,dealer);
				return;
			}
			if(playerCategoryScores.get(playerID)[TOTAL] > playerCategoryScores.get(winnerPlayerID)[TOTAL])
				winnerPlayerID = playerID;
		}
		
		if(winnerPlayerID == 0)
		{
			DealerSituations.Lose(this, dealer);
			Start();
		}
		else
		{
			DealerSituations.Win(this,dealer);
			Audio.isTurnedOn = false;
			casino.KickOut(this);
			Visual.DisplayText(this,"UNFORTUNETLY, YOU LOST",true);
		}
	}
	
	//============================================================================================//
	
	private void PlayDealer()
	{
		display.printMessage("ITS " + playerNames[1] + "'S TURN TO PLAY");
		
		int maxkeyCategoryScore = -1;
		for (int rollID = 0; rollID < 3; rollID++) 
		{
			try { Thread.sleep(2000);} catch (InterruptedException e) {}
			
			GenerateNewDiceNumbers();
			display.displayDice(dices);
			
			maxkeyCategoryScore = -1;
			for(int keyCategory : calculateScores.keySet())
				if(
						playerCategoryScores.get(1)[keyCategory]==-1 && 
						calculateScores.get(keyCategory).Calculate(dices) > 
						((maxkeyCategoryScore == -1)? -1 : calculateScores.get(maxkeyCategoryScore).Calculate(dices)))
					maxkeyCategoryScore = keyCategory;
			
			if(calculateScores.get(maxkeyCategoryScore).Calculate(dices)>=20)
				break;
		}
		
		try { Thread.sleep(2000);} catch (InterruptedException e) {}
		
		DisplayAndSaveScore(1,maxkeyCategoryScore,calculateScores.get(maxkeyCategoryScore).Calculate(dices));
	}
	
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
	
	private void UpdateScoreboard(int playerID)
	{
		// Cache
		int upperScore = FindScoreSum(playerID,upperScoreCategories);
		int lowerScore = FindScoreSum(playerID,lowerScoreCategories);
		// Upper
		DisplayAndSaveScore(playerID, UPPER_SCORE ,upperScore);
		// Bonus
		if(upperScore > 63)
			DisplayAndSaveScore(playerID, UPPER_SCORE, 35);
		// Lower
		DisplayAndSaveScore(playerID, LOWER_SCORE,lowerScore);
		// Total
		DisplayAndSaveScore(playerID, TOTAL, upperScore + lowerScore);
	}
	
	//============================================================================================//
	
	private void DisplayAndSaveScore(int playerID,int categoryID, int score)
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
			sum += playerCategoryScores.get(playerID)[categoryIDs[categoryIDID]];
			
		return sum;
	}
}