package obs1d1anc1ph3r.vaultofpasswords.passwords;

import java.io.IOException;
import javax.crypto.SecretKey;

import obs1d1anc1ph3r.vaultofpasswords.passwords.utils.UserListManager;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.BRIGHT_RED;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.RESET;

public class PasswordManager {

	public static void savePassword(String service, String username, String password, SecretKey vaultKey) throws IOException {
		try {
			service = service.toUpperCase();
			FileManager.directoryGenerator(service);
			FileManager.generatePasswordFile(service, username, password, vaultKey);
			UserListManager.addToUserList(service, username);
		} catch (Exception ex) {
			System.err.println(BRIGHT_RED + "ERROR SAVING PASSWORD" + RESET);
		}
	}

}
