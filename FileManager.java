package obs1d1anc1ph3r.vaultofpasswords.passwords;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javax.crypto.SecretKey;
import obs1d1anc1ph3r.vaultofpasswords.encryption.aes.AES;
import obs1d1anc1ph3r.vaultofpasswords.passwords.utils.ServiceListManager;

public class FileManager {

	private static final String RED = "\u001B[31m";
	private static final String GREEN = "\u001B[32m";
	private static final String YELLOW = "\u001B[33m";
	private static final String RESET = "\u001B[0m";

	private static final String PASSWORD_DIRECTORY_PATH = ".PASSWORDS/";

	public static void directoryGenerator(String service) throws IOException {
		Path passwords = Paths.get(PASSWORD_DIRECTORY_PATH);
		String servicePath = PASSWORD_DIRECTORY_PATH + service + "/";
		Path serviceDirectory = Paths.get(servicePath);

		if (Files.notExists(passwords)) {
			Files.createDirectories(passwords);
			System.out.println(YELLOW + "Directory created: " + passwords.toString() + RESET);
		}

		if (Files.notExists(serviceDirectory)) {
			Files.createDirectories(serviceDirectory);
			ServiceListManager.updateServiceList(service);
			System.out.println(YELLOW + "Service directory created: " + serviceDirectory.toString() + RESET);
		}

	}

	public static void generatePasswordFile(String service, String username, String password, SecretKey vaultKey) throws Exception {
		Path passwordFile = Paths.get(PASSWORD_DIRECTORY_PATH + service + "/" + username + ".vault");
		String fileContents = username + ":" + password;
		byte[] fileData = fileContents.getBytes();

		byte[] encryptedPasswordFile = AES.encryptAES(fileData, vaultKey);
		Files.write(passwordFile, encryptedPasswordFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

	}

}
