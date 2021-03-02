package SignInComponentsListeners;

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
		try
		{
			facebrook.SignIn(facebrook.GetSignInComponents().GetEMailTFText(), facebrook.GetSignInComponents().GetPasswordTFText());
		} 
		catch (Exception ex)
		{
			facebrook.getDialog().showErrorMessage(ex.toString().substring(ex.toString().indexOf(' ')));
		}
	}
}