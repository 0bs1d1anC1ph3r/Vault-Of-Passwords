package obs1d1anc1ph3r.vaultofpasswords.utils;

import java.io.Console;

public class InputMask {

	// Sneaky input, but does need to be run in a terminal
	public static String maskedInput() {
		Console console = System.console();

		if (console == null) {
			System.out.println("Program is not running in a terminal, please run the program in a terminal");
			return null;
		}

		char[] inputArray = console.readPassword("Enter your password: ");
		String input = new String(inputArray);
		return input;

	}
}
