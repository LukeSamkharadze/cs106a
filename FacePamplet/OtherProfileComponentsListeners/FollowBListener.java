package OtherProfileComponentsListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Facebrook;

public class FollowBListener implements ActionListener
{
	private Facebrook facebrook;
	
	public FollowBListener(Facebrook facebrook)
	{
		this.facebrook = facebrook;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			facebrook.GetSignedInProfile().Follow(facebrook.GetDisplayedProfile());
			facebrook.UpdateDatabase();
			facebrook.getDialog().println("Successfully followed");
		} 
		catch (Exception ex)
		{
			facebrook.getDialog().showErrorMessage(ex.toString().substring(ex.toString().indexOf(' ')));
		}
	}
}