package RegisterComponentsListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Facebrook;

public class SignInBListener implements ActionListener
{
	private Facebrook facebrook;
	
	public SignInBListener(Facebrook facebrook)
	{
		this.facebrook = facebrook;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		facebrook.ChangePanel(facebrook.GetCenterPanel(), facebrook.GetSignInComponents());
	}
}
