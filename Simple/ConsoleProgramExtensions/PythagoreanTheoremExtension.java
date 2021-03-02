package ConsoleProgramExtensions;
/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import CustomClasses._1973ConsoleProgram;
import acm.program.*;

public class PythagoreanTheoremExtension extends _1973ConsoleProgram {
	public void run() {
		PrintStartingText();
		PythagoreanTheorem();
		PrintEndingInfo();
	}

	// =========================================================================================//

	// Does main things
	private void PythagoreanTheorem() {
		do {
			double firstSide = ReadSide("first");
			double secondSide = ReadSide("second");

			LoadingScreen();

			double hypotenuse = CalculateHypotenuse(firstSide, secondSide);

			PrintHypotenuseInfo(hypotenuse);
		} while (ReadAnswer());
	}

	// Asks user input and returning it
	private double ReadSide(String text) {
		return ReadDouble("You may enter the " + text + " side : ");
	}

	// Calculating hypotenuse according to its sides
	private double CalculateHypotenuse(double firstSide, double secondSide) {
		return Math.sqrt(Square(firstSide) + Square(secondSide));
	}

	// Printing hyptenuse info
	private void PrintHypotenuseInfo(double number) {
		Println("\nThe hypotenuse has been found : " + number);
	}

	// Prints starting text to inform user about program
	private void PrintStartingText() {
		Println("Year 1973 Right Triangle hypotenuse calculator\n");
	}

	// Prints ending text to wish user nice day
	private void PrintEndingInfo() {
		Println("Have a nice day");
	}

	// Reads and returns bool according to the answer received
	private boolean ReadAnswer() {
		return ReadLine("\nDo you want to continue using our calculator (Y/N): ").equals("Y");
	}

	// Squaring number and returning it
	private double Square(double number) {
		return number * number;
	}

}
