/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {
	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICKS_IN_BASE = 14;

	public void run() {
		DrawPyramid();
	}

	// Draw pyramid using rectangles
	public void DrawPyramid() {
		for (int columnNumber = BRICKS_IN_BASE; columnNumber > 0; columnNumber--)
			DrawRow(columnNumber);
	}

	// Draw every rectangle on selected row
	public void DrawRow(int columnNumber) {
		for (int brickOnRow = 0; brickOnRow < columnNumber; brickOnRow++)
			DrawRect(columnNumber, brickOnRow);
	}

	// Draw each rectangle according to its place on column and row
	public void DrawRect(int columnNumber, int brickOnRow) {
		add(new GRect(getWidth() / 2. - (BRICK_WIDTH * columnNumber / 2.) + brickOnRow * BRICK_WIDTH,
				getHeight() - BRICK_HEIGHT * (BRICKS_IN_BASE - columnNumber + 1), BRICK_WIDTH, BRICK_HEIGHT));
	}
}