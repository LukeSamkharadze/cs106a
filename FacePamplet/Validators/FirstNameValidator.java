package Validators;

public class FirstNameValidator implements IValidator
{
	@Override
	public boolean Validate(String firstName) throws Exception
	{
		if(IsUsingValidCharacters(firstName) == false)
			throw new Exception("First name must have only latin characters");
		
		return true;
	}
	
	private boolean IsUsingValidCharacters(String firstName)
	{
		for (int charIndex = 0; charIndex < firstName.length(); charIndex++)
			if(IsLatinCharacter(firstName.charAt(charIndex)) == false) 
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