package Visual;

import java.awt.Color;
import java.util.Vector;

import Game.GameInfo;
import Main.BreakoutPro;
import Objects.Brick;
import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

public class Visual 
{
	private final String VAULT_NAME = "vault.jpg";
	private final String MENU_NAME = "menu.png";
	private final String BIG_TEXT_NAME = "cash-breakout.png";
	private final String LOGO_NAME = "intro.gif";
	private final String WIN_NAME = "win.png";
	private final String LOSE_NAME = "lose.png";
	private final String THANKS_NAME = "thanks-to-mari.png";
	
	public Vector<Brick> visualBricks = new Vector<Brick>();
	public final int VISUAL_BRICKS_COUNT = 2000;
	
	//=================
	
	public void DisplayIntro(BreakoutPro game,GameInfo info)
	{
		DisplayText(game, info);
		DisplayLogo(game, info);
		DisplayThanks(game,info);
		DisplayMenu(game, info);
	}
	
	//=================
	
	public void DisplayText(BreakoutPro game,GameInfo info)
	{
		GImage image = new GImage(BIG_TEXT_NAME);
		
		image.setBounds(
				info.APPLICATION_WIDTH / 2. - info.APPLICATION_WIDTH * 0.90 / 2.,
				info.APPLICATION_HEIGHT / 2. - info.APPLICATION_HEIGHT * 0.175/1.5,
				info.APPLICATION_WIDTH * 0.90,
				info.APPLICATION_HEIGHT * 0.175);
		
		Appear(game,image);
		
		Disappear(game, image);
	}

	public void DisplayLogo(BreakoutPro game,GameInfo info)
	{
		GImage image = new GImage(LOGO_NAME);
		
		image.setBounds(
				info.APPLICATION_WIDTH / 2. - info.APPLICATION_WIDTH * 0.5 / 2.,
				info.APPLICATION_HEIGHT / 2. - info.APPLICATION_HEIGHT * 0.75/1.75,
				info.APPLICATION_WIDTH * 0.5,
				info.APPLICATION_HEIGHT * 0.75);
		
		Appear(game, image);
		
		Sleep(5000);
		Disappear(game, image);
	}
	
	public void DisplayThanks(BreakoutPro game,GameInfo info)
	{
		GImage image = new GImage(THANKS_NAME);
		
		image.setBounds(0,0,info.APPLICATION_WIDTH,info.APPLICATION_HEIGHT);
		
		
		Appear(game, image);
		
		Disappear(game, image);
	}
	
	public void DisplayMenu(BreakoutPro game,GameInfo info)
	{
		GImage image = new GImage(MENU_NAME);
				
		image.setBounds(0,0,info.APPLICATION_WIDTH,info.APPLICATION_HEIGHT);
		
		game.add(image);
		
		DisplayMenuButtons(game,info);
	}
	
	//=================
	
	public void DisplayWin(BreakoutPro game,GameInfo info)
	{
		RemoveEverythingExceptVisualBricks(game);
		
		GImage image = new GImage(WIN_NAME);
		
		image.setBounds(0,0,info.APPLICATION_WIDTH,info.APPLICATION_HEIGHT);
		
		Appear(game, image);
		
		DisplayMenuButtons(game,info);
	}
	
	public void DisplayLose(BreakoutPro game,GameInfo info)
	{
		RemoveEverythingExceptVisualBricks(game);
		
		GImage image = new GImage(LOSE_NAME);
		
		image.setBounds(0,0,info.APPLICATION_WIDTH,info.APPLICATION_HEIGHT);
		
		Appear(game, image);
		
		DisplayMenuButtons(game,info);
	}
	
	//=================
	
	public void DisplayMenuButtons(BreakoutPro game,GameInfo info)
	{
		Button.isClicked=false;
		DisplayCampaingButton(game,info);
		DisplayCustomButton(game,info);
	}
	
	public void DisplayCampaingButton(BreakoutPro game,GameInfo info)
	{
		GImage image = new GImage("play-campaign1.png");
		
		image.setBounds(
				info.APPLICATION_WIDTH /2. - 2.2*info.APPLICATION_WIDTH * 0.4/2.,
				info.APPLICATION_HEIGHT / 2. + 2*info.APPLICATION_HEIGHT * 0.4 /2. / 2.,
				info.APPLICATION_WIDTH * 0.4,
				info.APPLICATION_HEIGHT * 0.4 /2.);
		
		game.add(image);
		
		PlayCampaign campaingButton = new PlayCampaign(game,image, null);
		image.addMouseListener(campaingButton.listener);
		
	}
	
