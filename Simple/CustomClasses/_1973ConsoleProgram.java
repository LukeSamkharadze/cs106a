package CustomClasses;

import acm.program.ConsoleProgram;

public class _1973ConsoleProgram extends ConsoleProgram {
	// Reads and returns integer entered by user
	protected int ReadInt(String text) {
		return Integer.parseInt(ReadLine(text));
	}

	// Reads double from user and returns it
	protected double ReadDouble(String string) {
		return Double.parseDouble(ReadLine(string));
	}

	// Reads line from user and returns it
	protected String ReadLine(String string) {
		Print(string);
		return readLine();
	}

	// Prints string
	protected void Print(String string) {
		for (int i = 0; i < string.length(); i++) {
			print(string.charAt(i));
			pause(50);
		}
	}

	// Prints string and new line after that
	protected void Println(String string) {
		Print(string);
		Print("\n");
	}

	// Animate Loading
	protected void LoadingScreen() {
		Print(" ");
		for (int i = 0; i < 3; i++) {
			Print(".");
			pause(100);
		}
		Println("");
	}
}
