package Components;

import javax.swing.JButton;

import Entry.Entry;
import Main.NameSurferPRO;
import Strategies.PieChartGraphStrategy;

public class PieChartGraphButton extends Button
{
	public PieChartGraphButton(String text) 
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
		nameSurferPro.ChangeGraph(new PieChartGraphStrategy());
	}
}