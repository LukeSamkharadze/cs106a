package Dictionary;

import acm.util.ErrorException;
import acm.util.RandomGenerator;

public class ScaryDictionary extends Dictionary
{
	@Override
	public String GetRandomWord() 
	{
		return GetWordFromFixedList(RandomGenerator.getInstance().nextInt(GetWordCountForFixedList()));
	}
	
	private int GetWordCountForFixedList()
	{
		return 5;
	}

	private String GetWordFromFixedList(int index) 
	{
		switch (index) 
		{
			case 0: return "WYEVLA";
			case 1: return "KIVILI";
			case 2: return "WIVILI";
			case 3: return "XERXI";
			case 4: return "IT";
			default: throw new ErrorException("getWord: Illegal index");
		}
	}
}
