package Components;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import acm.gui.TableLayout;

public class RegisterComponents implements IComponents
{
	private JTextField firstNameTF;
	private JTextField lastNameTF;
	private JTextField eMailTF;
	private JTextField passwordTF;
	
	private JButton registerB;
	private JButton signInB;
	
	//=============================================//
	
	public RegisterComponents(ActionListener registerBL, ActionListener signInBL) 
	{
		firstNameTF = new JTextField(15);
		firstNameTF.addActionListener(registerBL);
		
		lastNameTF = new JTextField(15);
		lastNameTF.addActionListener(registerBL);
		
		eMailTF = new JTextField(15);
		eMailTF.addActionListener(registerBL);
		
		passwordTF = new JTextField(15);
		passwordTF.addActionListener(registerBL);
		
		registerB = new JButton("Register");
		registerB.addActionListener(registerBL);
		
		signInB = new JButton("Sign In");
		signInB.addActionListener(signInBL);
	}
	
	public void AddToPanel(JPanel panel)
	{
		panel.setLayout(new TableLayout(7,2,10,10));
		
		panel.add(new JLabel(""));
		panel.add(new JLabel("REGISTERING"));
		
		panel.add(new JLabel("Name: "));
		panel.add(firstNameTF);
		
		panel.add(new JLabel("Lastname: "));
		panel.add(lastNameTF);
		
		panel.add(new JLabel("eMail: "));
		panel.add(eMailTF);
		
		panel.add(new JLabel("Password: "));
		panel.add(passwordTF);
		
		panel.add(new JLabel(""));
		panel.add(registerB);
		
		panel.add(new JLabel("Have an account?"));
		panel.add(signInB);
	}
	
	public void ClearFields()
	{
		eMailTF.setText("");
		firstNameTF.setText("");
		lastNameTF.setText("");
		passwordTF.setText("");
	}
	
	//=============================================//
	
	public String GetFirstNameTFText()
	{
		return firstNameTF.getText();
	}
	
	public String GetLastNameTFText()
	{
		return lastNameTF.getText();
	}
	
	public String GetEMailTFText()
	{
		return eMailTF.getText();
	}
	
	public String GetPasswordTFText()
	{
		return passwordTF.getText();
	}
}
