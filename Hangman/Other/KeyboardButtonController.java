package Other;

import java.util.Vector;

import Button.Button;
import Button.CharacterButton;
import Button.EditionButton;
import Button.MenuButton;
import Edition.Edition;
import Main.HangmanPro;

public class KeyboardButtonController 
{
	public Vector<Button> buttons = new Vector<Button>();
	
	public void DisplayKeyboardButtons(HangmanPro game,Edition editionToRestart)
	{
		final String[][] QWERTY = 
			{
				{"Q","W","E","R","T","Y","U","I","O","P"},
				{"A","S","D","F","G","H","J","K","L"},
				{"Z","X","C","V","B","N","M"}
			};
		
		final double GAP = 10;
		
		final double WIDTH = (game.getWidth() - 2*GAP - GAP*(QWERTY[0].length-1)) / QWERTY[0].length;
		final double HEIGHT = (game.getHeight()/3 - GAP - GAP * (QWERTY.length-1)) / QWERTY.length;
		
		  for (int rowID = 0; rowID < QWERTY.length; rowID++) 
			  for (int collumnID = 0; collumnID < QWERTY[rowID].length; collumnID++) 
				  DisplayCharacterButton(game, QWERTY, rowID, collumnID, WIDTH, HEIGHT, GAP,QWERTY[rowID][collumnID]);
		 
		  DisplayRestartButton(game,editionToRestart, QWERTY, QWERTY.length-1, QWERTY[QWERTY.length-1].length+2, WIDTH, HEIGHT, GAP, "RES");
	}
	
	
    private void DisplayCharacterButton(
    		HangmanPro game,String[][] qwerty,
    		int rowID,int collumnID,double width,double height, double gap, String text)
	{
		Button button = new CharacterButton(
				  game,
				  gap + (gap + width) * collumnID,
				  game.getHeight() - gap - height - (gap+height) * (qwerty.length-rowID-1),
				  width,
				  height,
				  text);
		  
		AddButton(game,button);
	}
	
	private void DisplayRestartButton(
			HangmanPro game,Edition editionToRestart,String[][] qwerty,
			int rowID,int collumnID,double width,double height, double gap, String text)
	{
		Button button = new EditionButton(
				  game,
				  editionToRestart,
				  gap + (gap + width) * collumnID,
				  game.getHeight() - gap - height - (gap+height) * (qwerty.length-rowID-1),
				  width,
				  height,
				  text);
		  
		AddButton(game,button);
	}
	
	public void DisplayMenuButton(HangmanPro game)
	{
		Button button = new MenuButton(game,game.getWidth()-75-10,10,75,50,"MENU");
		
		AddButton(game,button);
	}
	
	public void AddButton(HangmanPro game,Button button)
	{
		buttons.add(button);
		
		button.rect.setVisible(false);
		button.label.setVisible(false);
		  
		game.add(button.rect);
		game.add(button.label);
		  
		button.rect.sendToBack();
		button.label.sendToFront();
		
		button.rect.setVisible(true);
		button.label.setVisible(true);
	}
}
