package Main;
import acm.io.IODialog;
import acm.program.*;
import acm.util.RandomGenerator;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import Components.Button;
import Components.Components;
import Constants.MainConstants;
import Data.DataBase;
import Entry.Entry;
import Graphs.Graph;
import Strategies.DiagramGraphStrategy;
import Strategies.IGraphStrategy;
import Strategies.LineGraphStrategy;
import Strategies.PieChartGraphStrategy;

public class NameSurferPRO extends Program implements MainConstants
{
	private DataBase dataBase = new DataBase("names-data.txt");
	private Graph graph = new Graph(new PieChartGraphStrategy());
	
	private Components components = new Components();
	
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	
	public void init() 
	{
		add(graph,CENTER);
		add(components.label,SOUTH);
		add(components.textField,SOUTH);
		
		add(components.lineGraph,SOUTH);
		add(components.diagramGraph,SOUTH);
		add(components.pieChartGraph,SOUTH);
		
		add(components.remove,SOUTH);
		add(components.clear,SOUTH);
		
		addActionListeners();
	}

	public void ChartIsFull()
	{
		JOptionPane.showMessageDialog(this,"MAX NUMBER OF GRAPHS REACHED","MARI ERROR",JOptionPane.ERROR_MESSAGE);
	}
	
	public void ChangeGraph(IGraphStrategy graphStrategy)
	{
		graph.graphStrategy = graphStrategy;
		graph.Update(entries);
	}
	
	public boolean AddEntry(Entry entry)
	{
		if(CheckAvalibility(entry)==false)
			return false;
		
		entries.add(entry);
		return true;
	}
	
	public void RemoveEntry(Entry entry)
	{
		entries.remove(entry);
		graph.RemoveEntry(entry);
	}
	
	public void RemoveAllEntries()
	{
		entries.clear();
		graph.RemoveAllEntries();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		((Button) e.getSource()).ActionPerformed(this, dataBase.findEntry(components.textField.getText().toLowerCase()));
	}
	
	private boolean CheckAvalibility(Entry entry)
	{
		return entries.contains(entry)==false && entry !=null && entries.size() <= MAX_ENTRIES;
	}
}
