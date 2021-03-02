package Other;

import java.awt.Font;
import java.util.Vector;

import Edition.Edition;
import Main.HangmanPro;
import acm.graphics.GLabel;

public class Setup 
{
	public void SetupGame(
			HangmanPro game,Vector<Character> charactersTried,
			GLabel currentlyGuessedSecretWord, Edition currentEdition,
			KeyboardButtonController keyboardButtonController,String secretWord)
	{
		charactersTried.removeAllElements();
		
		currentlyGuessedSecretWord.setLocation(160 * game.getWidth()/754., game.getHeight()*(1.9/3.));
		currentlyGuessedSecretWord.setLabel(Other.CurrentlyGuessedSecretWord(charactersTried, secretWord));
		currentlyGuessedSecretWord.setFont(new Font("Arial",Font.BOLD,(int)(27 * game.getWidth()/754.)));
		
		game.add(currentlyGuessedSecretWord);
		
		currentEdition.drawer.Reset();
		keyboardButtonController.DisplayKeyboardButtons(game,currentEdition);
		keyboardButtonController.DisplayMenuButton(game);
	}
	
}
