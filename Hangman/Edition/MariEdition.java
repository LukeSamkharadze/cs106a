package Edition;

import Dictionary.MariDictionary;
import Drawer.MariDrawer;
import acm.program.GraphicsProgram;

public class MariEdition extends Edition
{
	public MariEdition(GraphicsProgram graphics) 
	{
		dictionary = new MariDictionary();
		drawer = new MariDrawer(graphics);
	}
}
