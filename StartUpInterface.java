package obs1d1anc1ph3r.vaultofpasswords.userinterface;

import obs1d1anc1ph3r.vaultofpasswords.encryption.argon2.Argon2HashManager;
import obs1d1anc1ph3r.vaultofpasswords.userinterface.utils.PrintLogo;
import obs1d1anc1ph3r.vaultofpasswords.utils.InputMask;

public class StartUpInterface {

	// Do not look at the logo class, it is scary
	public static void startUserInterface() {
		Argon2HashManager hashManager = new Argon2HashManager();
		PrintLogo.printLogo();
		String password = InputMask.maskedInput();
		hashManager.isCorrectPassword(password);
	}
}
