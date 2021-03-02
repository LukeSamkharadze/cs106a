/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	
	public void run() {
		FixPillars();
	}

	// METHODS //===========================================================================================================================//
	//======================================================================================================================================//

	// PRECONDITION - X=1;Y=1 - EAST /// POSTCONDITION - X=LAST;Y=1 - EAST
	private void FixPillars() {
		while (frontIsClear()) {
			FixPillar();
			MoveToNextPillar();
		}

		FixPillar();
	}

	// PRECONDITION - X=ANY;Y=ANY /// POSTCONDITION - X=Any;Y=1 - SOUTH
	private void FixPillar() {
		TurnNorthAndFillTillWall();
		TurnSouthAndMoveTillWall();
	}

	// PRECONDITION -- X=ANY;Y=1 /// POSTCONDITION - X=ANY+4;Y=1 - East
	private void MoveToNextPillar() {
		TurnEast();

		for (int i = 0; i < 4; i++)
			Move();
	}

	// Turn north and fill till wall
	private void TurnNorthAndFillTillWall() {
		TurnNorth();
		FillTillWall();
	}

	// Turn south and move till wall
	private void TurnSouthAndMoveTillWall() {
		TurnSouth();
		MoveTillWall();
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

	protected void FillTillWall() {
		FillSpot();

		while (frontIsClear()) {
			move();
			FillSpot();
		}
	}

	protected void FillSpot() {
		if (noBeepersPresent())
			PutBeeper();
	}

	//================================================//

	protected void PutBeeper() {
		if (beepersInBag())
			putBeeper();
	}

	//================================================//

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
}