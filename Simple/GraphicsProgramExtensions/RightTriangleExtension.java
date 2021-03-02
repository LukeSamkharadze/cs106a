package GraphicsProgramExtensions;

import acm.program.GraphicsProgram;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import acm.graphics.*;

public class RightTriangleExtension extends GraphicsProgram {
	private double circleRadius = 10;
	private int sideThickness = 4;

	private Vector<GOval> circles = new Vector<GOval>();
	private Vector<Vector<GLine>> lines = new Vector<Vector<GLine>>();

	private Vector<GLabel> cordinates = new Vector<GLabel>();
	private Vector<GLabel> distances = new Vector<GLabel>();

	private GOval slider;
	private double sliderWidth = 50;

	public void run() {
		SetupTriangle();
		UpdateTriangle();
		UpdateCircles(circles, lines);
		AttachListeners();
	}

	// ================================================================================================================//

	private void SetupTriangle() {
		GPoint points[] = new GPoint[] { new GPoint(100, 400), new GPoint(400, 400), new GPoint(100, 100) };

		CreateLines(lines, sideThickness);
		CreateCircles(points, circles, circleRadius);
		CreateCordinates(cordinates);
		CreateDistances(distances);

		slider = CreateSlider();
		circleRadius = slider.getX() + slider.getWidth() / 2. - (slider.getX() + circleRadius - 50);
	}

	private void UpdateTriangle() {
		UpdateLines(0, sideThickness, circles.elementAt(0), circles.elementAt(1));
		UpdateLines(1, sideThickness, circles.elementAt(0), circles.elementAt(2));
		UpdateLines(2, sideThickness, circles.elementAt(1), circles.elementAt(2));

		UpdateCordinates(cordinates, circles);
		UpdateDistances(distances, circles);
	}

	// ================================================================================================================//

	private void UpdateLines(int lineIndex, int sideThickness, GOval startCircle, GOval endCircle) {
		// startCircle.getX()+startCircle.getWidth()/2. - (index-sideThickness/2. *
		// sideThickness/2.),
		for (int sideIndex = 0; sideIndex < sideThickness; sideIndex++) {
			lines.elementAt(lineIndex).elementAt(sideIndex).setStartPoint(
					startCircle.getX() + startCircle.getWidth() / 2.
							- ((sideIndex - sideThickness / 2.) * sideThickness / 2.) / 2.,
					startCircle.getY() + startCircle.getHeight() / 2.
							- ((sideIndex - sideThickness / 2.) * sideThickness / 2.) / 2.);
			lines.elementAt(lineIndex).elementAt(sideIndex).setEndPoint(
					endCircle.getX() + endCircle.getWidth() / 2.
							- ((sideIndex - sideThickness / 2.) * sideThickness / 2.) / 2.,
					endCircle.getY() + endCircle.getHeight() / 2.
							- ((sideIndex - sideThickness / 2.) * sideThickness / 2.) / 2.);
		}
	}

	private void UpdateCircles(Vector<GOval> circles, Vector<Vector<GLine>> lines) {
		GPoint _0 = lines.elementAt(0).elementAt(lines.elementAt(0).size() / 2).getStartPoint();
		GPoint _1 = lines.elementAt(0).elementAt(lines.elementAt(0).size() / 2).getEndPoint();
		GPoint _2 = lines.elementAt(1).elementAt(lines.elementAt(1).size() / 2).getEndPoint();

		circles.elementAt(0).setBounds(_0.getX() - circleRadius, _0.getY() - circleRadius, circleRadius * 2,
				circleRadius * 2);
		circles.elementAt(1).setBounds(_1.getX() - circleRadius, _1.getY() - circleRadius, circleRadius * 2,
				circleRadius * 2);
		circles.elementAt(2).setBounds(_2.getX() - circleRadius, _2.getY() - circleRadius, circleRadius * 2,
				circleRadius * 2);
	}

	private void UpdateCordinates(Vector<GLabel> cordinates, Vector<GOval> circles) {
		for (int cordinateIndex = 0; cordinateIndex < cordinates.size(); cordinateIndex++) {
			cordinates.elementAt(cordinateIndex).setLocation(5, getHeight() - cordinateIndex * 15 - 5);
			cordinates.elementAt(cordinateIndex).setLabel("X: " + circles.elementAt(cordinateIndex).getX() + " Y: "
					+ circles.elementAt(cordinateIndex).getX());
		}
	}

