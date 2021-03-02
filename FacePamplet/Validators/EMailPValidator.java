package Validators;

public class EMailPValidator implements IValidator
{
	@Override
	public boolean Validate(String eMail) throws Exception
	{
		if(IsUsingValidCharacters(eMail) == false)
			throw new Exception("eMail must use only latin, numerical, at and dot characters");
		if(IsUsingAtCharacterOnce(eMail) == false)
			throw new Exception("eMail must use character \"@\" only once");
		if(IsUsingValidDotPlacements(eMail))
			throw new Exception("eMail must use valid dot placements");

		return true;
	}
	
	//=====================================================//
	
	private boolean IsUsingValidCharacters(String eMail)
	{
		for (int charIndex = 0; charIndex < eMail.length(); charIndex++)
			if(
					IsLatinCharacter(eMail.charAt(charIndex)) == false &&
					IsNumericalCharacter(eMail.charAt(charIndex)) == false &&
					eMail.charAt(charIndex) != '@' &&
					eMail.charAt(charIndex) != '.')
				return false;
		
		return true;
	}
	
	private boolean IsUsingAtCharacterOnce(String eMail)
	{
		int characterCounter = 0;
		for (int charIndex = 0; charIndex < eMail.length(); charIndex++)
			if(eMail.charAt(charIndex) == '@')
				if(characterCounter++ == 1)
					return false;
		
		return true;
	}
	
	private boolean IsUsingValidDotPlacements(String eMail)
	{
		for (int charIndex = 0; charIndex < eMail.length(); charIndex++)
			if(eMail.charAt(charIndex) == '.')
				if(charIndex == 0 || charIndex == eMail.length()-1)
					return true;
				else if(IsLatinCharacter(eMail.charAt(charIndex - 1)) == false || IsLatinCharacter(eMail.charAt(charIndex + 1)) == false)
					return true;
		
		if(eMail.substring(eMail.indexOf('@')).contains("."))
			return false;
		
		return true;
	}

	private boolean IsLatinCharacter(char character)
	{
		if((character > 64 && character < 91) || (character > 96 && character < 123))
			return true;
		
		return false;
	}
	
	private boolean IsNumericalCharacter(char character)
	{
		if(character > 47 && character < 58)
			return true;
		
		return false;
	}
}