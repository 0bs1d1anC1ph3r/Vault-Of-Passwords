package obs1d1anc1ph3r.vaultofpasswords.userinterface;

import javax.crypto.SecretKey;
import obs1d1anc1ph3r.vaultofpasswords.encryption.argon2.Argon2HashManager;
import obs1d1anc1ph3r.vaultofpasswords.encryption.keys.KeyManager;
import obs1d1anc1ph3r.vaultofpasswords.userinterface.utils.PrintLogo;
import obs1d1anc1ph3r.vaultofpasswords.utils.InputMask;

public class StartUpInterface {

	private static final String RED = "\u001B[31m";
	private static final String GREEN = "\u001B[32m";
	private static final String RESET = "\u001B[0m";

	// Do not look at the logo class, it is scary
	public static void startUserInterface() throws InterruptedException, Exception {
		Argon2HashManager hashManager = new Argon2HashManager();
		PrintLogo.printLogo();
		String password = InputMask.maskedInput();
		if (hashManager.isCorrectPassword(password)) {
			System.out.println(GREEN + "Correct password!" + RESET);
			SecretKey vaultKey = KeyManager.readyKeys(password);
			MainMenu.startMainMenuInterface(vaultKey);
		} else {
			System.out.println(RED + "Too many failed attemps :(" + RESET);
		}

		System.out.println("Exiting program...\n");

	}
}
