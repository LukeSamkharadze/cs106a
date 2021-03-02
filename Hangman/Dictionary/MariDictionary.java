package Dictionary;

import acm.util.ErrorException;
import acm.util.RandomGenerator;

public class MariDictionary extends Dictionary
{
	@Override
	public String GetRandomWord() 
	{
		return GetWordFromFixedList(RandomGenerator.getInstance().nextInt(GetWordCountForFixedList()));
	}
	
	private int GetWordCountForFixedList()
	{
		return 9;
	}

	private String GetWordFromFixedList(int index) 
	{
		switch (index) 
		{
			case 0: return "SHAVIVARDEBI";
			case 1: return "WOOOW";
			case 2: return "XOOOM";
			case 3: return "AHAAM";
			case 4: return "WAVEDI";
			case 5: return "NUUUUU";
			case 6: return "ANUUUU";
			case 7: return "YAVAAAAAAAAAAAAAAAAAAA";
			case 8: return "MEGOBREBSHIWARMATEBEBI"; // LOL
			default: throw new ErrorException("getWord: Illegal index");
		}
	}
}
