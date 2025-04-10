package obs1d1anc1ph3r.vaultofpasswords.utils;

import java.io.Console;

public class InputMask {

	private static final String RED = "\u001B[31m";
	private static final String RESET = "\u001B[0m";

	// Sneaky input, but does need to be run in a terminal
	public static String maskedInput() {
		Console console = System.console();

		if (console == null) {
			System.out.println(RED + "\nProgram is not running in a terminal, please run the program in a terminal" + RESET);
			System.exit(0);
		}

		char[] inputArray = console.readPassword("Enter your password: ");
		String input = new String(inputArray);
		return input;

	}
}
