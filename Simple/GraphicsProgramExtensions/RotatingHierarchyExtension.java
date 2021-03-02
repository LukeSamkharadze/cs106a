package GraphicsProgramExtensions;

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
import java.util.Iterator;
import java.util.Vector;

import CustomClasses.HierarchyBox;

public class RotatingHierarchyExtension extends GraphicsProgram {
	// Constant variables (perfectly changable)
	private static final double BOX_WIDTH = 75;
	private static final double BOX_HEIGHT = 25;

	private static final double CONNECTING_LINE_LENGTH = 125;
	private static final double GAP_BETWEEN_BOXES = 20;

	// Normal variables
	private static double canvasMiddleX;
	private static double canvasMiddleY;

	// Lines used to connect hierarchy boxes
	// public Vector<GLine> lines = new Vector<GLine>();

	public void run() {
		GetCanvasInfo();

		Vector<GLine> lines = new Vector<GLine>();
		HierarchyBox parentBox = CreateParentBox("Program", Color.black);

		CreateHierarchy(parentBox);

		parentBox.Add(this);

		AnimateRotation(parentBox, lines);
	}

	private void CreateHierarchy(HierarchyBox parentBox) {
		
		// CreateSonBoxes(parent,number of sons, names of sons, colors of sons)
		CreateSonBoxes(parentBox, 3, new String[] { "GraphicsProgram", "ConsoleProgram", "DialogProgram" }, Color.red);

		// Creating son for the third son of parentBox
		HierarchyBox secondParentBox = parentBox.sonBoxes.elementAt(2);
		CreateSonBoxes(secondParentBox, 1, new String[] { "1" }, Color.green);

		// Again creating 2 sons for the first son of secondParentBox
		HierarchyBox thirdParentBox = secondParentBox.sonBoxes.firstElement();
		CreateSonBoxes(thirdParentBox, 2, new String[] { "11", "22" }, Color.blue);
	}

	// =================================================================================================================================

	// Getting canvas info
	private void GetCanvasInfo() {
		canvasMiddleX = getWidth() / 2.;
		canvasMiddleY = getHeight() / 2.;
	}

	// Draws hierarchy parent box
	private HierarchyBox CreateParentBox(String text, Color color) {
		return CreateHierarchyBox(canvasMiddleX - BOX_WIDTH / 2., canvasMiddleY - BOX_HEIGHT / 2., BOX_WIDTH,
				BOX_HEIGHT, text, color);
	}

	// Draws hierarchy son box
	private void CreateSonBoxes(HierarchyBox parentBox, int sonNumber, String[] texts, Color color) {
		double lineStartingX = parentBox.getX() + BOX_WIDTH / 2.;
		double lineStartingY = parentBox.getY() + BOX_HEIGHT;

		for (int sonBoxID = 0; sonBoxID < sonNumber; sonBoxID++)
			parentBox.sonBoxes.add(CreateHierarchyBox(
					lineStartingX - ((BOX_WIDTH) * (sonNumber / 2. - sonBoxID))
							- ((GAP_BETWEEN_BOXES) * (sonNumber / 2 - sonBoxID)),
					lineStartingY + CONNECTING_LINE_LENGTH, BOX_WIDTH, BOX_HEIGHT, texts[sonBoxID], color));
	}

	// Draws hierarchy box with rectangle and text inside
	private HierarchyBox CreateHierarchyBox(double x, double y, double width, double height, String text, Color color) {
		return new HierarchyBox(x, y, width, height, text, color);
	}

	// Rotate sons around the parent box
	private void RotateSonsAround(HierarchyBox parentBox, double angle) {
		for (int sonID = 0; sonID < parentBox.sonBoxes.size(); sonID++) {
			double currentAngle = angle + (sonID + 1) * 360. / parentBox.sonBoxes.size();
			parentBox.sonBoxes.elementAt(sonID).SetBounds(
					parentBox.getX() + BOX_WIDTH / 2. + cos(currentAngle) * CONNECTING_LINE_LENGTH - BOX_WIDTH / 2.,
					parentBox.getY() + BOX_HEIGHT / 2. + sin(currentAngle) * CONNECTING_LINE_LENGTH, BOX_WIDTH,
					BOX_HEIGHT);

			RotateSonsAround(parentBox.sonBoxes.elementAt(sonID), angle*2);
		}
	}

	private void AnimateRotation(HierarchyBox parentBox, Vector<GLine> lines) {
		for (double angle = 0;; angle += 0.25, pause(10)) {
			RemoveLines(lines);
			DrawLines(parentBox, lines);
			RotateSonsAround(parentBox, angle);
		}
	}

	// Draws lines from parent class to son classes
	private void DrawLines(HierarchyBox parentBox, Vector<GLine> lines) {
		for (int lineID = 0; lineID < parentBox.sonBoxes.size(); lineID++) {
			GLine line = new GLine(parentBox.getX() + BOX_WIDTH / 2., parentBox.getY() + BOX_HEIGHT,
					parentBox.sonBoxes.elementAt(lineID).getX() + BOX_WIDTH / 2.,
					parentBox.sonBoxes.elementAt(lineID).getY());

			add(line);
			lines.add(line);

			DrawLines(parentBox.sonBoxes.elementAt(lineID), lines);
		}
	}

	// Removes lines from canvas
	private void RemoveLines(Vector<GLine> lines) {
		for (int lineID = 0; lineID < lines.size(); lineID++)
			remove(lines.elementAt(lineID));
	}

	// =================================================================================================================================

	private double sin(double angle) {
		return Math.sin(Math.toRadians(angle));
	}

	private double cos(double angle) {
		return Math.cos(Math.toRadians(angle));
	}
}