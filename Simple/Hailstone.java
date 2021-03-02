/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int counter = 0;
		for (int number = readInt("Enter a number: "); number > 1; counter++) {
			PrintOperationInfo(number);
			number = DoOperation(number);
		}

		PrintStepsTook(counter);
	}

	// Does operation on each iteration
	private int DoOperation(int integer) {
		if (IsEven(integer))
			return integer / 2;
		else
			return integer * 3 + 1;
	}

	// Prints info what we have to do on each step
	private void PrintOperationInfo(int integer) {
		if (IsEven(integer))
			println(integer + " is even so I take half: " + integer / 2);
		else
			println(integer + " is odd, so I make 3n + 1: " + (integer * 3 + 1));
	}

	private void PrintStepsTook(int integer) {
		if (integer > 0)
			println("The process took " + integer + " to reach 1");
		else
			println("Input must be more than 1");
	}

	// Checks if integer is even and returns bool
	private boolean IsEven(int integer) {
		return integer % 2 == 0;
	}
}