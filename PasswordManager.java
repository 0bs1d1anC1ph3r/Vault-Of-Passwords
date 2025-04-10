package obs1d1anc1ph3r.vaultofpasswords.passwords;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import javax.crypto.SecretKey;
import obs1d1anc1ph3r.vaultofpasswords.encryption.aes.AES;
import obs1d1anc1ph3r.vaultofpasswords.passwords.utils.ServiceListManager;

public class PasswordManager {

	private static final String RED = "\u001B[31m";
	private static final String GREEN = "\u001B[32m";
	private static final String RESET = "\u001B[0m";

	private static final String PASSWORD_DIRECTORY_PATH = ".PASSWORDS/";

	public static void savePassword(String service, String username, String password, SecretKey vaultKey) throws IOException {
		try {
			service = service.toUpperCase();
			directoryGenerator(service);
			generatePasswordFile(service, username, password, vaultKey);
			addToUserList(service, username);
		} catch (Exception ex) {
			System.err.println(RED + "ERROR SAVING PASSWORD" + RESET);
			ex.printStackTrace();
		}
	}

	private static void directoryGenerator(String service) throws IOException {
		Path passwords = Paths.get(PASSWORD_DIRECTORY_PATH);
		String servicePath = PASSWORD_DIRECTORY_PATH + service + "/";
		Path serviceDirectory = Paths.get(servicePath);

		if (Files.notExists(passwords)) {
			Files.createDirectories(passwords);
			System.out.println(GREEN + "Directory created successfully: " + passwords.toString() + RESET);
		}

		if (Files.notExists(serviceDirectory)) {
			Files.createDirectories(serviceDirectory);
			ServiceListManager.updateServiceList(service);
			System.out.println(GREEN + "Directory created successfully: " + serviceDirectory.toString() + RESET);
		}

	}

	private static void generatePasswordFile(String service, String username, String password, SecretKey vaultKey) throws Exception {
		Path passwordFile = Paths.get(PASSWORD_DIRECTORY_PATH + service + "/" + username + ".vault");
		String fileContents = username + ":" + password;
		byte[] fileData = fileContents.getBytes();

		byte[] encryptedPasswordFile = AES.encryptAES(fileData, vaultKey);
		Files.write(passwordFile, encryptedPasswordFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

	}

	private static void addToUserList(String service, String username) throws IOException {
		Path userListFile = Paths.get(PASSWORD_DIRECTORY_PATH + service + "/userlist.csv");

		if (!userListExists(userListFile)) {
			generateUserList(userListFile);
		}

		List<String> usernames = Files.readAllLines(userListFile);

		Collections.sort(usernames);
		if (usernameExists(usernames, username + ".vault")) {
			System.out.println(RED + "Username already exists." + RESET);
			return;
		}

		usernames.add(username + ".vault");
		Collections.sort(usernames);
		Files.write(userListFile, usernames, StandardOpenOption.TRUNCATE_EXISTING);

	}

	private static boolean usernameExists(List<String> usernames, String username) {
		return Collections.binarySearch(usernames, username) >= 0;
	}

	private static boolean userListExists(Path userListFile) {
		return Files.exists(userListFile);
	}

	private static void generateUserList(Path userListFile) throws IOException {
		Files.createFile(userListFile);
	}

}
