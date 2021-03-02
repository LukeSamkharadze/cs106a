package Components;

import Entry.Entry;
import Main.NameSurferPRO;
import Strategies.LineGraphStrategy;
import Strategies.PieChartGraphStrategy;

public class LineGraphButton extends Button
{
	public LineGraphButton(String text) 
	{
		super(text);
	}
	
	@Override
	public void ActionPerformed(NameSurferPRO nameSurferPro, Entry entry)
	{
		if(entry==null)
			return;
		
		if(nameSurferPro.AddEntry(entry)==false)
			nameSurferPro.ChartIsFull();
		nameSurferPro.ChangeGraph(new LineGraphStrategy());
	}
}
