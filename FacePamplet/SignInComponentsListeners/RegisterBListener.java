package SignInComponentsListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Facebrook;

public class RegisterBListener implements ActionListener
{
	private Facebrook facebrook;
	
	public RegisterBListener(Facebrook facebrook) 
	{
		this.facebrook = facebrook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		facebrook.ChangePanel(facebrook.GetCenterPanel(),facebrook.GetRegisterComponents());
	}
}
