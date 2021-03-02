package Components;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import acm.gui.TableLayout;

public class MyProfileComponents implements IComponents
{
	private JTextField statusTF;
	private JButton setStatusB;
	
	private JTextField profilePictureTF;
	private JButton setProfilePictureB;
	
	private JButton signOutB;
	
	//=============================================//
	
	public MyProfileComponents(ActionListener setStatusBL, ActionListener setProfilePicturBL, ActionListener signOutBL) 
	{
		statusTF = new JTextField(10);
		statusTF.addActionListener(setStatusBL);
		
		setStatusB = new JButton("Set Status");
		setStatusB.addActionListener(setStatusBL);
		
		profilePictureTF = new JTextField(10);
		profilePictureTF.addActionListener(setProfilePicturBL);
		
		setProfilePictureB = new JButton("Set Profile Picture:");
		setProfilePictureB.addActionListener(setProfilePicturBL);
		
		signOutB = new JButton("Sign out");
		signOutB.addActionListener(signOutBL);
	}
	
	public void AddToPanel(JPanel panel)
	{
		panel.setLayout(new TableLayout(10,1,10,10));
		
		panel.add(new JLabel("Change Your Status:"));
		panel.add(statusTF);
		panel.add(setStatusB);
		
		panel.add(new JLabel(""));
		
		panel.add(new JLabel("Change Your Profile Picture:"));
		panel.add(profilePictureTF);
		panel.add(setProfilePictureB);
		
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		
		panel.add(signOutB);
	}
	
	//=============================================//
	
	public void ClearFields()
	{
		statusTF.setText("");
		profilePictureTF.setText("");
	}
	
	//=============================================//
	
	public String GetStatusTFText()
	{
		return statusTF.getText();
	}
	
	public String GetProfilePictureTFText()
	{
		return profilePictureTF.getText();
	}
}