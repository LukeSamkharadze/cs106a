package RegisterComponentsListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Facebrook;
import Validators.IValidator;

public class RegisterBListener implements ActionListener
{
	private Facebrook facebrook;
	
	private IValidator eMailValidator;
	private IValidator passwordValidator;
	private IValidator firstNameValidator;
	private IValidator lastNameValidator;
	
	public RegisterBListener(
			IValidator eMailValidator,
			IValidator passwordValidator,
			IValidator firstNameValidator,
			IValidator lastNameValidator,
			Facebrook facebrook)
	{
		this.facebrook = facebrook;
		
		this.eMailValidator = eMailValidator;
		this.passwordValidator = passwordValidator;
		this.firstNameValidator = firstNameValidator;
		this.lastNameValidator = lastNameValidator;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			passwordValidator.Validate(facebrook.GetRegisterComponents().GetPasswordTFText());
			eMailValidator.Validate(facebrook.GetRegisterComponents().GetEMailTFText());
			firstNameValidator.Validate(facebrook.GetRegisterComponents().GetFirstNameTFText());
			lastNameValidator.Validate(facebrook.GetRegisterComponents().GetLastNameTFText());
			
			facebrook.CreateNewProfile();
			facebrook.getDialog().println("Successfully created your account");
			facebrook.ChangePanel(facebrook.GetCenterPanel(), facebrook.GetSignInComponents());
		} 
		catch (Exception ex)
		{
			facebrook.getDialog().showErrorMessage(ex.toString().substring(ex.toString().indexOf(' ')));
		}
	}
}