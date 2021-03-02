/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */
import acm.graphics.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener 
{
	private ArrayList<NameSurferEntry> entriesDisplayed = new ArrayList<NameSurferEntry>();
	
	private Color[] colors = new Color[] {Color.BLACK,Color.RED,Color.BLUE,Color.GREEN};
	private int colorID = 0;
	
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() 
	{
		addComponentListener(this);
		DrawGraph();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void Clear() 
	{
		entriesDisplayed.clear();
		Reset();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void AddEntry(NameSurferEntry entry) 
	{
		entriesDisplayed.add(entry);
		DisplayEntry(entry,colors[colorID++ % colors.length]);
	}
	
	// Displaying specific entry according to color specified
	private void DisplayEntry(NameSurferEntry entry,Color color)
	{
		for (int columnID = 0; columnID < NDECADES-1; columnID++)
		{
			GLine line = new GLine(
					columnID*getWidth()/(double)NDECADES,
					GRAPH_MARGIN_SIZE+(getHeight()-2*GRAPH_MARGIN_SIZE)*((((entry.getRank(columnID)==0)? MAX_RANK:entry.getRank(columnID))-1)/((double)MAX_RANK-1)),
					(columnID+1)*getWidth()/(double)NDECADES,
					GRAPH_MARGIN_SIZE+(getHeight()-2*GRAPH_MARGIN_SIZE)*((((entry.getRank(columnID+1)==0)? MAX_RANK:entry.getRank(columnID+1))-1)/((double)MAX_RANK-1)));
			
			GLabel label = new GLabel(entry.getName() + " " + ((entry.getRank(columnID)==0)? "*" : entry.getRank(columnID)));
			label.setLocation(
					columnID*getWidth()/(double)NDECADES + 3,
					GRAPH_MARGIN_SIZE+(getHeight()-2*GRAPH_MARGIN_SIZE)*((((entry.getRank(columnID)==0)? MAX_RANK:entry.getRank(columnID))-1)/((double)MAX_RANK-1)));
			
			label.setColor(color);
			line.setColor(color);
			
			add(line);
			add(label);
		}
		
		GLabel label = new GLabel(entry.getName() + " " + ((entry.getRank(NDECADES-1)==0)? "*" : entry.getRank(NDECADES-1)));
		label.setLocation(
				3+10*getWidth()/(double)NDECADES,
				GRAPH_MARGIN_SIZE+(getHeight()-2*GRAPH_MARGIN_SIZE)*((((entry.getRank(NDECADES-1)==0)? MAX_RANK:entry.getRank(NDECADES-1))-1)/((double)MAX_RANK-1)));
		
		label.setColor(color);
		add(label);
	}
	
	// Drawing graph
	private void DrawGraph()
	{
		for (int columnID = 0; columnID < NDECADES; columnID++)
		{
			add(new GLine(columnID*getWidth()/(double)NDECADES, 0, columnID*getWidth()/(double)NDECADES, getHeight()));
			add(new GLabel(START_DECADE + columnID * 10 + ""),3 + columnID*getWidth()/(double)NDECADES, getHeight()-3);
		}
		
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight()-GRAPH_MARGIN_SIZE, getWidth(), getHeight()-GRAPH_MARGIN_SIZE));
	}
	
	private void Reset()
	{
		removeAll();
		DrawGraph();
		colorID = 0;
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	private void Update() 
	{
		Reset();
		
		for (NameSurferEntry entry : entriesDisplayed)
			DisplayEntry(entry,colors[colorID++ % colors.length]);
	}
	
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { Update(); }
	public void componentShown(ComponentEvent e) { }
}
