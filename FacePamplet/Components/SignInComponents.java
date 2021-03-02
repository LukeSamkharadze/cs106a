package Components;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import acm.gui.TableLayout;

public class SignInComponents implements IComponents
{
	private JTextField eMailTF;
	private JTextField passwordTF;
	
	private JButton signInB;
	private JButton registerB;
	
	//=============================================//
	
	public SignInComponents(ActionListener signInBL, ActionListener registerBL) 
	{
		eMailTF = new JTextField(15);
		eMailTF.addActionListener(signInBL);
		
		passwordTF = new JTextField(15);
		passwordTF.addActionListener(signInBL);
		
		signInB = new JButton("Sign in");
		signInB.addActionListener(signInBL);
		
		registerB = new JButton("Register");
		registerB.addActionListener(registerBL);
	}
	
	public void AddToPanel(JPanel panel)
	{
		panel.setLayout(new TableLayout(5,2,10,10));
		
		panel.add(new JLabel(""));
		panel.add(new JLabel("SIGNING IN"));
		
		panel.add(new JLabel("eMail:"));
		panel.add(eMailTF);
		
		panel.add(new JLabel("Password:"));
		panel.add(passwordTF);
		
		panel.add(new JLabel(""));
		panel.add(signInB);
		
		panel.add(new JLabel("No account?"));
		panel.add(registerB);
	}
	
	public void ClearFields()
	{
		eMailTF.setText("");
		passwordTF.setText("");
	}
	
	//=============================================//
	
	public String GetEMailTFText()
	{
		return eMailTF.getText();
	}
	
	public String GetPasswordTFText()
	{
		return passwordTF.getText();
	}
}