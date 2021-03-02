/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NameSurferDataBase implements NameSurferConstants 
{
	ArrayList<NameSurferEntry> data = new ArrayList<NameSurferEntry>();
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) 
	{
		LoadData(filename);
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) 
	{
		for (NameSurferEntry nameSurferEntry : data) 
			if(nameSurferEntry.getName().toLowerCase().equals(name.toLowerCase()))
				return nameSurferEntry;
		
		return null;
	}
	
	// Loading data into the arraylist
	private void LoadData(String filename)
	{
		 try 
		 {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
			while (true) 
			{
				String line = bufferedReader.readLine();
				if (line == null) break;
				data.add(new NameSurferEntry(line.substring(0, line.indexOf(' ')),ParseLine(line)));
			}
			bufferedReader.close();
		} 
		 catch(Exception ex) { }
	}
	
	// Parsing line and return decade ratings
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