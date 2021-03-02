package MyProfileComponentsListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Facebrook;

public class SignOutBListener implements ActionListener
{
	private Facebrook facebrook;
	
	public SignOutBListener(Facebrook facebrook) 
	{
		this.facebrook = facebrook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		facebrook.SignOut();
		facebrook.getDialog().println("Successfully signed out");
	}
}