	public void DisplayCustomButton(BreakoutPro game,GameInfo info)
	{
		GImage image = new GImage("play-custom1.png");
		
		image.setBounds(
				info.APPLICATION_WIDTH /2.,
				info.APPLICATION_HEIGHT / 2. + 2*info.APPLICATION_HEIGHT * 0.4 /2. / 2.,
				info.APPLICATION_WIDTH * 0.4,
				info.APPLICATION_HEIGHT * 0.4 /2.);
		
		game.add(image);
		
		PlayCustom customButton = new PlayCustom(game,image, null);
		customButton.game = game;
		image.addMouseListener(customButton.listener);
	}
	
	public void DisplayPlayCustomButton(BreakoutPro game,GameInfo info)
	{
		GImage image = new GImage("start-custom1.png");
		
		image.setBounds(0,0, info.APPLICATION_WIDTH * 0.2, info.APPLICATION_HEIGHT * 0.2 /2.);
		
		game.add(image);
		
		StartCustomGame startCustomGame = new StartCustomGame(game,image, null);
		startCustomGame.game = game;
		image.addMouseListener(startCustomGame.listener);
	}
	
	//==================
	
	public void DrawLines(BreakoutPro game, GameInfo info)
	{
		GRect rect = new GRect(info.WIDTH-2.5,0,5,info.HEIGHT);
		
		rect.setFillColor(Color.BLACK);
		rect.setFilled(true);
		
		game.add(rect);
	}
	
	public void DrawVault(BreakoutPro game, GameInfo info)
	{
		GImage vaultImage = new GImage(VAULT_NAME);
		
		vaultImage.setBounds(
				(info.APPLICATION_WIDTH + info.WIDTH)/2. - (info.APPLICATION_WIDTH-info.WIDTH) * 0.6 / 2.,
				info.APPLICATION_HEIGHT - info.APPLICATION_HEIGHT * 0.2,
				(info.APPLICATION_WIDTH-info.WIDTH) * 0.6,
				info.APPLICATION_HEIGHT * 0.2);
		
		game.add(vaultImage);
	}
	
	//==================
	
	public void DisplayVisualBricks(BreakoutPro game, boolean remove)
	{
		for (int i = visualBricks.size(); i < VISUAL_BRICKS_COUNT; i++)
		{
			visualBricks.add(game.setup.CreateBrick(game, game.info, 1,
					RandomGenerator.getInstance().nextInt(game.info.APPLICATION_WIDTH),
					RandomGenerator.getInstance().nextInt(game.info.APPLICATION_HEIGHT)));
			
			if(i%10==0)
				Sleep(1);
		}
		
		if(remove)
		{
			for (int i = visualBricks.size()-1; i >= 0; i--)
			{
				game.remove(visualBricks.elementAt(i).image);
				
				if(i%10==0)
					Sleep(1);
			}
			
			visualBricks.removeAllElements();
		}
	}
	
	private void Appear(BreakoutPro game,GImage image)
	{
			game.add(image);
			for (int pauseSecond = 0; pauseSecond < 20; pauseSecond++,Sleep(pauseSecond*pauseSecond))
				image.setVisible(!image.isVisible());
			Sleep(5000);
	}
	
	private void Disappear(BreakoutPro game,GImage image)
	{
			DisplayVisualBricks(game,false);
			game.remove(image);
			DisplayVisualBricks(game,true);
	}
	
	public void RemoveEverythingExceptVisualBricks(BreakoutPro game)
	{
		DisplayVisualBricks(game,false);
		
		for (int i = 0; i < game.getElementCount()-VISUAL_BRICKS_COUNT; i++)
			game.getElement(i).setVisible(false);
		
		DisplayVisualBricks(game,true);
		
		game.removeAll();
	}
	
	//==================
	
	private void Sleep(int milliSeconds)
	{
		try 
		{
			Thread.sleep(milliSeconds);
		} 
		catch (InterruptedException e) {}
	}
}