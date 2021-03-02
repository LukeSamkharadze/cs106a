package Dealer;

import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class DealerSituations 
{
	public static void Greet(GraphicsProgram graphics, Dealer dealer, String playerNames[], IODialog dialog)
	{

		WriteTextAndWait(graphics, dealer, "WELCOMEE...");
		WriteTextAndWait(graphics, dealer, "Welcome to casino \"MARIKUNA\"...");
		WriteTextAndWait(graphics, dealer, "My name is " + playerNames[1] + "...");
		WriteTextAndWait(graphics, dealer, "Whats your name???");

		playerNames[0] = dialog.readLine("Enter your name");

		WriteTextAndWait(graphics,dealer,"Nice to meet you " + playerNames[0] + "...");
		WriteTextAndWait(graphics,dealer,"I am sorry, but...");
	 	WriteTextAndWait(graphics,dealer,"We have only one game available...");
		WriteTextAndWait(graphics,dealer,"and I am all alone here...");
		WriteTextAndWait(graphics,dealer,"You will have to play with me...");
		WriteTextAndWait(graphics,dealer,"Dont worry, It is YAHTZEE...");
		WriteTextAndWait(graphics,dealer,"Lets play and taste some defeat!!!...");
		 
		dealer.Disappear(graphics.getGCanvas());
	}

	public static void Win(GraphicsProgram graphics, Dealer dealer) 
	{
		WriteTextAndWait(graphics, dealer, "FOOOOOO...");
		WriteTextAndWait(graphics, dealer, "SUCH A LOSER...");
		WriteTextAndWait(graphics, dealer, "GET OUT!!!...");
		dealer.Disappear(graphics.getGCanvas());
	}

	public static void Lose(GraphicsProgram graphics, Dealer dealer)
	{
		WriteTextAndWait(graphics, dealer, "WOOW...");
		WriteTextAndWait(graphics, dealer, "You are such a beast...");
		WriteTextAndWait(graphics, dealer, "Stay and play some more with me...");
		dealer.Disappear(graphics.getGCanvas());
	}

	public static void Draw(GraphicsProgram graphics, Dealer dealer) 
	{
		WriteTextAndWait(graphics, dealer, "Okayy...");
		WriteTextAndWait(graphics, dealer, "You are playing good...");
		WriteTextAndWait(graphics, dealer, "Lets play one more game...");
		dealer.Disappear(graphics.getGCanvas());
	}

	public static void NiceMove(GraphicsProgram graphics, Dealer dealer)
	{
		int randomNumber = RandomGenerator.getInstance().nextInt(5);
		
		if(randomNumber==0)
			WriteTextAndWait(graphics, dealer, "OMG, LUCKY ROLL!!!...");
		else if(randomNumber==1)
			WriteTextAndWait(graphics, dealer, "YOU ARE LUCKY ONE!!!...");
		
		dealer.Disappear(graphics.getGCanvas());
	}
	
	public static void BadMove(GraphicsProgram graphics, Dealer dealer)
	{
		int randomNumber = RandomGenerator.getInstance().nextInt(5);
		
		if(randomNumber==0)
			WriteTextAndWait(graphics, dealer, "LOL, YOU SUCK!!!...");
		else if(randomNumber==1)
			WriteTextAndWait(graphics, dealer, "AHAHAHA!!!...");
		
		dealer.Disappear(graphics.getGCanvas());
	}
	
	public static void YAHTZE(GraphicsProgram graphics, Dealer dealer)
	{
		WriteTextAndWait(graphics, dealer, "AHHHHHHHHHHH!!!...");
		WriteTextAndWait(graphics, dealer, "ITS NOT FAIR!!!...");
		
		dealer.Disappear(graphics.getGCanvas());
	}
	
	public static void Cheating1(GraphicsProgram graphics, Dealer dealer)
	{
		WriteTextAndWait(graphics, dealer, "HEY, WHAT ARE YOU DOING???...");
		WriteTextAndWait(graphics, dealer, "ARE YOU TRYING TO CHEAT???...");
		WriteTextAndWait(graphics, dealer, "I AM WATCHING YOU!!!...");
		dealer.Disappear(graphics.getGCanvas());
	}

	public static void Cheating2(GraphicsProgram graphics, Dealer dealer) 
	{
		WriteTextAndWait(graphics, dealer, "I HAVE ALREADY TOLD YOU!!!...");
		WriteTextAndWait(graphics, dealer, "GET OUT!!! YOU CHEATER!!!...");
		dealer.Disappear(graphics.getGCanvas());
	}

	public static void Speak(GraphicsProgram graphics, Dealer dealer, String text) {
		WriteTextAndWait(graphics, dealer, text);
		dealer.Disappear(graphics.getGCanvas());
	}

	private static void WriteTextAndWait(GraphicsProgram graphics, Dealer dealer, String text) {
		dealer.Speak(graphics.getGCanvas(), text);
		graphics.waitForClick();
	}
}