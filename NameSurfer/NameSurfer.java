/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants 
{
	private NameSurferDataBase dataBase = new NameSurferDataBase("names-data.txt");
	private NameSurferGraph graph = new NameSurferGraph();
	
	private JLabel labelL = new JLabel("Name");
	private JTextField textFieldT = new JTextField(15);
	private JButton graphB = new JButton("Graph");
	private JButton clearB = new JButton("Clear");
	
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the bottom of the window.
	 */
	public void init() 
	{
		add(graph,CENTER);
		add(labelL,SOUTH);
		add(textFieldT,SOUTH);
		add(graphB,SOUTH);
		add(clearB,SOUTH);
		
		addActionListeners();
		textFieldT.addActionListener(this);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==graphB || e.getSource() == textFieldT)
		{
			if(dataBase.findEntry(textFieldT.getText())!=null)
					graph.AddEntry(dataBase.findEntry(textFieldT.getText()));
		}
		else if (e.getSource()==clearB)
			graph.Clear();
	}
}
