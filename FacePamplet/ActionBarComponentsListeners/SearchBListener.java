package ActionBarComponentsListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Facebrook;

public class SearchBListener implements ActionListener
{
	private Facebrook facebrook;
	
	public SearchBListener(Facebrook facebrook) 
	{
		this.facebrook = facebrook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		String[] search = facebrook.GetActionBarComponents().GetSearchTFText().split(" ");
		
		if(search.length != 2)
		{
			facebrook.getDialog().showErrorMessage("Profile was not found");
			return;
		}
		
		facebrook.SearchAndDisplayProfile(search[0],search[1]);
	}
}
