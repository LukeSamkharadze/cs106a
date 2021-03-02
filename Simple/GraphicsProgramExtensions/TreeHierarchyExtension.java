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
import java.util.Vector;

import CustomClasses.HierarchyBox;

public class TreeHierarchyExtension extends GraphicsProgram {
	// Constant variables (perfectly changable)
	private static final double BOX_WIDTH = 100;
	private static final double BOX_HEIGHT = 30;

	private static final double SMALL_CONNECTING_LINE_LENGTH = 30;
	private static final double GAP_BETWEEN_BOXES = 10;

	// Normal variables
	private static double canvasMiddleX;
	private static double canvasMiddleY;

	public void run() {
		GetCanvasInfo();

		HierarchyBox parentBox = CreateParentBox("Program", Color.black);

		CreateHierarchy(parentBox);

		parentBox.Add(this);

		DrawLines(parentBox);
	}

	public void CreateHierarchy(HierarchyBox parentBox) {
		// (parent, number of sons, names of sons, color of sons)
		CreateSonBoxes(parentBox, 3, new String[] { "GraphicsProgram", "ConsoleProgram", "DialogProgram" },Color.black);

		HierarchyBox secondParentBox = parentBox.sonBoxes.lastElement();
		CreateSonBoxes(secondParentBox, 4, new String[] { "Ae", "Mushaobs", "!", "!" }, Color.black);

		HierarchyBox thirdParentBox = secondParentBox.sonBoxes.firstElement();
		CreateSonBoxes(thirdParentBox,1, new String[] { "Kide", ":D", "!!!!" }, Color.black);

		HierarchyBox fourthParentBox = thirdParentBox.sonBoxes.firstElement();
		CreateSonBoxes(fourthParentBox,5, new String[] { "Pruw", "2", "3", "4", "5" }, Color.black);

		HierarchyBox fifthParentBox = fourthParentBox.sonBoxes.elementAt(3);
		CreateSonBoxes(fifthParentBox,2, new String[] { "123", "123" }, Color.black);
	}

	// Getting canvas info
	private void GetCanvasInfo() {
		canvasMiddleX = getWidth() / 2.;
		canvasMiddleY = getHeight() / 2.;
	}

	// Draws hierarchy parent box
	private HierarchyBox CreateParentBox(String text, Color color) {
		return CreateHierarchyBox(canvasMiddleX - BOX_WIDTH / 2.,
				canvasMiddleY - BOX_HEIGHT - 6 * SMALL_CONNECTING_LINE_LENGTH, BOX_WIDTH, BOX_HEIGHT, text, color);
	}

	// Draws hierarchy son box
	private void CreateSonBoxes(HierarchyBox parentBox, int sonNumber, String[] texts, Color color) {
		double lineStartingX = parentBox.getX() + BOX_WIDTH / 2.;
		double lineStartingY = parentBox.getY() + BOX_HEIGHT;

		for (int sonBoxID = 0; sonBoxID < sonNumber; sonBoxID++)
			parentBox.sonBoxes.add(CreateHierarchyBox(
					lineStartingX - ((BOX_WIDTH) * (sonNumber / 2. - sonBoxID))
							- ((GAP_BETWEEN_BOXES) * ((sonNumber - 1) / 2. - sonBoxID)),
					lineStartingY + SMALL_CONNECTING_LINE_LENGTH, BOX_WIDTH, BOX_HEIGHT, texts[sonBoxID], color));
	}

	// Draws hierarchy box with rectangle and text inside
	private HierarchyBox CreateHierarchyBox(double x, double y, double width, double height, String text, Color color) {
		return new HierarchyBox(x, y, width, height, text, color);
	}

	// Draws lines from parent class to son classes
	private void DrawLines(HierarchyBox parentBox) {
		for (int lineID = 0; lineID < parentBox.sonBoxes.size(); lineID++) {
			add(new GLine(parentBox.getX() + BOX_WIDTH / 2., parentBox.getY() + BOX_HEIGHT,
					parentBox.sonBoxes.elementAt(lineID).getX() + BOX_WIDTH / 2.,
					parentBox.sonBoxes.elementAt(lineID).getY()));

			DrawLines(parentBox.sonBoxes.elementAt(lineID));
		}
	}

}