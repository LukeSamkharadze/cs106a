package Main;

import java.util.ArrayList;

import acm.graphics.GCanvas;

public class Profile
{
	private String eMail;
	private String firstName;
	private String lastName;
	private String password;
	private String status;
	private String profilePicture;
	
	private ArrayList<Profile> following;
	
	//=======================================================//
	
	public Profile(String eMail, String firstName, String lastName, String password, String status, String profilePicture, ArrayList<Profile> following) 
	{
		this.eMail = eMail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.status = status;
		this.profilePicture = profilePicture;
		
		this.following = following;
	}
	
	//=======================================================//
	
	public void Follow(Profile profile) throws Exception
	{
		if(following.contains(profile))
			throw new Exception("This person is already in your following list");
		
		following.add(profile);
	}
	
	//=======================================================//

	public void SetStatus(String status)
	{
		this.status = status;
	}
	
	public void SetprofilePicture(String profilePicture)
	{
		this.profilePicture = profilePicture;
	}
	
	//=======================================================//
	
	public String GetEMail()
	{
		return eMail;
	}
	
	public String GetFirstName()
	{
		return firstName;
	}
	
	public String GetLastName()
	{
		return lastName;
	}
	
	public String GetPassword()
	{
		return password;
	}
	
	public String GetStatus()
	{
		return status;
	}
	
	public String GetProfilePicture()
	{
		return profilePicture;
	}
	
	public ArrayList<Profile> GetFollowing()
	{
		return following;
	}
}