package MyProfileComponentsListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Facebrook;

public class SetStatusBListener implements ActionListener
{
	private Facebrook facebrook;
	
	public SetStatusBListener(Facebrook facebrook) 
	{
		this.facebrook = facebrook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		facebrook.GetSignedInProfile().SetStatus(facebrook.GetMyProfileComponents().GetStatusTFText());
		facebrook.SearchAndDisplayProfile(facebrook.GetSignedInProfile().GetFirstName(), facebrook.GetSignedInProfile().GetLastName());
		
		facebrook.getDialog().println("Status successfully updated");
		
		facebrook.UpdateDatabase();
	}
}