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

import acm.graphics.GRect;
import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int STOPPER = 0;

	public void run() {
		PrintStartingScreen();

		Vector<Integer> numbers = new Vector<Integer>();

		while (AddNumberAndReturn(numbers) != STOPPER) {
		}
		;

		PrintInfo(numbers);
	}

	// Prints starting info for this program
	private void PrintStartingScreen() {
		println("This program finds the largest and smallest numbers.");
	}

	// Adds number to collection and returns it
	private int AddNumberAndReturn(Vector<Integer> numbers) {
		numbers.add(readInt("? "));
		return numbers.lastElement();
	}

	// Prints info what we have to do on each step
	private void PrintInfo(Vector<Integer> numbers) {

		if (numbers.size() > 1) {
			// Sub list without zero
			Collection subList = numbers.subList(0, numbers.size() - 1);
			println("smallest: " + Collections.min(subList));
			println("largest: " + Collections.max(subList));
		} else
			println("es aris shesabamisi mesiji");
	}
}