package Edition;

import Dictionary.ScaryDictionary;
import Drawer.ScaryDrawer;
import acm.program.GraphicsProgram;

public class ScaryEdition extends Edition
{
	public ScaryEdition(GraphicsProgram graphics) 
	{
		dictionary = new ScaryDictionary();
		drawer = new ScaryDrawer(graphics);
	}
}
