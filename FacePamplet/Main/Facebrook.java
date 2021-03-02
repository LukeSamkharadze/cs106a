package Main;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import Canvas.*;
import Components.*;
import Validators.*;

import acm.program.Program;

public class Facebrook extends Program
{
	private SignInComponents signInComponents;
	private RegisterComponents registerComponents;
	private ActionBarComponents actionBarComponents;
	private MyProfileComponents myProfileComponents;
	private OtherProfileComponents otherProfileComponents;
	
	private JPanel centerPanel;
	private JPanel northPanel;
	private JPanel eastPanel;
	
	//=============================================//
	
	private Database database;
	private Canvas canvas;
	
	//=============================================//

	private Profile SignedInProfile;
	private Profile DisplayedProfile;
	
	//=============================================//
	
	public void init()
	{
		database = new Database();
		canvas = new Canvas(this);
		
		centerPanel = new JPanel();
		northPanel = new JPanel();
		eastPanel = new JPanel();
		
		signInComponents = new SignInComponents(
				new SignInComponentsListeners.SignInBListener(this),
				new SignInComponentsListeners.RegisterBListener(this));
		
		registerComponents = new RegisterComponents(
				new RegisterComponentsListeners.RegisterBListener(
						new EMailPValidator(), new PasswordValidator(), new FirstNameValidator(), new LastNameValidator(), this),
				new RegisterComponentsListeners.SignInBListener(this));
		
		actionBarComponents = new ActionBarComponents(
				new ActionBarComponentsListeners.SearchBListener(this),
				new ActionBarComponentsListeners.MyProfileBListener(this));
		
		myProfileComponents = new MyProfileComponents(
				new MyProfileComponentsListeners.SetStatusBListener(this),
				new MyProfileComponentsListeners.SetProfilePictureBListener(this),
				new MyProfileComponentsListeners.SignOutBListener(this));
		
		otherProfileComponents = new OtherProfileComponents(
				new OtherProfileComponentsListeners.FollowBListener(this));
	}
			
	public void run()
	{
		add(centerPanel);
		add(eastPanel,EAST);
		add(northPanel,NORTH);
		
		signInComponents.AddToPanel(centerPanel);
	}
	
	//=============================================//
	
	public void UpdateDatabase()
	{
		database.Update();
	}
	
	//=============================================//
	
	public void SignIn(String eMail, String password) throws Exception
	{
		Profile profile = database.GetProfileByPassword(eMail, password);
		
		if(profile == null)
			throw new Exception("We were not able to find your account");
		
		centerPanel.removeAll();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(canvas);
		
		ChangePanel(northPanel, actionBarComponents);
		ChangePanel(eastPanel, myProfileComponents);
		
		SignedInProfile = profile;
		DisplayedProfile = profile;
		canvas.displayProfile(profile);
	}
	
	public void SignOut()
	{
		northPanel.removeAll();
		eastPanel.removeAll();
		ChangePanel(centerPanel, signInComponents);
	}
	
	//=============================================//
	
	public void CreateNewProfile() throws Exception
	{
		if(database.GetProfile(registerComponents.GetEMailTFText()) != null)
			throw new Exception("That eMail is already used");
		
		Profile profile = new Profile(
				registerComponents.GetEMailTFText(),
				registerComponents.GetFirstNameTFText(),
				registerComponents.GetLastNameTFText(),
				registerComponents.GetPasswordTFText(),
				"Status has not been set",
				"default.png",
				new ArrayList<Profile>());
		
		database.AddProfileToDataBase(profile);
	}
	
	//=============================================//
	
	public void SearchAndDisplayProfile(String firstName, String lastName)
	{
		Profile profile = database.GetProfile(firstName, lastName);
		
		if(profile == null)
		{
			getDialog().showErrorMessage("Profile was not found");
			return;
		}
		
		DisplayedProfile = profile;

		if(SignedInProfile == profile)
			ChangePanel(eastPanel, myProfileComponents);
		else
			ChangePanel(eastPanel, otherProfileComponents);
		
		canvas.displayProfile(DisplayedProfile);
	}
	
	public void ChangePanel(JPanel panel, IComponents components)
	{
		ClearFields();
		
		panel.removeAll();
		
		if(components != null)
			components.AddToPanel(panel);
		
	    revalidate();
	    repaint();
	}
	 
	private void ClearFields()
	{
		registerComponents.ClearFields();
		signInComponents.ClearFields();
		myProfileComponents.ClearFields();
		actionBarComponents.ClearFields();
	}
	
	//=============================================//

	public Profile GetSignedInProfile()
	{
		return SignedInProfile;
	}
	
	public Profile GetDisplayedProfile()
	{
		return DisplayedProfile;
	}
	
	//=============================================//
	
	public SignInComponents GetSignInComponents()
	{
		return signInComponents;
	}
	
	public RegisterComponents GetRegisterComponents()
	{
		return registerComponents;
	}

	public ActionBarComponents GetActionBarComponents()
	{
		return actionBarComponents;
	}
	
	public MyProfileComponents GetMyProfileComponents()
	{
		return myProfileComponents;
	}
	
	//=============================================//
	
	public JPanel GetCenterPanel()
	{
		return centerPanel;
	}
	
	public JPanel GetNorthPanel()
	{
		return northPanel;
	}
	
	public JPanel GetEastPanel()
	{
		return eastPanel;
	}
}