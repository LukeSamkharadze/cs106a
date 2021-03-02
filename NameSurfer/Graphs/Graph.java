package Graphs;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Constants.MainConstants;
import Entry.Entry;
import Strategies.IGraphStrategy;
import acm.graphics.GCanvas;

public class Graph extends GCanvas implements ComponentListener, MainConstants
{
	private Color[] colors = new Color[] { Color.pink,Color.orange,Color.magenta,Color.gray,Color.green,Color.cyan,Color.red,Color.blue,Color.DARK_GRAY,Color.yellow};
	
	private Map<Entry,Color> entriesAndColors = new HashMap<Entry,Color>();
	
	public IGraphStrategy graphStrategy;
	
	public Graph(IGraphStrategy graphStrategy) 
	{
		this.graphStrategy = graphStrategy;
		addComponentListener(this);
	}

	//====================================================//
	
	public boolean AddEntry(Entry entry)
	{
		if(entriesAndColors.size() >= MAX_ENTRIES)
			return false;
		
		entriesAndColors.put(entry,GetNonUsedColor());
		Update();
		
		return true;
	}

	public void RemoveEntry(Entry entry)
	{
		entriesAndColors.remove(entry);
		Update();
	}
	
	public void RemoveAllEntries() 
	{
		entriesAndColors.clear();
		Update();
	}

	//====================================================//
	
	public void Update() 
	{
		removeAll();
		graphStrategy.DisplayGraph(this);
		graphStrategy.DisplayEntries(this,entriesAndColors);
	}
	
	public void Update(ArrayList<Entry> entries) 
	{
		entriesAndColors.clear();
		
		for(Entry entry : entries)
			AddEntry(entry);
	}
	
	//====================================================//
	
	private Color GetNonUsedColor()
	{
		for (int colorID = 0; colorID < colors.length; colorID++) 
		{
			boolean flag = true;
			
			for(Color color : entriesAndColors.values())
				if(colors[colorID] == color)
				{
					flag = false;
					break;
				}
			
			if(flag)
				return colors[colorID];
		}
		
		return null;
	}
	
	//====================================================//
	
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { Update(); }
	public void componentShown(ComponentEvent e) { }
}