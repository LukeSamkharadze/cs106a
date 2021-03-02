package Strategies;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import Constants.MainConstants;
import Constants.PieChartConstants;
import Entry.Entry;
import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class PieChartGraphStrategy implements IGraphStrategy, PieChartConstants, MainConstants
{
	@Override
	public void DisplayGraph(GCanvas canvas)
	{
		GOval oval = new GOval(
				canvas.getWidth()/2. - (canvas.getHeight()-canvas.getHeight()*LEGEND_SCALE)*CIRCLE_SCALE,
				(canvas.getHeight()-canvas.getHeight()*LEGEND_SCALE)/2.- (canvas.getHeight()-canvas.getHeight()*LEGEND_SCALE)*CIRCLE_SCALE,
				(canvas.getHeight()-canvas.getHeight()*LEGEND_SCALE)*CIRCLE_SCALE*2,
				(canvas.getHeight()-canvas.getHeight()*LEGEND_SCALE)*CIRCLE_SCALE*2);
		
		canvas.add(oval);
	}
	
	@Override
	public void DisplayEntries(GCanvas canvas, Map<Entry,Color> entriesAndColors)
	{
		Map<Entry,Double> averageRatings = new HashMap<Entry,Double>();
		Map<Entry,Double> degreesOfEntries = new HashMap<Entry,Double>();
		
		for (Entry entry : entriesAndColors.keySet())
			averageRatings.put(entry,AverageRating(entry));
		
		double sumOfAverages = 0;
		
		for (Entry entry : entriesAndColors.keySet())
			sumOfAverages += averageRatings.get(entry);
		
		for (Entry entry : entriesAndColors.keySet())
			degreesOfEntries.put(entry,averageRatings.get(entry)/sumOfAverages*360);
		
		double angle = 0;
		for (Entry entry : entriesAndColors.keySet())
		{
			for (double angleID = angle; angleID < angle + degreesOfEntries.get(entry); angleID+=0.001) 
			{
				GLine line = new GLine(
						canvas.getWidth()/2.,
						(canvas.getHeight() - canvas.getHeight()*LEGEND_SCALE)/2.,
						canvas.getWidth()/2.+ Math.cos(Math.toRadians(angleID))*(canvas.getHeight() - canvas.getHeight()*LEGEND_SCALE)*CIRCLE_SCALE,
						(canvas.getHeight()-canvas.getHeight()*LEGEND_SCALE)/2.+Math.sin(Math.toRadians(angleID))*(canvas.getHeight() - canvas.getHeight()*LEGEND_SCALE)*CIRCLE_SCALE);
				line.setColor(entriesAndColors.get(entry));
				line.sendToFront();
				
				canvas.add(line);
			}
			
			angle = angle + degreesOfEntries.get(entry);
		}
		
		DisplayLegends(canvas, entriesAndColors);
	}
	
	private void DisplayLegends(GCanvas canvas, Map<Entry,Color> entriesAndColors)
	{
		Map<Entry,GLabel> labels = new HashMap<>();
		
		double totalWidth = 0;
		
		for(Entry entry : entriesAndColors.keySet())
		{
			GLabel label = new GLabel(entry.getName());
			label.setFont(new Font("Arial",Font.PLAIN,(int)(canvas.getHeight()*LABEL_SCALE)));
			labels.put(entry,label);
			
			totalWidth += 
					label.getWidth() +
					canvas.getWidth()*LEGEND_GAP_SCALE/2. +
					canvas.getWidth()*LEGEND_GAP_SCALE*2 +
					canvas.getWidth()*LEGEND_GAP_SCALE;
		}
		
		double XOffset = (canvas.getWidth() - totalWidth)/2.;
		double YOffset = canvas.getHeight() - canvas.getHeight()*LEGEND_SCALE;

		for(Entry entry : entriesAndColors.keySet())
		{
			labels.get(entry).setLocation(XOffset, YOffset+labels.get(entry).getHeight()/4.);
			XOffset += labels.get(entry).getWidth() + canvas.getWidth()*LEGEND_GAP_SCALE/2.;
			
			GRect rect = new GRect(
					XOffset,
					YOffset-canvas.getWidth()*LABEL_SCALE/6.,
					canvas.getWidth()*LABEL_SCALE,
					canvas.getWidth()*LABEL_SCALE/3.);
			rect.setFillColor(entriesAndColors.get(entry));
			rect.setFilled(true);
			
			XOffset += canvas.getWidth()*LEGEND_GAP_SCALE*3;
			
			canvas.add(rect);
			canvas.add(labels.get(entry));
		}
	}
	
	private double AverageRating(Entry entry)
	{
		int averageRating = 0;
		for (int decadeID = 0; decadeID < NDECADES-1; decadeID++)
			averageRating += 1000-((entry.getRank(decadeID) == 0)? 1000 : entry.getRank(decadeID));
		return averageRating/=(double)NDECADES;
	}
}
