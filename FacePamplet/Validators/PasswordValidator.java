package Validators;

public class PasswordValidator implements IValidator
{
	@Override
	public boolean Validate(String password) throws Exception
	{
		if(IsUsingValidCharacters(password) == false)
			throw new Exception("Password must have only latin and numerical characters");
		if(password.length() < 5)
			throw new Exception("Password must be at least 5 characters");
		if(password.equals(password.toLowerCase()))
			throw new Exception("Password must have at least 1 upper case character");
		if(password.equals(password.toUpperCase()))
			throw new Exception("Password must have at least 1 lower case character");
		if(IsUsingNumericalCharacter(password) == false)
			throw new Exception("Password must have at least 1 numerical character");
		if(IsUsingLatinCharacter(password) == false)
			throw new Exception("Password must have at least 1 latin character");
		
		return true;
	}
	
	//=====================================================//
	
	private boolean IsUsingValidCharacters(String password)
	{
		for (int charIndex = 0; charIndex < password.length(); charIndex++)
			if(IsLatinCharacter(password.charAt(charIndex)) == false && IsNumericalCharacter(password.charAt(charIndex)) == false) 
				return false;
		
		return true;
	}
	
	private boolean IsUsingNumericalCharacter(String password)
	{
		for (int charIndex = 0; charIndex < password.length(); charIndex++)
			if(IsNumericalCharacter(password.charAt(charIndex)) == true)
				return true;
		
		return false;
	}
	
	private boolean IsUsingLatinCharacter(String password)
	{
		for (int charIndex = 0; charIndex < password.length(); charIndex++)
			if(IsLatinCharacter(password.charAt(charIndex)) == true)
				return true;
		
		return false;
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