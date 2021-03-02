package Components;

import Entry.Entry;
import Main.NameSurferPRO;

public class RemoveButton extends Button
{
	public RemoveButton(String text) 
	{
		super(text);
	}
	
	@Override
	public void ActionPerformed(NameSurferPRO nameSurferPro, Entry entry) 
	{
		nameSurferPro.RemoveEntry(entry);
	}
}
