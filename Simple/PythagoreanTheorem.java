/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		PrintStartingInfo();

		int firstSide = readInt("a: ");
		int secondSide = readInt("b: ");

		double hypotenuse = CalculateHypotenuse(firstSide, secondSide);
		println("c = " + hypotenuse);
	}

	private void PrintStartingInfo() {
		println("Enter values to compute Pythagorean theorem.");
	}

	private double CalculateHypotenuse(double firstSide, double secondSide) {
		return Math.sqrt(Math.pow(firstSide, 2) + Math.pow(secondSide, 2));
	}
}