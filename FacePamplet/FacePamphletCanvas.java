/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants 
{
	private GLabel nameLabel;
	private GImage profilePicture;
	private GLabel statusLabel;
	
	private GLabel friendsTitleLabel;
	private GLabel friendsLabel;
	
	private GLabel messageLabel;
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() 
	{
		messageLabel = new GLabel("");
		messageLabel.setFont(MESSAGE_FONT);
		messageLabel.setLocation(
				getWidth()/2. - messageLabel.getWidth()/2.,
				getHeight() - BOTTOM_MESSAGE_MARGIN-messageLabel.getDescent());
		
		add(messageLabel);
	}

	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) 
	{
		messageLabel.setLabel(msg);
		messageLabel.setLocation(
				getWidth()/2. - messageLabel.getWidth()/2.,
				getHeight()-BOTTOM_MESSAGE_MARGIN-messageLabel.getDescent());
	}
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) 
	{
		removeAll();
		
		nameLabel = new GLabel(profile.getName());
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameLabel.setLocation(
				LEFT_MARGIN,
				TOP_MARGIN+nameLabel.getAscent());
		
		statusLabel = new GLabel(profile.getStatus());
		statusLabel.setFont(PROFILE_STATUS_FONT);
		statusLabel.setLocation(
				LEFT_MARGIN,
				TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN + IMAGE_WIDTH + STATUS_MARGIN + statusLabel.getAscent());
		
		friendsTitleLabel = new GLabel("Friends");
		friendsTitleLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		friendsTitleLabel.setLocation(
				getWidth()/2.,
				TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN - friendsTitleLabel.getDescent());
		
		friendsLabel = new GLabel("");
		Iterator<String> friends = profile.getFriends();
		while(friends.hasNext())
			friendsLabel.setLabel(friendsLabel.getLabel()+"\n"+friends.next());
		friendsLabel.setFont(PROFILE_FRIEND_FONT);
		friendsLabel.setLocation(				
				getWidth()/2.,
				TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN + friendsLabel.getAscent());
		
		profilePicture = profile.getImage();
		DrawProfilePicture(profilePicture);
		
		add(nameLabel);
		add(statusLabel);
		add(friendsTitleLabel);
		add(friendsLabel);
		add(messageLabel);
	}
	
	public void clear()
	{
		removeAll();
		add(messageLabel);
	}
	
	private void DrawProfilePicture(GImage image)
	{
		if(image == null)
		{
			GRect borderRect = new GRect(LEFT_MARGIN,TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN,IMAGE_WIDTH,IMAGE_HEIGHT);
			
			GLabel noImageLabel = new GLabel("No Image");
			noImageLabel.setFont(PROFILE_IMAGE_FONT);
			noImageLabel.setLocation(
					borderRect.getX() + borderRect.getWidth()/2. - noImageLabel.getWidth()/2.,
					borderRect.getY() + borderRect.getHeight()/2. - noImageLabel.getHeight()/2. + noImageLabel.getAscent());
			
			add(borderRect);
			add(noImageLabel);
		}
		else
		{
			image.setBounds(LEFT_MARGIN,TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN,IMAGE_WIDTH,IMAGE_HEIGHT);
			
			add(image);
		}
	}
}