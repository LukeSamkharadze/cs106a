package Components;

import javax.swing.JButton;

import Entry.Entry;
import Graphs.Graph;
import Main.NameSurferPRO;

public abstract class Button extends JButton
{
	public Button(String text) 
	{
		super(text);
	}

	public abstract void ActionPerformed(NameSurferPRO nameSurferPro, Entry entry);
}
