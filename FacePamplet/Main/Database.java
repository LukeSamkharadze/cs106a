package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Database
{
	private static final String DATA_FOLDER = "data.txt";
	
	private ArrayList<Profile> profiles;
	
	//=======================================================//
	
	public Database() 
	{
		profiles = new ArrayList<>();
		
		AddProfilesToProfiles();
	}
	
	//=======================================================//
	
	public void Update()
	{
		try
		{
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File(DATA_FOLDER), false));
			
			for(Profile profile : profiles)
			{
				String friendEMails = "";
				
				writer.print(profile.GetEMail() + "~");
				writer.print(profile.GetFirstName() + "~");
				writer.print(profile.GetLastName() + "~");
				writer.print(profile.GetPassword() + "~");
				writer.print(profile.GetStatus() + "~");
				writer.print(profile.GetProfilePicture() + "~");
				
				for(int friendID = 0; friendID < profile.GetFollowing().size() - 1; friendID++)
					friendEMails += profile.GetFollowing().get(friendID).GetEMail() + " ";
				if(profile.GetFollowing().isEmpty() == false)
					friendEMails += profile.GetFollowing().get(profile.GetFollowing().size() - 1).GetEMail();
				
				writer.print(friendEMails);
				
				writer.println();
			}
			
			writer.close();
		}
		catch (Exception e) { System.out.println(e.toString());}
	}
	
	//=======================================================//
	
	public Profile GetProfile(String eMail)
	{
		for(Profile profile : profiles)
			if(profile.GetEMail().equals(eMail))
				return profile;
			
		return null;
	}
	
	public Profile GetProfile(String firstName, String lastName)
	{
		for(Profile profile : profiles)
			if(profile.GetFirstName().equals(firstName) && profile.GetLastName().contentEquals(lastName))
				return profile;
			
		return null;
	}
	
	public Profile GetProfileByPassword(String eMail, String password)
	{
		for(Profile profile : profiles)
			if(profile.GetEMail().equals(eMail) && profile.GetPassword().equals(password))
				return profile;
			
		return null;
	}
	
	//=======================================================//
	
	public void AddProfileToDataBase(Profile profile)
	{
		profiles.add(profile);
		Update();
	}
	
	//=======================================================//
	
	private void AddProfilesToProfiles()
	{
		for(String profileInfo : GetProfilesInfo())
			AddProfileToProfilesWithoutFriends(profileInfo);
		
		AddFriendsToProfiles();
	}
	
	private void AddProfileToProfilesWithoutFriends(String profileInfo)
	{
		String[] cutProfileInfo = profileInfo.split("~");
		
		String eMail = cutProfileInfo[0];
		String firstName = cutProfileInfo[1];
		String lastName = cutProfileInfo[2];
		String password = cutProfileInfo[3];
		String status = cutProfileInfo[4];
		String profilePicture = cutProfileInfo[5];
		
		Profile profile = new Profile(eMail, firstName, lastName, password, status, profilePicture, new ArrayList<Profile>());
		profiles.add(profile);
	}
	
	public void AddFriendsToProfiles()
	{
		for(String profileInfo : GetProfilesInfo())
		{
			String[] cutProfileInfo = profileInfo.split("~");
			
			if(cutProfileInfo.length < 7)
				continue;
			
			Profile currentProfile = GetProfile(cutProfileInfo[0]);
			ArrayList<Profile> friends = GetProfilesFromEMails(cutProfileInfo[6].split(" "));
			
			for(Profile friend : friends)
				try { currentProfile.Follow(friend); } catch(Exception ex) { }
		}
	}

	//=======================================================//
	
	private ArrayList<String> GetProfilesInfo()
	{
		ArrayList<String> profilesInfo = new ArrayList<String>();
		
		try 
		 {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_FOLDER));
			while (true) 
			{
				String line = bufferedReader.readLine();
				if (line == null) break;
				profilesInfo.add(line);
			}
			bufferedReader.close();
		} 
		 catch(Exception ex) { }
		
		return profilesInfo;
	}

	private ArrayList<Profile> GetProfilesFromEMails(String[] eMails)
	{
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		
		for (int eMailID = 0; eMailID < eMails.length; eMailID++)
			profiles.add(GetProfile(eMails[eMailID]));
		
		return profiles;
	}
}