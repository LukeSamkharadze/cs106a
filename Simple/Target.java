/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.util.Vector;

public class Target extends GraphicsProgram {

	// Constant Variables
	private static final double SMALL_CIRCLE_RADIUS = 100;

	private static final double[] CIRCLE_RADIUSES = new double[] { 2.54, 1.65, 0.76 };

	private static final Color[] CIRCLE_COLORS = new Color[] { Color.red, Color.white, Color.red };

	// Normal Variables
	private static double centerY;
	private static double centerX;

	public void run() {
		GetCanvasInfo();

		// Drawing circles
		for (int circleID = 0; circleID < CIRCLE_RADIUSES.length; circleID++)
			DrawCircle(centerX, centerY, ConvertCMToPixels(CIRCLE_RADIUSES[circleID]), CIRCLE_COLORS[circleID]);
	}

	// I am genius // I am converting centimeters to pixels (ratio in instruction
	// file)
	public double ConvertCMToPixels(double centimeters) {
		return centimeters * 72 / 2.54;
	}

	// Getting canvas info that I need
	public void GetCanvasInfo() {
		centerY = getHeight() / 2.;
		centerX = getWidth() / 2.;
	}

	// Drawing circle according to its center, radius and color
	public void DrawCircle(double x, double y, double radius, Color color) {
		GOval circle = new GOval(x - radius / 2., y - radius / 2., radius, radius);
		ColorCircle(circle, color);
		add(circle);
	}

	// Filling oval with specific color and makes it visible
	public void ColorCircle(GOval oval, Color color) {
		oval.setColor(color);
		oval.setFillColor(color);
		oval.setFilled(true);
	}
}
