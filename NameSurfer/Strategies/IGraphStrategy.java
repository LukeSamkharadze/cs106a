package Strategies;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import Entry.Entry;
import acm.graphics.GCanvas;

public interface IGraphStrategy 
{
	public abstract void DisplayGraph(GCanvas canvas);
	
	public abstract void DisplayEntries(GCanvas canvas, Map<Entry,Color> entriesAndColors);
}