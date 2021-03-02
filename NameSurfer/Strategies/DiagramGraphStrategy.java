package Strategies;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;

import Constants.DiagramConstants;
import Constants.MainConstants;
import Entry.Entry;
import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;

public class DiagramGraphStrategy implements IGraphStrategy, DiagramConstants, MainConstants
{
	@Override
	public void DisplayGraph(GCanvas canvas) 
	{
		double eachDecadeWidth = (canvas.getWidth() - canvas.getWidth()*LEGEND_SCALE)/(double)NDECADES;
		
		for (int columnID = 0; columnID < NDECADES; columnID++)
		{
			canvas.add(new GLine(
					canvas.getWidth()*LEGEND_SCALE+columnID * eachDecadeWidth,
					0,
					canvas.getWidth()*LEGEND_SCALE+columnID * eachDecadeWidth,
					canvas.getHeight()));
			
			GLabel label = new GLabel(START_DECADE + columnID*10 + "");
			label.setLocation(canvas.getWidth()*3/800. + canvas.getWidth()*LEGEND_SCALE+columnID * eachDecadeWidth, canvas.getHeight()-3);
			label.setFont(new Font("Arial",Font.PLAIN,(int)(canvas.getHeight()*LABEL_SCALE)));
			
			canvas.add(label);
		}
		
		canvas.add(new GLine(canvas.getWidth()*LEGEND_SCALE, 0, canvas.getWidth(), 0));
		canvas.add(new GLine(
				canvas.getWidth()*LEGEND_SCALE,
				canvas.getHeight()-canvas.getHeight()*OFFSET_SCALE,
				canvas.getWidth(),
				canvas.getHeight()-canvas.getHeight()*OFFSET_SCALE));
		
	}

	@Override
	public void DisplayEntries(GCanvas canvas, Map<Entry,Color> entriesAndColors) 
	{
		double eachDecadeWidth = (canvas.getWidth() - canvas.getWidth()*LEGEND_SCALE)/(double)NDECADES;
		double eachDiagramWidth = eachDecadeWidth/10.;
		
		int counter = 0;
		for (Entry entry : entriesAndColors.keySet()) 
		{
			for (int columnID = 0; columnID < NDECADES-1; columnID++)
			{
				GRect rect = new GRect(
						canvas.getWidth()*LEGEND_SCALE + eachDecadeWidth*columnID + eachDiagramWidth*counter,
						(canvas.getHeight()-canvas.getHeight()*OFFSET_SCALE)*((((entry.getRank(columnID)==0)?
								MAX_RANK-1:entry.getRank(columnID))-1)/((double)MAX_RANK-1)),
						eachDiagramWidth,
						canvas.getHeight()-canvas.getHeight()*OFFSET_SCALE-
						((canvas.getHeight()-canvas.getHeight()*OFFSET_SCALE)*((((entry.getRank(columnID)==0)?
								MAX_RANK-1:entry.getRank(columnID))-1)/((double)MAX_RANK-1))));
				
				rect.setFillColor(entriesAndColors.get(entry));
				rect.setFilled(true);
				
				canvas.add(rect);
			}
			DisplayLegend(canvas, entry, entriesAndColors.get(entry), counter);
			counter++;
		}
	}
	
	private void DisplayLegend(GCanvas canvas, Entry entry,Color color,int legendCounter)
	{
		double XOffset = canvas.getWidth()*LEGEND_SCALE/2.;
		double YOffset = (legendCounter+1) * canvas.getHeight()*LEGEND_GAP_SCALE;

		GLabel label = new GLabel(entry.getName());
		label.setFont(new Font("Arial",Font.PLAIN,(int)(canvas.getHeight()*LABEL_SCALE)));
		label.setLocation(XOffset-label.getWidth()/2., YOffset);
		
		GRect rect = new GRect(
				XOffset-canvas.getHeight()*LABEL_SCALE,
				YOffset+canvas.getHeight()*LABEL_SCALE/2.,
				canvas.getHeight()*LABEL_SCALE*2,
				canvas.getHeight()*LABEL_SCALE/2.);
		rect.setFillColor(color);
		rect.setFilled(true);
		
		canvas.add(rect);
		canvas.add(label);
	}
}
