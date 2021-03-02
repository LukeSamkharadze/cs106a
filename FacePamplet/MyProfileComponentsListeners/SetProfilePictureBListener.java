package MyProfileComponentsListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Facebrook;

public class SetProfilePictureBListener implements ActionListener
{
	private Facebrook facebrook;
	
	public SetProfilePictureBListener(Facebrook facebrook) 
	{
		this.facebrook = facebrook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		facebrook.GetSignedInProfile().SetprofilePicture(facebrook.GetMyProfileComponents().GetProfilePictureTFText());
		facebrook.SearchAndDisplayProfile(facebrook.GetSignedInProfile().GetFirstName(), facebrook.GetSignedInProfile().GetLastName());
		
		facebrook.getDialog().println("Profile Picture successfully updated");
		
		facebrook.UpdateDatabase();
	}
}