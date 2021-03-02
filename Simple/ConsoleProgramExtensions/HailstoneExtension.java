package ConsoleProgramExtensions;
/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import CustomClasses._1973ConsoleProgram;
import acm.program.*;

public class HailstoneExtension extends _1973ConsoleProgram {
	public void run() {
		PrintStartingText();
		Hailstone();
		PrintEndingText();
	}

	// =========================================================================================//

	// Does main tasks
	private void Hailstone() {
		do {
			int number = AskNumber();
			while (number != 1) {
				PrintNumberInfo(number);
				LoadingScreen();
				number = DoOperation(number);
			}
		} while (AskToContinue());
	}

	// Does operation according to the number we have
	private int DoOperation(int integer) {
		if (IsEven(integer))
			return integer / 2;
		else
			return integer * 3 + 1;
	}

	// Checks if integer is even and returns bool
	private boolean IsEven(int integer) {
		return integer % 2 == 0;
	}

	private int AskNumber() {
		return ReadInt("\nYou may enter the number : ");
	}

	// Reads and returns bool according to the answer received
	private boolean AskToContinue() {
		return ReadLine("\nDo you want to continue using our program (Y/N): ").equals("Y");
	}

	// Prints info what we have to do on each step
	private void PrintNumberInfo(int integer) {
		if (integer == 1)
			Print("Number is now 1 and we have no proof why :P\n");
		else if (IsEven(integer))
			Print("Number is now " + integer + " so we /2 and we would get " + integer / 2);
		else
			Print("Number is now " + integer + " so we *3+1 and we would get " + (integer * 3 + 1));
	}

	// Prints starting text to inform user about program
	private void PrintStartingText() {
		Println("Year 1973 Hailstone problem calculator");
	}

	// Prints ending text to wish user nice day
	private void PrintEndingText() {
		Println("\nHave a nice day");
	}
}