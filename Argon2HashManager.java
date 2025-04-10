package obs1d1anc1ph3r.vaultofpasswords.encryption.argon2;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import obs1d1anc1ph3r.vaultofpasswords.utils.InputMask;

public class Argon2HashManager {

	private final Argon2 argon2 = Argon2Factory.create();
	private static final String PASSWORD_HASH_PATH = ".HASHES/password.hash";
	private static final String HASH_DIRECTORY = ".HASHES/";
	private static final int ATTEMPTS = 2;

	private static final String RED = "\u001B[31m";
	private static final String RESET = "\u001B[0m";
	private static final String GREEN = "\u001B[32m";
	private static final String YELLOW = "\u001B[33m";

	// Method to check if the provided password is correct
	public boolean isCorrectPassword(String password) {
		try {
			Path filePath = Paths.get(PASSWORD_HASH_PATH);
			return hashMatch(filePath, password);
		} catch (IOException ex) {
			System.out.println(RED + "ERROR VERIFYING PASSWORD" + RESET);
			return false;
		}
	}

	// Method to get the hash of the password and save it to a file
	private String generateHash(String password) {
		String hash = argon2.hash(2, 65536, 1, password);
		return hash;
	}

	// Method to check if the hash file exists
	private boolean hashExists(Path filePath) {
		if (!Files.exists(filePath)) {
			System.err.println(YELLOW + "Password does not exist. Please create a new password." + RESET);
			String password = InputMask.maskedInput();
			String hashedPassword = generateHash(password);
			saveHashFile(hashedPassword, filePath);
			return false;
		}
		return true;
	}

	// Method to compare the provided password with the stored hash
	private boolean hashMatch(Path filePath, String password) throws IOException {

		if (!Files.isDirectory(Paths.get(HASH_DIRECTORY))) {
			generateHashDirectory();
		}

		if (!hashExists(filePath)) {
			System.out.println(GREEN + "Password created" + RESET);
			password = InputMask.maskedInput();
		}

		String storedHash;
		try {
			storedHash = Files.readString(filePath);
		} catch (IOException e) {
			System.err.println(RED + "Error reading the hash file: " + e.getMessage() + RESET);
			return false;
		}

		for (int i = 0; i < ATTEMPTS; i++) {
			if (argon2.verify(storedHash, password)) {
				return true;
			} else {
				System.out.println(RED + "Incorrect password. Attempts remaining: " + (ATTEMPTS - i) + RESET);
				password = InputMask.maskedInput();
			}
		}
		return false;
	}

	// Method to save the hashed password to a file
	private void saveHashFile(String hashedPassword, Path filePath) {
		try {
			Files.writeString(filePath, hashedPassword);
			System.out.println(GREEN + "Password saved successfully." + RESET);
		} catch (IOException e) {
			System.err.println(RED + "Error saving the password: " + e.getMessage() + RESET);
		}
	}

	// Create hidden directory
	private void generateHashDirectory() throws IOException {
		Files.createDirectories(Paths.get(HASH_DIRECTORY));
	}

}
