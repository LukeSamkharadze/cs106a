import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.Vector;

public class Hangman extends ConsoleProgram 
{
	HangmanLexicon lexicon = new HangmanLexicon();
	HangmanCanvas canvas = new HangmanCanvas();
	
	Vector<Character> charactersTried = new Vector<Character>();
	String secretWord;
	
	int livesLeft = 8;
	
	public void init()
	{
		add(canvas);
	}
	
	public void run() 
	{
		Setup();
		Start();
	}
	
	private void Start()
	{
		println("Welcome to Hangman!");
		if(Play())
		{			
			println("You guessed the word: " + secretWord);
			println("You win");
		}
		else
		{
			println("The word was: " + secretWord);
			println("You lose");
		}
	}
	
	private boolean Play()
	{
		for (;livesLeft > 0 && IsSecretWordGuessed() == false;)
		{
			print("The word now looks like this: ");
			println(CurrentlyGuessedSecretWord());
			println("You have " + ((livesLeft>1)? livesLeft + " guesses": "one guess") + " left.");
			
			char guess = GetGuess();
			charactersTried.add(guess);
			
			if(IsCharacterInSecretWord(guess) == false)
			{
				println("There are no " + guess +"'s in the word.");
				
				canvas.AddToTriedLettersLabel(guess);
				livesLeft--;
			}
			else
			{
				println("That guess is correct.");
				
				canvas.UpdateCurrentlyGuessedSecretWord(CurrentlyGuessedSecretWord());
			}
		}
		
		
		return IsSecretWordGuessed();
	}
	
	private void Setup()
	{	
		charactersTried.removeAllElements();
		secretWord = lexicon.GetRandomWordFromLexicon();
		
		canvas.Reset(CurrentlyGuessedSecretWord());
	}
	
	//=============================================//
	
	private char GetGuess()
	{
		for (;;) 
		{
			String enteredLine = readLine("Your guess: ");
			
			if(enteredLine.length() == 0 || enteredLine.length() > 1)
			{
				println("Try to enter 1 character");
				continue;
			}
			char potentialGuess = enteredLine.toUpperCase().charAt(0);
			
			if(IsCharacterAvailable(potentialGuess))
				return potentialGuess;
			
			println("Try again");
		}
	}
	
	//=============================================//
	
	private String CurrentlyGuessedSecretWord()
	{
		String currentlyGuessedSecretWord = "";
		
		for (int secretWordCharIndex = 0; secretWordCharIndex < secretWord.length(); secretWordCharIndex++)
		{
			if(charactersTried.contains(secretWord.charAt(secretWordCharIndex)))
				currentlyGuessedSecretWord += secretWord.charAt(secretWordCharIndex);
			else
				currentlyGuessedSecretWord += "-";
		}
		
		return currentlyGuessedSecretWord;
	}
	
	//=============================================//
	
	private boolean IsCharacterAvailable(char c)
	{
		if((c > 64 && c < 91) || (c>96 && c < 123))
			return true;
		
		return false;
	}

	private boolean IsCharacterInSecretWord(char c)
	{
		for (int secretWordCharIndex = 0; secretWordCharIndex < secretWord.length(); secretWordCharIndex++)
			if(secretWord.charAt(secretWordCharIndex) == c)
				return true;
		
		return false;
	}

	private boolean IsSecretWordGuessed()
	{
		for (int secretWordCharIndex = 0; secretWordCharIndex < secretWord.length(); secretWordCharIndex++)
			if(charactersTried.contains(secretWord.charAt(secretWordCharIndex)) == false)
				return false;
		
		return true;
	}
}