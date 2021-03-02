package GraphicsProgramExtensions;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class PyramidExtension extends GraphicsProgram {

	private static final int BRICK_WIDTH = 15 + 2;
	private static final int BRICK_HEIGHT = 10 + 2;
	private static final int BRICKS_IN_BASE = 35;

	public void run() {
		DrawPyramid();
	}

	public void DrawPyramid() {
		for (int columnNumber = BRICKS_IN_BASE; columnNumber > 0; columnNumber--)
			DrawRow(columnNumber);
	}

	public void DrawRow(int columnNumber) {
		int counter1 = 0;
		for (int rowNumber = 0; rowNumber < columnNumber; rowNumber++)
			Land(columnNumber, rowNumber, counter1++);

	}

	public void Land(int columnNumber, int rowNumber, int rectID) {
		new Thread(() -> {
			StartLanding(columnNumber, rowNumber, CreateNewRect(rectID, columnNumber));
		}).start();
	}

	public GRect CreateNewRect(int rectID, int columnNumber) {
		double x;
		double y;
		do {
			x = Math.random() * (getWidth() + 2 * BRICK_WIDTH) - BRICK_WIDTH;
			y = Math.random() * (getHeight() + 2 * BRICK_HEIGHT) - BRICK_HEIGHT;
		} while (y > 0 && y < getHeight() && x > 0 && x < getWidth());

		GRect rect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);

		double doubleColor = rectID * 255 / columnNumber;

		rect.setFillColor(new Color((int) doubleColor, (int) doubleColor, (int) doubleColor));
		rect.setFilled(true);
		add(rect);

		return rect;
	}

	public void StartLanding(int columnNumber, int rowNumber, GRect rect) {
		double landingXPoint = getWidth() / 2. - (BRICK_WIDTH * columnNumber / 2.) + rowNumber * BRICK_WIDTH;
		double landingYPoint = getHeight() - BRICK_HEIGHT * (BRICKS_IN_BASE - columnNumber + 1);

		while (IsLanded(landingXPoint, landingYPoint, rect) == false) {
			if (Math.random() <= 0.95)
				rect.move(Math.signum(landingXPoint - rect.getX()), Math.signum(landingYPoint - rect.getY()));
			// else
			rect.move(((Math.random() - 0.5) * 20), ((Math.random() - 0.5)) * 20);

			pause(10);
		}

		FinishLanding(landingXPoint, landingYPoint, rect);
	}

	public void FinishLanding(double landingXPoint, double landingYPoint, GRect rect) {
		rect.setBounds(landingXPoint + 1, landingYPoint + 1, BRICK_WIDTH - 1, BRICK_HEIGHT - 1);
	}

	public boolean IsLanded(double landingXPoint, double landingYPoint, GRect rect) {
		double possibleGap = 5;

		if (rect.getX() >= landingXPoint - possibleGap && rect.getX() <= landingXPoint + possibleGap)
			if (rect.getY() >= landingYPoint - possibleGap && rect.getY() <= landingYPoint + possibleGap)
				return true;

		return false;
	}
}