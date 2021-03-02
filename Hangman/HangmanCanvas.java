import java.awt.Font;

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas 
{
	private final int SCAFFOLD_HEIGHT = 360;
	private final int BEAM_LENGTH = 144;
	private final int ROPE_LENGTH = 18;
	private final int HEAD_RADIUS = 36;
	private final int BODY_LENGTH = 144;
	private final int ARM_OFFSET_FROM_HEAD = 28;
	private final int UPPER_ARM_LENGTH = 72;
	private final int LOWER_ARM_LENGTH = 44;
	private final int HIP_WIDTH = 36;
	private final int LEG_LENGTH = 108;
	private final int FOOT_LENGTH = 28;
	
	private final double yCenterOffset = 50;
	
	private double xCenter;
	private double yCenter;
	
	private GLabel currentlyGuessedSecretWord = new GLabel("");
	private GLabel triedLetters = new GLabel("");
	
	public void Reset(String secretWord) 
	{
		xCenter = getWidth()/2.;
		yCenter = getHeight()/2. - yCenterOffset;
		
		currentlyGuessedSecretWord.setFont(new Font("Arial",Font.PLAIN,25));
		triedLetters.setFont(new Font("Arial",Font.PLAIN,15));
		
		currentlyGuessedSecretWord.setLocation(20, getHeight()-60);
		triedLetters.setLocation(20,getHeight()-20);
		
		currentlyGuessedSecretWord.setLabel(secretWord);
		triedLetters.setLabel("");
		
		removeAll();
		
		add(currentlyGuessedSecretWord);
		add(triedLetters);
		
		DrawScaffold();
	}

	public void UpdateCurrentlyGuessedSecretWord(String secretWord) 
	{
		currentlyGuessedSecretWord.setLabel(secretWord);
	}

	public void AddToTriedLettersLabel(char letter) 
	{
		triedLetters.setLabel(triedLetters.getLabel() + letter);
		
		switch (triedLetters.getLabel().length()) 
		{
			case 1: DrawHead(); break;
			case 2: DrawBody(); break;
			case 3: DrawShoulders(); break;
			case 4: DrawFirstHand(); break;
			case 5: DrawSecondHand(); break;
			case 6: DrawHips(); break;
			case 7: DrawFirstLeg(); break;
			case 8: DrawSecondLeg(); break;
		}
	}
	
	//==============================//
	
	private void DrawScaffold()
	{
		add(new GLine(
				xCenter - BEAM_LENGTH,
				yCenter + SCAFFOLD_HEIGHT/2.,
				xCenter - BEAM_LENGTH,
				yCenter - SCAFFOLD_HEIGHT/2.));
		
		add(new GLine(
				xCenter - BEAM_LENGTH,
				yCenter - SCAFFOLD_HEIGHT/2.,
				xCenter,
				yCenter - SCAFFOLD_HEIGHT/2.));
		
		add(new GLine(
				xCenter,
				yCenter - SCAFFOLD_HEIGHT/2.,
				xCenter,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH));
	}

	//==============================//
	
	private void DrawHead()
	{
		add(new GOval(
				xCenter - HEAD_RADIUS,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH,
				HEAD_RADIUS*2,
				HEAD_RADIUS*2));
	}
	
	private void DrawBody()
	{
		add(new GLine(
				xCenter,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS,
				xCenter,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH));
	}

	private void DrawShoulders()
	{
		add(new GLine(
				xCenter - UPPER_ARM_LENGTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				xCenter + UPPER_ARM_LENGTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD));
	}
	
	private void DrawFirstHand()
	{
		add(new GLine(
				xCenter - UPPER_ARM_LENGTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				xCenter - UPPER_ARM_LENGTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));
	}

	private void DrawSecondHand()
	{
		add(new GLine(
				xCenter + UPPER_ARM_LENGTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				xCenter + UPPER_ARM_LENGTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));
	}
	
	private void DrawHips()
	{
		add(new GLine(
				xCenter - HIP_WIDTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH,
				xCenter + HIP_WIDTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH));
	}

	private void DrawFirstLeg()
	{
		add(new GLine(
				xCenter - HIP_WIDTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH,
				xCenter - HIP_WIDTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH));
		
		add(new GLine(
				xCenter - HIP_WIDTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
				xCenter - HIP_WIDTH - FOOT_LENGTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH));
	}
	
	private void DrawSecondLeg()
	{
		add(new GLine(
				xCenter + HIP_WIDTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH,
				xCenter + HIP_WIDTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH));
		
		add(new GLine(
				xCenter + HIP_WIDTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
				xCenter + HIP_WIDTH + FOOT_LENGTH,
				yCenter - SCAFFOLD_HEIGHT/2. + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH));
	}
}