/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.util.Vector;

public class ProgramHierarchy extends GraphicsProgram {
	// Constant variables (perfectly changable)
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 40;

	private static final double SMALL_CONNECTING_LINE_LENGTH = 75;
	private static final double GAP_BETWEEN_BOXES = 20;

	// Normal variables
	private static double canvasMiddleX;
	private static double canvasMiddleY;

	public void run() {
		GetCanvasInfo();

		DrawProgram();
		DrawGraphics();
		DrawConsole();
		DrawDialog();

		DrawLines();
	}

	// Getting canvas info
	private void GetCanvasInfo() {
		canvasMiddleX = getWidth() / 2.;
		canvasMiddleY = getHeight() / 2.;
	}

	// Draws program box
	private void DrawProgram() {
		DrawHierarchyBox(canvasMiddleX - BOX_WIDTH / 2.,
				canvasMiddleY - (2 * BOX_HEIGHT + SMALL_CONNECTING_LINE_LENGTH) / 2., BOX_WIDTH, BOX_HEIGHT, "Program");
	}

	// Draws graphics hierarchy box
	private void DrawGraphics() {
		DrawHierarchyBox(canvasMiddleX - BOX_WIDTH / 2. - BOX_WIDTH - GAP_BETWEEN_BOXES,
				canvasMiddleY - (2 * BOX_HEIGHT + SMALL_CONNECTING_LINE_LENGTH) / 2. + SMALL_CONNECTING_LINE_LENGTH,
				BOX_WIDTH, BOX_HEIGHT, "GraphicsProgram");
	}

	// Draws console hierarchy box
	private void DrawConsole() {
		DrawHierarchyBox(canvasMiddleX - BOX_WIDTH / 2.,
				canvasMiddleY - (2 * BOX_HEIGHT + SMALL_CONNECTING_LINE_LENGTH) / 2. + SMALL_CONNECTING_LINE_LENGTH,
				BOX_WIDTH, BOX_HEIGHT, "ConsoleProgram");
	}

	// Draws dialog hierarchy box
	private void DrawDialog() {
		DrawHierarchyBox(canvasMiddleX - BOX_WIDTH / 2. + BOX_WIDTH + GAP_BETWEEN_BOXES,
				canvasMiddleY - (2 * BOX_HEIGHT + SMALL_CONNECTING_LINE_LENGTH) / 2. + SMALL_CONNECTING_LINE_LENGTH,
				BOX_WIDTH, BOX_HEIGHT, "DialogProgram");
	}

	// Draws hierarchy box with rectangle and text inside
	private void DrawHierarchyBox(double x, double y, double width, double height, String text) {
		add(DrawRectangle(x, y, width, height));
		DrawText(x, y, width, height, text);
	}

	// Draws rectangle and makes it visible
	private GRect DrawRectangle(double x, double y, double width, double height) {
		GRect rect = new GRect(x, y, width, height);
		add(rect);

		return rect;
	}

	// Draws text with middle alignment relative to box
	private void DrawText(double x, double y, double width, double height, String text) {
		GLabel label = new GLabel(text, x, y);

		double middleAlignmentX = x + (width / 2.) - label.getWidth() / 2.;
		double middleAlignmentY = y + (height / 2.) + label.getAscent()/2.;

		label.setLocation(middleAlignmentX, middleAlignmentY);
		add(label);
	}

	// Draws lines from parent class to son classes
	private void DrawLines() {
		for (int lineID = 0; lineID < 3; lineID++)
			add(new GLine(canvasMiddleX,
					canvasMiddleY - (2 * BOX_HEIGHT + SMALL_CONNECTING_LINE_LENGTH) / 2. + BOX_HEIGHT,
					canvasMiddleX - (1 - lineID) * (GAP_BETWEEN_BOXES + BOX_WIDTH), canvasMiddleY
							- (2 * BOX_HEIGHT + SMALL_CONNECTING_LINE_LENGTH) / 2. + SMALL_CONNECTING_LINE_LENGTH));
	}
}