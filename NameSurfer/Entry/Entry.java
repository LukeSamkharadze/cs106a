package Entry;

public class Entry 
{
	private String name;
	int[] ratings;
	
	public Entry(String name, int[] ratings)
	{
		this.name = name;
		this.ratings = ratings;
	}

	public String getName() 
	{
		return name;
	}

	public int getRank(int decade)
	{
		return ratings[decade];
	}
}