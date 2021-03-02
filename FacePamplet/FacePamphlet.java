/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants 
{
	private JLabel nameFieldLabel = new JLabel("Name");
	private JTextField nameField = new JTextField(TEXT_FIELD_SIZE);
	
	private JButton createAccountButton = new JButton("Add");
	private JButton deleteAccountButton = new JButton("Delete");
	private JButton lookupAccountButton = new JButton("Lookup");
	
	private JTextField statusField = new JTextField(TEXT_FIELD_SIZE);
	private JButton changeStatusButton = new JButton("Change Status");
	private JLabel emptyLabel1 = new JLabel(EMPTY_LABEL_TEXT); 	//===========================//
	private JTextField pictureField = new JTextField(TEXT_FIELD_SIZE);
	private JButton changePictureButton = new JButton("Change Picture");
	private JLabel emptyLabel2 = new JLabel(EMPTY_LABEL_TEXT); 	//===========================//
	private JTextField friendField = new JTextField(TEXT_FIELD_SIZE);
	private JButton addFriendButton = new JButton("Add Friend");
	
	//====================================================================//
	
	private FacePamphletCanvas canvas = new FacePamphletCanvas();
	private FacePamphletDatabase dataBase = new FacePamphletDatabase();
	
	private FacePamphletProfile profileSelected;
	
	//====================================================================//
	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() 
	{
		add(nameFieldLabel, NORTH);
		add(nameField, NORTH);
		add(createAccountButton, NORTH);
		add(deleteAccountButton, NORTH);
		add(lookupAccountButton, NORTH);
		
		add(statusField, WEST);
		statusField.addActionListener(this);
		
		add(changeStatusButton, WEST);
		add(emptyLabel1, WEST);
		add(pictureField, WEST);
		pictureField.addActionListener(this);
		
		add(changePictureButton, WEST);
		add(emptyLabel2, WEST);
		add(friendField, WEST);
		friendField.addActionListener(this);
		
		add(addFriendButton, WEST);
		
		add(canvas, CENTER);
		
		addActionListeners();
    }
    
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) 
    {
    	if(e.getSource() == createAccountButton)
    		CreateButtonResponse(dataBase.getProfile(nameField.getText()));
    	else if(e.getSource() == deleteAccountButton)
    		DeleteButtonResponse(dataBase.getProfile(nameField.getText()));
    	else if(e.getSource() == lookupAccountButton)
    		LookupButtonResponse(dataBase.getProfile(nameField.getText()));
    	else if(e.getSource() == changeStatusButton || e.getSource() == statusField)
    		ChangeStatusResponse();
    	else if(e.getSource() == changePictureButton || e.getSource() == pictureField)
    		ChangePictureResponse();
    	else if(e.getSource() == addFriendButton || e.getSource() == friendField)
    		AddFriendResponse();
    	
    	if(profileSelected == null)
    		return;
    	
    	if(dataBase.getProfile(profileSelected.getName()) == null)
    		canvas.clear();
    	else
    		canvas.displayProfile(profileSelected);
	}
    
    private void CreateButtonResponse(FacePamphletProfile profile)
    {
    	if(profile == null)
		{
			FacePamphletProfile newProfile = new FacePamphletProfile(nameField.getText());
			
			dataBase.addProfile(newProfile);
			profileSelected = newProfile;
			canvas.showMessage("New profile created");
		}
		else
			canvas.showMessage("Account already exists");
    }
    
    private void DeleteButtonResponse(FacePamphletProfile profile)
    {
		if(profile == null)
			canvas.showMessage("Profile does not exist");
		else
		{
    		dataBase.deleteProfile(nameField.getText());
    		canvas.showMessage("Profile of " + nameField.getText() + " deleted");
		}
    }

    private void LookupButtonResponse(FacePamphletProfile profile)
    {
    	if(profile == null)
			canvas.showMessage("A profile with the name " + nameField.getText() + " does not exist");
		else
		{
			profileSelected = profile;
			canvas.showMessage("Displaying " + profile.getName());
		}
    }

    private void ChangeStatusResponse()
    {
    	if(profileSelected == null)
    	{
			canvas.showMessage("Please select a profile to change status");
			return;
    	}
    	
		profileSelected.setStatus(statusField.getText());
		canvas.showMessage("Status updated to " + statusField.getText());
    }

    private void ChangePictureResponse()
    {
    	if(profileSelected == null)
    	{
			canvas.showMessage("Please select a profile to upload picture");
			return;
    	}
    	
    	try
		{
			profileSelected.setImage(new GImage(pictureField.getText()));
			canvas.showMessage("Picture updated");
		}
		catch (Exception ex) 
		{
			canvas.showMessage("Picture was not found");
		}
    }

    private void AddFriendResponse()
    {
    	if(profileSelected == null)
    	{
			canvas.showMessage("Please select a profile to add friend");
			return;
    	}
    	
    	FacePamphletProfile friendProfile = dataBase.getProfile(friendField.getText());
		
		if(friendProfile == null)
			canvas.showMessage("Profile does not exist");
		else if(profileSelected == friendProfile)
			canvas.showMessage("You can not add yourself as a friend");
		else
		{
			if(profileSelected.addFriend(friendField.getText()))
			{
				friendProfile.addFriend(profileSelected.getName());
    			canvas.showMessage(friendProfile.getName() + " added as a friend");
			}
			else
				canvas.showMessage(profileSelected.getName() + " and " + friendProfile.getName() + " are already friends");
		}
    }
}