package CustomClasses;

import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class HierarchyBox extends GRect {
	public HierarchyBox parentBox;
	public Vector<HierarchyBox> sonBoxes = new Vector<HierarchyBox>();

	public GLabel label;

	public HierarchyBox(double x, double y, double width, double height, String text, Color color) {
		super(x, y, width, height);
		this.setColor(color);
		CreateLabel(text);
	}

	// Draws text with middle alignment relative to box
	private void CreateLabel(String text) {
		label = new GLabel(text);
		AlignLabel();
	}

	// Aligning Label to grect
	private void AlignLabel() {
		label.setLocation(this.getX() + (this.getWidth() / 2.) - label.getWidth() / 2.,
				this.getY() + (this.getHeight() / 2.) + label.getHeight() / 4);
	}

	// Make visible to certain graphics program
	public void Add(GraphicsProgram graphicsProgram) {
		graphicsProgram.add(this);
		graphicsProgram.add(label);

		for (Iterator iterator = sonBoxes.iterator(); iterator.hasNext();) {
			HierarchyBox sonBox = (HierarchyBox) iterator.next();

			sonBox.Add(graphicsProgram);
		}
	}

	// Moving rectangle with label as well
	public void Move(double dx, double dy) {
		this.move(dx, dy);
		label.move(dx, dy);
	}

	// Setting new bounds for rect and aligning label to it
	public void SetBounds(double x, double y, double width, double height) {
		this.setBounds(x, y, width, height);
		AlignLabel();
	}
}
