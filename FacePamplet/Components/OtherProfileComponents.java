package Components;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import acm.gui.TableLayout;

public class OtherProfileComponents implements IComponents
{
	private JButton followB;
	
	//=============================================//
	
	public OtherProfileComponents(ActionListener followBL) 
	{
		followB = new JButton("Follow");
		followB.addActionListener(followBL);
	}
	
	public void AddToPanel(JPanel panel)
	{
		panel.setLayout(new TableLayout(1,1,10,10));
		
		panel.add(followB);
	}
}