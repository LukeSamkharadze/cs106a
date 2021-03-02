package Main;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import acmx.export.javax.swing.JButton;

import java.awt.*;
import java.util.Vector;

import Button.Button;
import Edition.Edition;
import Edition.MariEdition;
import Edition.ScaryEdition;
import Other.Audio;
import Other.KeyboardButtonController;
import Other.Other;
import Visual.Visual;

import Other.Setup;

public class HangmanPro extends GraphicsProgram
{
	private Vector<Character> charactersTried = new Vector<Character>();
	
	private Setup setup = new Setup();
	
	private Edition scaryEdition = new ScaryEdition(this);
	private Edition mariEdition = new MariEdition(this);
	private Edition currentEdition;
	
	private Visual visual = new Visual();
	private KeyboardButtonController keyboardButtonController = new KeyboardButtonController();
	
	private GLabel currentlyGuessedSecretWord = new GLabel("");
	private String secretWord;
	private int livesLeft = 8;
	
	//==================================================================//
	
	public void run() 
	{	
		println(getWidth());
		println(getHeight());
		
		visual.PlayIntro(this);
		
		Audio.PlayBackgroundMusic();
		
		GoToMenu();
	}
	
	//==================================================================//
	
	public void GoToMenu() 
	{
		FadeIn();
		setBackground(Color.WHITE);
		visual.DisplayText(this, "WELCOME TO HANGMAN.PRO",true);
		visual.DisplayMenu(this,scaryEdition, mariEdition, keyboardButtonController);
		FadeOut();
	}

	public void Setup(Edition edition)
	{	
		FadeIn();	
		
		currentEdition = edition;
		
		if(currentEdition instanceof ScaryEdition)
			setBackground(Color.RED);
		else
			setBackground(Color.PINK);
		
		livesLeft = 8;
		secretWord = currentEdition.dictionary.GetRandomWord();
		setup.SetupGame(this, charactersTried, currentlyGuessedSecretWord, currentEdition, keyboardButtonController, secretWord);
		
		FadeOut();
	}
	
	public boolean TryGuess(char guess) throws Exception
	{
		if(Other.IsCharacterAvailable(charactersTried, guess) == false)
			throw new Exception("Character is not available");
		
		if (livesLeft > 0 && Other.IsSecretWordGuessed(charactersTried, secretWord) == false)
		{
			charactersTried.add(guess);
			
			if(Other.IsCharacterInSecretWord(secretWord, guess) == true)
			{
				currentlyGuessedSecretWord.setLabel(Other.CurrentlyGuessedSecretWord(charactersTried, secretWord));
				if(Other.IsSecretWordGuessed(charactersTried, secretWord))
					visual.DisplayText(this,"Y O U   W O N",false);
				return true;
			}
			else
			{
				livesLeft--;
				currentEdition.drawer.AddNewPart(8-livesLeft);
				
				if(livesLeft==0)
					visual.DisplayText(this,"Y O U   L O S T",false);
				
				return false;
			}
		}
		
		throw new Exception("You cannot try guesses");
	}
	
	//=============================
	
	private void FadeIn()
	{
		for (Button button : keyboardButtonController.buttons)
			button.rect.removeMouseListener(button.mouseListener);
		keyboardButtonController.buttons.removeAllElements();
		
		visual.FadeIn(this);
		
		for (int i = getElementCount()-2; i >=0; i--)
			remove(getElement(i));
	}
	
	private void FadeOut()
	{
		visual.FadeOut(this);
		
		for (Button button : keyboardButtonController.buttons)
			button.rect.addMouseListener(button.mouseListener);
	}
}