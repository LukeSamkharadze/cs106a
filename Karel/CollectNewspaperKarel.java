/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	
	public void run() {
		CollectNewspaper();
	}

	// METHODS //===========================================================================================================================//
	//======================================================================================================================================//

	// PRECONDITION - X=3;Y=4 - EAST /// POSTCONDITION - X=3;Y=4 - EAST
	protected void CollectNewspaper() {
		MoveToNewspaper();
		PickUpNewspaper();
		MoveToStartingPosition();
	}

	// PRECONDITION - X=3;Y=4 - EAST /// POSTCONDITION - X=6;Y=3 - EAST
	protected void MoveToNewspaper() {
		MoveTillWall();

		TurnSouth();

		MoveTillLeftIsOpen();

		TurnEast();
		Move();
	}

	// PRECONDITION - X=6;Y=3 /// POSTCONDITION - X=6;Y=3
	protected void PickUpNewspaper() {
		PickBeepers();
	}

	// PRECONDITION - X=6;Y=3 /// POSTCONDITION - X=4;Y=4
	protected void MoveToStartingPosition() {
		turnRight();

		for (int i = 0; i < 2; i++) {
			turnRight();
			MoveTillWall();
		}

		TurnEast();
	}

	// Safe moving till left block is clear
	protected void MoveTillLeftIsOpen() {
		while (frontIsClear() && leftIsBlocked())
			move();
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

	// Pick every beeper availabe
	protected void PickBeepers() {
		while (beepersPresent())
			pickBeeper();
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