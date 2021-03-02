package Visual;

import java.awt.Color;
import java.awt.Font;

import Button.Button;
import Button.EditionButton;
import Edition.Edition;
import Main.HangmanPro;
import Other.Audio;
import Other.KeyboardButtonController;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class Visual 
{
	private GRect fadeRect;
	
	public void SetupFadeRect(GRect fadeRect)
	{
		this.fadeRect = fadeRect;
	}
	
	public void PlayIntro(GraphicsProgram graphics)
	{
		Audio.PlayMouseClick2Sound();
		graphics.pause(250);
		GImage jumpscare= new GImage("mari-hand.jpg");
		jumpscare.setSize(graphics.getWidth(), graphics.getHeight());
		
		graphics.add(jumpscare);
		graphics.pause(2000);
	}
	
	public void DisplayMenu(HangmanPro game,Edition scaryEdition, Edition mariEdition, KeyboardButtonController buttonAdder)
	{
		Button scaryEditionButton = new EditionButton(
				game, scaryEdition, 10,game.getHeight()/2., game.getWidth()/2. - 15,game.getHeight()/2.-10, "START SCARY EDITION");
		buttonAdder.AddButton(game, scaryEditionButton);
		
		Button mariEditionButton = new EditionButton(
				game, mariEdition, game.getWidth()/2. +5,game.getHeight()/2., game.getWidth()/2. - 15,game.getHeight()/2. - 10, "START MARI EDITION");
		buttonAdder.AddButton(game, mariEditionButton);
	}
	
	public void DisplayText(GraphicsProgram graphics, String text,boolean inMiddle)
	{
		GLabel label = new GLabel(text);
		label.setFont(new Font("Arial",Font.BOLD,(int)(40 * graphics.getWidth()/754.)));
		label.setLocation(
				(graphics.getWidth()/2. + ((inMiddle)?graphics.getWidth()/2.:graphics.getWidth()*2/3.))/2.-label.getWidth()/2.,
				(0 + graphics.getHeight()/2.)/2.+label.getAscent());
		
		label.setColor(new Color(0,0,0,0));
		graphics.add(label);
		
		  new Thread(() ->
		  {
			for (int i = 0; i <= 255; i++)
			{
				label.setColor(new Color(0,0,0,i));
				graphics.pause(5);
			}
		  }).start();
	}
	
	public void FadeIn(GraphicsProgram graphics)
	{
		fadeRect = new GRect(0,0,graphics.getWidth(),graphics.getHeight());
		fadeRect.setFilled(true);
		fadeRect.setFillColor(new Color(0, 0, 0, 0));
		graphics.add(fadeRect);
		
		for (int i = 0; i <= 255; i++)
		{
			fadeRect.setFillColor(new Color(0,0, 0, i));
			graphics.pause(2);
		}
	}
	
	public void FadeOut(GraphicsProgram graphics)
	{
		for (int i = 255; i >= 0; i--)
		{
			fadeRect.setFillColor(new Color(0,0, 0, i));
			graphics.pause(2);
		}
		graphics.remove(fadeRect);
	}
}
