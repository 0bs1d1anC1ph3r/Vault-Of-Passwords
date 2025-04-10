package obs1d1anc1ph3r.vaultofpasswords.userinterface;

import java.io.IOException;
import javax.crypto.SecretKey;
import obs1d1anc1ph3r.vaultofpasswords.passwords.PasswordManager;

public class MainMenu {

	public static void startMainMenuInterface(SecretKey vaultKey) throws IOException {
		PasswordManager.savePassword("bbbbb", "Username", "password", vaultKey);
	}

}
