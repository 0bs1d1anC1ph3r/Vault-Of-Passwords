package obs1d1anc1ph3r.vaultofpasswords.userinterface;

import javax.crypto.SecretKey;

import obs1d1anc1ph3r.vaultofpasswords.encryption.argon2.Argon2HashManager;
import obs1d1anc1ph3r.vaultofpasswords.encryption.keys.KeyManager;
import obs1d1anc1ph3r.vaultofpasswords.userinterface.utils.PrintLogo;

import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.BRIGHT_GREEN;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.BRIGHT_RED;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.RESET;
import obs1d1anc1ph3r.vaultofpasswords.utils.InputMask;

public class StartUpInterface {

	private static String password;

	public StartUpInterface() throws Exception {
		Argon2HashManager hashManager = new Argon2HashManager();
		PrintLogo.printLogo();
		password = InputMask.maskedInput();
		startUserInterface(hashManager);
	}

	// Do not look at the logo class, it is scary
	private void startUserInterface(Argon2HashManager hashManager) throws InterruptedException, Exception {

		if (hashManager.isCorrectPassword(password)) {
			System.out.println(BRIGHT_GREEN + "Correct password!" + RESET);
			SecretKey vaultKey = KeyManager.readyKeys(password);
			MainMenu.startMainMenuInterface(vaultKey);
		} else {
			System.out.println(BRIGHT_RED + "Too many failed attemps :(" + RESET);
		}

		System.out.println("Exiting program...\n");

	}

	public static void setPassword(String password) {
		StartUpInterface.password = password;
	}

}
