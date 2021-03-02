/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	
	public void run() {
		DrawCheckerboardPattern();
	}

	// METHODS //===========================================================================================================================//
	//======================================================================================================================================//

	// PRECONDITION - X=1;Y=1 - EAST /// POSTCONDITION - X=LAST;Y=LAST - EAST
	protected void DrawCheckerboardPattern() {
		FillCheckerFromCurrent();

		while (leftIsClear()) {
			GetInPosition();
			FillCheckerFromNext();
			GetInPosition();
			FillCheckerFromCurrent();
		}
	}

	// Fill till wall by checker pattern, start placing beepers from the current position
	protected void FillCheckerFromCurrent() {
		FillSpot();
		Move();
		FillCheckerFromNext();
	}

	// Fill till wall by checker pattern, start placing beepers from the next position
	protected void FillCheckerFromNext() {
		while (frontIsClear()) {
			move();
			FillSpot();
			Move();
		}
	}

	// Return to first column and 1 unit higher row
	protected void GetInPosition() {
		if (leftIsClear()) {
			GoToFirstRow();
			UTurnRight();
		}
	}

	// Turn around and move till wall
	protected void GoToFirstRow() {
		turnAround();
		MoveTillWall();
	}

	// Make U turn like movement from right
	protected void UTurnRight() {
		turnRight();
		Move();
		turnRight();
	}

	// GENERAL METHODS //===================================================================================================================//
	//======================================================================================================================================//

	protected void Move() {
		if (frontIsClear())
			move();
	}

	protected void MoveTillWall() {
		while (frontIsClear())
			move();
	}

	//================================================//

	protected void FillSpot() {
		if (noBeepersPresent())
			PutBeeper();
	}

	//================================================//

	protected void PutBeeper() {
		if (beepersInBag())
			putBeeper();
	}

}