package Components;

import Entry.Entry;
import Main.NameSurferPRO;

public class ClearButton extends Button
{
	public ClearButton(String text) 
	{
		super(text);
	}
	
	@Override
	public void ActionPerformed(NameSurferPRO nameSurferPro, Entry entry) 
	{
		nameSurferPro.RemoveAllEntries();
	}
}
