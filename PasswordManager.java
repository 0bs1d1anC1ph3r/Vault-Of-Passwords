package obs1d1anc1ph3r.vaultofpasswords.passwords;

import java.io.IOException;
import javax.crypto.SecretKey;

import obs1d1anc1ph3r.vaultofpasswords.passwords.utils.UserListManager;

public class PasswordManager {

	private static final String RED = "\u001B[31m";
	private static final String GREEN = "\u001B[32m";
	private static final String RESET = "\u001B[0m";

	public static void savePassword(String service, String username, String password, SecretKey vaultKey) throws IOException {
		try {
			service = service.toUpperCase();
			FileManager.directoryGenerator(service);
			FileManager.generatePasswordFile(service, username, password, vaultKey);
			UserListManager.addToUserList(service, username);
		} catch (Exception ex) {
			System.err.println(RED + "ERROR SAVING PASSWORD" + RESET);
		}
	}

}
