package Components;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import acm.gui.TableLayout;

public class ActionBarComponents implements IComponents
{
	private JTextField searchTF;
	
	private JButton searchB;
	private JButton myProfileB;
	
	//=============================================//
	
	public ActionBarComponents(ActionListener searchBL, ActionListener  myProfileBL) 
	{
		searchTF = new JTextField(15);
		searchTF.addActionListener(searchBL);
		
		searchB = new JButton("Search");
		searchB.addActionListener(searchBL);
		
		myProfileB = new JButton("My Profile");
		myProfileB.addActionListener(myProfileBL);
	}
	
	//=============================================//
	
	public void AddToPanel(JPanel panel)
	{
		panel.setLayout(new TableLayout(1,5,10,0));
		
		panel.add(new JLabel("Search: "));
		panel.add(searchTF);
		panel.add(searchB);
		panel.add(myProfileB);
	}
	
	//=============================================//

	public void ClearFields()
	{
		searchTF.setText("");
	}
	
	//=============================================//
	
	public String GetSearchTFText()
	{
		return searchTF.getText();
	}
}