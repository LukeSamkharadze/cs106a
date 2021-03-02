package ActionBarComponentsListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Facebrook;

public class MyProfileBListener implements ActionListener
{
	private Facebrook facebrook;
	
	public MyProfileBListener(Facebrook facebrook) 
	{
		this.facebrook = facebrook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		facebrook.SearchAndDisplayProfile(facebrook.GetSignedInProfile().GetFirstName(), facebrook.GetSignedInProfile().GetLastName());
	}
}