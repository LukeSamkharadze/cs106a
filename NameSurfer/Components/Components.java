package Components;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Components 
{
	public JLabel label = new JLabel("Name");
	public JTextField textField = new JTextField(15);
	
	public Button lineGraph = new LineGraphButton("Line Graph");
	public Button diagramGraph = new DiagramGraphButton("Diagram Graph");
	public Button pieChartGraph = new PieChartGraphButton("Pie Chart Graph");
	
	public Button remove = new RemoveButton("Remove");
	public Button clear = new ClearButton("Clear");
}
