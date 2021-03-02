/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	
	public void run() {
		PlaceBeeperInMiddle();
	}

	// METHODS //===========================================================================================================================//
	//======================================================================================================================================//

	// Place beeper in the middle of the axis karel is facing
	protected void PlaceBeeperInMiddle() {
		// Filling axis karel is facing with beepers except first and last to track its movement
		FillExceptFirstAndLast();
		// Will start action from beeper
		TurnAroundAndMove();

		CollectBeepersFromBothEnds();

		// No beepers present, so we have to move back
		TurnAroundAndMove();
		// Putting last middle beeper
		PutBeeper();
	}

	// Move and fill till wall except first and last
	protected void FillExceptFirstAndLast() {
		Move();
		while (frontIsClear()) {
			FillSpot();
			move();
		}
	}
	
	// Do zig-zag motion and pick last beeper until beepers are present
	protected void CollectBeepersFromBothEnds()
	{
		while (beepersPresent()) {
			MoveTillNoBeepersPresent();

			TurnAroundAndMove();

			PickBeepers();
			Move();
		}
	}
	
	// Safe moving until beepers are not present
	protected void MoveTillNoBeepersPresent() {
		while (beepersPresent())
			Move();
	}

	// Safe move back by 1 block
	protected void TurnAroundAndMove() {
		turnAround();
		Move();
	}

	// GENERAL METHODS //===================================================================================================================//
	//======================================================================================================================================//

	// Safe move
protected void Move() {
		if (frontIsClear())
			move();
	}

	// Move till wall
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

	protected void PickBeepers() {
		while (beepersPresent())
			pickBeeper();
	}

	protected void PutBeeper() {
		if (beepersInBag())
			putBeeper();
	}
}