package ConsoleProgramExtensions;
/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import CustomClasses._1973ConsoleProgram;
import acm.graphics.GRect;
import acm.program.*;

public class FindRangeExtension extends _1973ConsoleProgram {
	private static final int STOPPER = 0;

	public void run() {
		PrintStartingText();
		FindRange();
		PrintEndingText();
	}

	// =========================================================================================//

	// Does main thing
	private void FindRange() {
		Vector<Integer> numbers = new Vector<Integer>();

		do {
			numbers.removeAllElements();

			while (AddNumber(numbers) != STOPPER)
				PrintMaximumMinimum(numbers);
		} while (AskToContinue());
	}

	// Adding user number to collection and returning it
	private int AddNumber(Vector<Integer> numbers) {
		numbers.add(ReadInt("\nYou may enter the number : "));
		return numbers.lastElement();
	}

	// Prints info what we have to do on each step
	private void PrintMaximumMinimum(Vector<Integer> numbers) {
		Print("Currently Max is " + Collections.max(numbers) + " and Min is " + Collections.min(numbers));
	}

	// Reads and returns bool according to the answer received
	private boolean AskToContinue() {
		return ReadLine("\nDo you want to continue using our program (Y/N): ").equals("Y");
	}

	// Prints starting text to inform user about program
	private void PrintStartingText() {
		Println("Year 1973 Minimum-Maximum finder");
		Println("Type \"" + STOPPER + "\" to stop the program");
	}

	// Prints ending text to wish user nice day
	private void PrintEndingText() {
		Println("\nHave a nice day");
	}
}
