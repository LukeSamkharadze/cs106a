package Other;

import java.util.Vector;

public class Other 
{
	public static String CurrentlyGuessedSecretWord(Vector<Character> charactersTried,String secretWord)
	{
		String currentlyGuessedSecretWord = "";
		
		for (int secretWordCharIndex = 0; secretWordCharIndex < secretWord.length(); secretWordCharIndex++)
		{
			if(charactersTried.contains(secretWord.charAt(secretWordCharIndex)) == true)
				currentlyGuessedSecretWord += secretWord.charAt(secretWordCharIndex);
			else
				currentlyGuessedSecretWord += "_";
			
			currentlyGuessedSecretWord += " ";
		}
		
		return currentlyGuessedSecretWord;
	}
	
	public static boolean IsCharacterAvailable(Vector<Character> charactersTried,char c)
	{
		if(charactersTried.contains(c) == true)
			return false;
		
		if((c > 64 && c < 91) || (c > 96 && c < 123))
			return true;
		
		return false;
	}

	public static boolean IsCharacterInSecretWord(String secretWord,char c)
	{
		for (int secretWordCharIndex = 0; secretWordCharIndex < secretWord.length(); secretWordCharIndex++)
			if(secretWord.charAt(secretWordCharIndex) == c)
				return true;
		
		return false;
	}
	
	public static boolean IsSecretWordGuessed(Vector<Character> charactersTried,String secretWord)
	{
		for (int secretWordCharIndex = 0; secretWordCharIndex < secretWord.length(); secretWordCharIndex++)
			if(charactersTried.contains(secretWord.charAt(secretWordCharIndex)) == false)
				return false;
		
		return true;
	}

}