	private void UpdateDistances(Vector<GLabel> distances, Vector<GOval> circles) {
		double circle0X = circles.elementAt(0).getX() + circles.elementAt(0).getWidth() / 2.;
		double circle0Y = circles.elementAt(0).getY() + circles.elementAt(0).getHeight() / 2.;

		double circle1X = circles.elementAt(1).getX() + circles.elementAt(1).getWidth() / 2.;
		double circle1Y = circles.elementAt(1).getY() + circles.elementAt(1).getHeight() / 2.;

		double circle2X = circles.elementAt(2).getX() + circles.elementAt(2).getWidth() / 2.;
		double circle2Y = circles.elementAt(2).getY() + circles.elementAt(2).getHeight() / 2.;

		distances.elementAt(0).setLabel(Distance(circle0X, circle0Y, circle1X, circle1Y) + "");

		distances.elementAt(0).setLocation((circle0X + circle1X) / 2. + 5, (circle0Y + circle1Y) / 2. - 5);

		distances.elementAt(1).setLabel(Distance(circle0X, circle0Y, circle2X, circle2Y) + "");

		distances.elementAt(1).setLocation((circle0X + circle2X) / 2. + 5, (circle0Y + circle2Y) / 2. - +5);

		distances.elementAt(2).setLabel(Math.round(Distance(circle1X, circle1Y, circle2X, circle2Y)) + "");

		distances.elementAt(2).setLocation((circle1X + circle2X) / 2. + 5, (circle1Y + circle2Y) / 2. - 5);
	}

	// ================================================================================================================//

	private GOval CreateSlider() {
		GOval slider = CreateCircle(new GPoint(getWidth() - 75, getHeight() - 25), circleRadius / 2.);
		GLine sliderLine = new GLine(getWidth() - 75 - sliderWidth, getHeight() - 25, getWidth() - 75 + sliderWidth,
				getHeight() - 25);

		slider.setFillColor(Color.red);
		slider.setFilled(true);

		add(sliderLine);
		add(slider);

		return slider;
	}

	private void CreateDistances(Vector<GLabel> distances) {
		for (int distanceIndex = 0; distanceIndex < 3; distanceIndex++) {
			GLabel distance = new GLabel("ddd", 55, 55);
			distance.setColor(Color.red);
			add(distance);

			distances.add(distance);
		}
	}

	private void CreateCordinates(Vector<GLabel> cordinates) {
		for (int cordinateIndex = 0; cordinateIndex < 3; cordinateIndex++) {
			GLabel cordinate = new GLabel("", 5, getHeight() - cordinateIndex * 15);
			add(cordinate);

			cordinates.add(cordinate);
		}

	}

	private void CreateLines(Vector<Vector<GLine>> lines, int sideThickness) {
		for (int lineIndex = 0; lineIndex < 3; lineIndex++) {
			lines.add(new Vector<GLine>());
			for (int sideIndex = 0; sideIndex < sideThickness; sideIndex++) {
				GLine line = new GLine(0, 0, 0, 0);
				add(line);
				lines.elementAt(lineIndex).add(line);
			}
		}
	}

	private void CreateCircles(GPoint[] points, Vector<GOval> circles, double circleRadius) {
		for (int index = 0; index < points.length; index++) {
			GOval circle = CreateCircle(points[index], circleRadius);

			circle.setFilled(true);
			add(circle);

			circles.add(circle);
		}
	}

	private GOval CreateCircle(GPoint center, double circleRadius) {
		return new GOval(center.getX() - circleRadius, center.getY() - circleRadius, 2 * circleRadius,
				2 * circleRadius);
	}

	// ================================================================================================================//

	private double Distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	// ================================================================================================================//

	private void AttachListeners() {
		for (int index = 0; index < 3; index++) {
			AttachCircleMouseListener(index);
			for (int sideIndex = 0; sideIndex < sideThickness; sideIndex++)
				AttachLineMouseListener(index, sideIndex);
		}

		AttachSliderListener();
	}

