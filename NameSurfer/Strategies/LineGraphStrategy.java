package Strategies;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;

import Constants.LineGraphConstants;
import Constants.MainConstants;
import Entry.Entry;
import Main.NameSurferPRO;
import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

public class LineGraphStrategy implements IGraphStrategy, LineGraphConstants, MainConstants
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
		int counter = 0;
		for (Entry entry : entriesAndColors.keySet())
		{
			DisplayEntry(canvas,entry, entriesAndColors.get(entry));
			DisplayLegend(canvas, entry, entriesAndColors.get(entry), counter);
			counter++;
		}
	}
	
	public void DisplayEntry(GCanvas canvas, Entry entry,Color color)
	{
		double eachDecadeWidth = (canvas.getWidth() - canvas.getWidth()*LEGEND_SCALE)/(double)NDECADES;
		
		for (int columnID = 0; columnID < NDECADES-1; columnID++)
		{
			GLine line = new GLine(
					canvas.getWidth()*LEGEND_SCALE + columnID * eachDecadeWidth,
					(canvas.getHeight()-canvas.getHeight()*OFFSET_SCALE)*((((entry.getRank(columnID)==0)?
							MAX_RANK:
								entry.getRank(columnID))-1)/((double)MAX_RANK-1)),
					canvas.getWidth()*LEGEND_SCALE + (columnID+1) * eachDecadeWidth,
					(canvas.getHeight()-canvas.getHeight()*OFFSET_SCALE)*((((entry.getRank(columnID+1)==0)?
							MAX_RANK:
								entry.getRank(columnID+1))-1)/((double)MAX_RANK-1)));
			
			line.setColor(color);
			
			canvas.add(line);
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