package Canvas;

import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Iterator;

import Main.Facebrook;
import Main.Profile;
import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Canvas extends GCanvas implements ComponentListener, Constants
{
	private Facebrook facebrook;
	
	private GLabel nameLabel;
	private GImage profilePicture;
	private GLabel statusLabel;
	
	private GLabel followingTitleLabel;
	private GLabel followingLabel;
	
	//=============================================//
	
	public Canvas(Facebrook facebrook)
	{
		this.facebrook = facebrook;
		
		addComponentListener(this);
	}
	
	//=============================================//
	
	public void displayProfile(Profile profile) 
	{
		removeAll();

		if(profile == null)
			return;
		
		nameLabel = new GLabel(profile.GetFirstName() + " " + profile.GetLastName());
		nameLabel.setFont(new Font("Arial",Font.PLAIN,(int)(PROFILE_NAME_FONT * facebrook.getWidth())));
		nameLabel.setLocation(
				LEFT_MARGIN * facebrook.getWidth(),
				TOP_MARGIN * facebrook.getHeight() + nameLabel.getAscent());
		
		statusLabel = new GLabel(profile.GetStatus());
		statusLabel.setFont(new Font("Arial",Font.PLAIN,(int)(PROFILE_STATUS_FONT * facebrook.getWidth())));
		statusLabel.setLocation(
				LEFT_MARGIN * facebrook.getWidth(),
				TOP_MARGIN * facebrook.getHeight() + nameLabel.getHeight() +
				IMAGE_MARGIN * facebrook.getHeight() + IMAGE_HEIGHT * facebrook.getWidth() +
				STATUS_MARGIN * facebrook.getHeight() + statusLabel.getAscent());
		
		followingTitleLabel = new GLabel("Following");
		followingTitleLabel.setFont(new Font("Arial",Font.PLAIN,(int)(PROFILE_FRIEND_LABEL_FONT * facebrook.getWidth())));
		followingTitleLabel.setLocation(
				facebrook.getWidth()/2.,
				TOP_MARGIN * facebrook.getHeight() + nameLabel.getHeight() +
				IMAGE_MARGIN * facebrook.getHeight() - followingTitleLabel.getDescent());
		
		followingLabel = new GLabel("");
		Iterator<Profile> friends = profile.GetFollowing().iterator();
		while(friends.hasNext())
			followingLabel.setLabel(followingLabel.getLabel() + "\n" + friends.next().GetEMail());
		followingLabel.setFont(new Font("Arial",Font.PLAIN,(int)(PROFILE_FRIEND_FONT * facebrook.getWidth())));
		followingLabel.setLocation(				
				facebrook.getWidth()/2.,
				TOP_MARGIN * facebrook.getHeight() + nameLabel.getHeight() +
				IMAGE_MARGIN * facebrook.getHeight() + followingLabel.getAscent());
		
		try
		{
			profilePicture = new GImage(profile.GetProfilePicture());
		}
		catch (Exception e) 
		{
			profilePicture = new GImage("default.png");
		}
		
		DrawProfilePicture(profilePicture);
		
		add(nameLabel);
		add(statusLabel);
		add(followingTitleLabel);
		add(followingLabel);
	}
	
	private void DrawProfilePicture(GImage image)
	{
		if(image == null)
		{
			GRect borderRect = new GRect(
					LEFT_MARGIN * facebrook.getWidth(),
					TOP_MARGIN * facebrook.getHeight() + nameLabel.getHeight() + IMAGE_MARGIN * facebrook.getHeight(),
					IMAGE_WIDTH * facebrook.getWidth(),
					IMAGE_HEIGHT * facebrook.getWidth());
			
			GLabel noImageLabel = new GLabel("No Image");
			noImageLabel.setFont(new Font("Arial",Font.PLAIN,(int)(PROFILE_IMAGE_FONT * facebrook.getWidth())));
			noImageLabel.setLocation(
					borderRect.getX() + borderRect.getWidth()/2. - noImageLabel.getWidth()/2.,
					borderRect.getY() + borderRect.getHeight()/2. - noImageLabel.getHeight()/2. + noImageLabel.getAscent());
			
			add(borderRect);
			add(noImageLabel);
		}
		else
		{
			image.setBounds(
					LEFT_MARGIN * facebrook.getWidth(),
					TOP_MARGIN * facebrook.getHeight() + nameLabel.getHeight() + IMAGE_MARGIN * facebrook.getHeight(),
					IMAGE_WIDTH * facebrook.getWidth(),
					IMAGE_HEIGHT * facebrook.getWidth());
			
			add(image);
		}
	}

	@Override
	public void componentResized(ComponentEvent e) { displayProfile(facebrook.GetDisplayedProfile()); };

	@Override
	public void componentMoved(ComponentEvent e) { }

	@Override
	public void componentShown(ComponentEvent e) { }

	@Override
	public void componentHidden(ComponentEvent e)  { }
}
