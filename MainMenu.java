package obs1d1anc1ph3r.vaultofpasswords.userinterface;

import java.io.IOException;
import javax.crypto.SecretKey;
import obs1d1anc1ph3r.vaultofpasswords.passwords.PasswordManager;
import obs1d1anc1ph3r.vaultofpasswords.userinterface.utils.PrintServiceList;
import obs1d1anc1ph3r.vaultofpasswords.userinterface.utils.PrintUserList;

public class MainMenu {

	public static void startMainMenuInterface(SecretKey vaultKey) throws IOException {
		PasswordManager.savePassword("bbbbb", "Username", "password", vaultKey);
		PasswordManager.savePassword("69", "Username", "password", vaultKey);
		PasswordManager.savePassword("69", "a", "password", vaultKey);
		PasswordManager.savePassword("69", "b", "password", vaultKey);
		PasswordManager.savePassword("69", "c", "password", vaultKey);
		PasswordManager.savePassword("69", "d", "password", vaultKey);
		PasswordManager.savePassword("56", "e", "password", vaultKey);
		PasswordManager.savePassword("69", "FUCK", "password", vaultKey);
		PasswordManager.savePassword("69", "HELP", "password", vaultKey);
		PasswordManager.savePassword("69", "xX_DarkReaper_Xx", "password", vaultKey);
		PasswordManager.savePassword("69", "4243", "password", vaultKey);
		PasswordManager.savePassword("56", "xX_DarkReaper_Xx", "password", vaultKey);
		PasswordManager.savePassword("4", "Username", "password", vaultKey);
		PasswordManager.savePassword("aaaaaa", "Username", "password", vaultKey);
		PasswordManager.savePassword("aaas", "Username", "password", vaultKey);
		PasswordManager.savePassword("asss", "Username", "password", vaultKey);
		PasswordManager.savePassword("56assss", "Username", "password", vaultKey);
		PasswordManager.savePassword("olololo4", "Username", "password", vaultKey);
		PasswordManager.savePassword("aaFUCKaaaa", "Username", "password", vaultKey);
		PasswordManager.savePassword("aaa", "Username", "password", vaultKey);
		PasswordManager.savePassword("aaFUwdasCKaaaa", "Username", "password", vaultKey);
		PasswordManager.savePassword("wasdwasd", "Username", "password", vaultKey);
		PasswordManager.savePassword("w", "Username", "password", vaultKey);
		PasswordManager.savePassword("e", "Username", "password", vaultKey);
		PrintServiceList.PrintServiceList();
		PrintUserList.PrintUserList("69");
	}

}