	private void AttachCircleMouseListener(int index) {
		MouseMotionListener mouseMotionListener = new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				UpdateTriangle();

				if (index == 0) {
					circles.elementAt(index).setBounds(e.getX() - circleRadius, e.getY() - circleRadius,
							circleRadius * 2, circleRadius * 2);
					circles.elementAt(1).setBounds(circles.elementAt(1).getX(),
							circles.elementAt(index).getY()
									- (e.getX() - circleRadius - circles.elementAt(index).getX()),
							circleRadius * 2, circleRadius * 2);
					circles.elementAt(2).setBounds(e.getX() - circleRadius, circles.elementAt(2).getY(),
							circleRadius * 2, circleRadius * 2);
				} else if (index == 1) {
					circles.elementAt(index).setBounds(e.getX() - circleRadius, e.getY() - circleRadius,
							circleRadius * 2, circleRadius * 2);
					circles.elementAt(0).setBounds(circles.elementAt(0).getX(), e.getY() - circleRadius,
							circleRadius * 2, circleRadius * 2);
				} else if (index == 2) {
					circles.elementAt(index).setBounds(e.getX() - circleRadius, e.getY() - circleRadius,
							circleRadius * 2, circleRadius * 2);
					circles.elementAt(0).setBounds(e.getX() - circleRadius, circles.elementAt(0).getY(),
							circleRadius * 2, circleRadius * 2);
				}

			}
		};
		circles.elementAt(index).addMouseMotionListener(mouseMotionListener);
	}

	private void AttachLineMouseListener(int index, int sideIndex) {
		MouseMotionListener mouseMotionListener = new MouseMotionListener() {
			MouseEvent previousE = null;

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				UpdateTriangle();

				if (previousE != null)
					if (Distance(previousE.getX(), previousE.getY(), e.getX(), e.getY()) <= 10)
						if (index == 0) {
							circles.elementAt(0).setBounds(circles.elementAt(0).getX() + e.getX() - previousE.getX(),
									e.getY() - circleRadius, circleRadius * 2, circleRadius * 2);
							circles.elementAt(1).setBounds(circles.elementAt(1).getX() + e.getX() - previousE.getX(),
									e.getY() - circleRadius, circleRadius * 2, circleRadius * 2);
							circles.elementAt(2).setBounds(circles.elementAt(2).getX() + e.getX() - previousE.getX(),
									circles.elementAt(2).getY(), circleRadius * 2, circleRadius * 2);
						} else if (index == 1) {
							circles.elementAt(0).setBounds(e.getX() - circleRadius,
									circles.elementAt(0).getY() + e.getY() - previousE.getY(), circleRadius * 2,
									circleRadius * 2);
							circles.elementAt(2).setBounds(e.getX() - circleRadius,
									circles.elementAt(2).getY() + e.getY() - previousE.getY(), circleRadius * 2,
									circleRadius * 2);
							circles.elementAt(1).setBounds(circles.elementAt(1).getX(),
									circles.elementAt(1).getY() + e.getY() - previousE.getY(), circleRadius * 2,
									circleRadius * 2);
						} else if (index == 2) {
							circles.elementAt(1).setBounds(circles.elementAt(1).getX() + e.getX() - previousE.getX(),
									circles.elementAt(1).getY(), circleRadius * 2, circleRadius * 2);
							circles.elementAt(2).setBounds(circles.elementAt(0).getX(),
									circles.elementAt(2).getY() + e.getY() - previousE.getY(), circleRadius * 2,
									circleRadius * 2);
						}
				previousE = e;
			}
		};
		lines.elementAt(index).elementAt(sideIndex).addMouseMotionListener(mouseMotionListener);
	}

	private void AttachSliderListener() {
		MouseMotionListener mouseMotionListener = new MouseMotionListener() {
			double startingX = slider.getX() + circleRadius;

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (Distance(startingX, slider.getY(), e.getX(), slider.getY()) <= 50)
					slider.setBounds(e.getX() - slider.getWidth() / 2., slider.getY(), slider.getWidth(),
							slider.getHeight());

				circleRadius = slider.getX() + slider.getWidth() / 2. - (startingX - 50);

				UpdateCircles(circles, lines);
			}
		};
		slider.addMouseMotionListener(mouseMotionListener);
	}

	// ================================================================================================================//
}