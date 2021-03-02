package Components;

import Entry.Entry;
import Main.NameSurferPRO;
import Strategies.DiagramGraphStrategy;
import Strategies.LineGraphStrategy;

public class DiagramGraphButton extends Button
{
	public DiagramGraphButton(String text) 
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
		nameSurferPro.ChangeGraph(new DiagramGraphStrategy());
	}
}
