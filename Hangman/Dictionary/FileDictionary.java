package Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import acm.util.RandomGenerator;

public class FileDictionary extends Dictionary
{
	private Vector<String> words = new Vector<String>();
	
	@Override
	public String GetRandomWord() 
	{
		return words.elementAt(RandomGenerator.getInstance().nextInt(words.size()));
	}
	
	public FileDictionary()
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while(true)
			{
				String line = bufferedReader.readLine();
				if(line == null)
					break;
				
				words.add(line);
			}
			bufferedReader.close();
		} catch (Exception ex) {}
	}
}
