package ClockKarel;

import stanford.karel.SuperKarel;

public class ClockKarel extends SuperKarel {
	public int xWorldLength;
	public int yWorldLength;

	public void run() {
		double clockFrameRadius = 24;

		// CLOCK HAND ID
		// { HAND RADIUS // HAND SPEED // STARTING ANGLE }
		double[][] radiusSpeedAngle = new double[][] { { 20, -5, 0 }, { 10, -1, 0 }, { 5, -20, 0 } };

		GetWorldInfo();

		DrawFrame(clockFrameRadius);
		AnimateHands(radiusSpeedAngle);
	}

	public void AnimateHands(double[][] radiusSpeedAngle) {
		for (int clockHandID = 0; clockHandID < radiusSpeedAngle.length; clockHandID++)
			_PutOrPickBeepers(radiusSpeedAngle[clockHandID][2], true, radiusSpeedAngle[clockHandID][0]);
		for (;; pause(100)) {
			for (int clockHandID = 0; clockHandID < radiusSpeedAngle.length; clockHandID++) {
				_PutOrPickBeepers(radiusSpeedAngle[clockHandID][2], false, radiusSpeedAngle[clockHandID][0]);
				radiusSpeedAngle[clockHandID][2] += radiusSpeedAngle[clockHandID][1];
				_PutOrPickBeepers(radiusSpeedAngle[clockHandID][2], true, radiusSpeedAngle[clockHandID][0]);
			}
		}
	}

	public void DrawFrame(double clockFrameRadius) {
		for (int x = (int) -xWorldLength / 2; x <= (int) xWorldLength / 2; x++)
			for (int y = (int) -yWorldLength / 2; y <= (int) yWorldLength / 2; y++)
				if (y * y >= clockFrameRadius * clockFrameRadius - x * x)
					_PutOrPickBeeper(x, y, true);
	}

	// =============================================================================//

	public void GotoCordinates(double x, double y) {
		TurnSouth();
		MoveTillWall();
		TurnWest();
		MoveTillWall();

		TurnEast();
		for (int steps = (int) -xWorldLength / 2 + 1; steps < (int) x; steps++)
			move();

		TurnNorth();
		for (int steps = (int) -yWorldLength / 2 + 1; steps < (int) y; steps++)
			move();
	}

	public void _PutBeeper(double x, double y) {
		GotoCordinates(x, y);
		putBeeper();
	}

	public void _PickBeeper(double x, double y) {
		GotoCordinates(x, y);
		pickBeeper();
	}

	public void _PutOrPickBeeper(double x, double y, boolean putOrPick) {
		if (putOrPick)
			_PutBeeper(x, y);
		else
			_PickBeeper(x, y);
	}

	public void _PutOrPickBeepers(double angle, boolean putOrPick, double radius) {
		for (double x = -xWorldLength / 2 - 1; x < xWorldLength / 2; x++)
			for (double y = -yWorldLength / 2 - 1; y < yWorldLength / 2; y++) {
				if (y == Math.round(Tan(angle) * x) && x >= 0 && x <= Cos(angle) * radius)
					_PutOrPickBeeper(x, y, putOrPick);
				else if (y == Math.round(Tan(angle) * x) && x <= 0 && x >= Cos(angle) * radius)
					_PutOrPickBeeper(x, y, putOrPick);
				else if (x == Math.round(1 / Tan(angle) * y) && y >= 0 && y <= Sin(angle) * radius)
					_PutOrPickBeeper(x, y, putOrPick);
				else if (x == Math.round(1 / Tan(angle) * y) && y <= 0 && y >= Sin(angle) * radius)
					_PutOrPickBeeper(x, y, putOrPick);

			}
	}

	public void GetWorldInfo() {
		xWorldLength = GetWorldX();
		yWorldLength = GetWorldY();
	}

	public int GetWorldX() {
		TurnEast();
		return GetStepsTillWall() + 1;
	}

	public int GetWorldY() {
		TurnNorth();
		return GetStepsTillWall() + 1;
	}

	public int GetStepsTillWall() {
		for (int steps = 0;; steps++)
			if (frontIsClear() == true)
				move();
			else
				return steps;
	}

	// =============================================================================//

	protected void MoveTillWall() {
		while (frontIsClear())
			move();
	}

	protected void TurnNorth() {
		while (facingNorth() == false)
			turnLeft();
	}

	protected void TurnSouth() {
		while (facingSouth() == false)
			turnLeft();
	}

	protected void TurnEast() {
		while (facingEast() == false)
			turnLeft();
	}

	protected void TurnWest() {
		while (facingWest() == false)
			turnLeft();
	}

	// =============================================================================//

	public double Tan(double angle) {
		return Math.tan(Math.toRadians(angle));
	}

	public double Cos(double angle) {
		return Math.cos(Math.toRadians(angle));
	}

	public double Sin(double angle) {
		return Math.sin(Math.toRadians(angle));
	}

	public double Cot(double angle) {
		return 1 / Math.tan(Math.toRadians(angle));
	}
}
