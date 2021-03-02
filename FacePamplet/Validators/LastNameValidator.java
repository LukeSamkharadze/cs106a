package Validators;

public class LastNameValidator implements IValidator
{
	@Override
	public boolean Validate(String lastName) throws Exception
	{
		if(IsUsingValidCharacters(lastName) == false)
			throw new Exception("Last name must have only latin characters");
		
		return true;
	}
	
	private boolean IsUsingValidCharacters(String lastName)
	{
		for (int charIndex = 0; charIndex < lastName.length(); charIndex++)
			if(IsLatinCharacter(lastName.charAt(charIndex)) == false) 
				return false;
		
		return true;
	}
	
	private boolean IsLatinCharacter(char character)
	{
		if((character > 64 && character < 91) || (character > 96 && character < 123))
			return true;
		
		return false;
	}
}