import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import acm.util.*;

public class HangmanLexicon
{
	Vector<String> lexiconWords = new Vector<String>();
	
	public HangmanLexicon()
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while(true)
			{
				String line = bufferedReader.readLine();
				if(line == null)
					break;
				
				lexiconWords.add(line);
			}
			bufferedReader.close();
		} catch (Exception ex) {}
	}
	
	//========================================//
	
	public String GetRandomWordFromLexicon()
	{
		return lexiconWords.elementAt(RandomGenerator.getInstance().nextInt(lexiconWords.size()));
	}
	
	//========================================//
	
	public String GetRandomWordFromFixedList()
	{
		return GetWordFromFixedList(RandomGenerator.getInstance().nextInt(GetWordCountForFixedList()));
	}
	
	public int GetWordCountForFixedList()
	{
		return 10;
	}

	public String GetWordFromFixedList(int index) 
	{
		switch (index) 
		{
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}
	};
}
