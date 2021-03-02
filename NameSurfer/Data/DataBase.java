package Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Entry.Entry;

public class DataBase 
{
	ArrayList<Entry> data = new ArrayList<Entry>();
	
	public DataBase(String filename) 
	{
		LoadData(filename);
	}
	
	public Entry findEntry(String name) 
	{
		for (Entry nameSurferEntry : data) 
			if(nameSurferEntry.getName().toLowerCase().equals(name.toLowerCase()))
				return nameSurferEntry;
		
		return null;
	}
	
	private void LoadData(String filename)
	{
		 try 
		 {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
			while (true) 
			{
				String line = bufferedReader.readLine();
				if (line == null) break;
				data.add(new Entry(line.substring(0, line.indexOf(' ')),ParseLine(line)));
			}
			bufferedReader.close();
		} 
		 catch(Exception ex) { }
	}
	
	private int[] ParseLine(String line)
	{
		int[] ratings = new int[11];
		
		StringTokenizer stringTokenizer = new StringTokenizer(line," ");

		stringTokenizer.nextToken();
		
		for (int ratingsID = 0; ratingsID < ratings.length; ratingsID++) 
			ratings[ratingsID] = Integer.parseInt(stringTokenizer.nextToken());
		
		return ratings;
	}
}