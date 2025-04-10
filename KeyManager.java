package obs1d1anc1ph3r.vaultofpasswords.encryption.keys;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import obs1d1anc1ph3r.vaultofpasswords.encryption.aes.AES;
import obs1d1anc1ph3r.vaultofpasswords.encryption.aes.AESKeyGenerator;
import obs1d1anc1ph3r.vaultofpasswords.encryption.argon2.Argon2KeyGenerator;

public class KeyManager {

	private static final String KEY_DIRECTORY = ".KEYS/";
	private static final String VAULT_KEY_FILE = ".KEYS/vault.key";
	private static final int SALT_LENGTH = 16;

	private static final String RED = "\u001B[31m";
	private static final String RESET = "\u001B[0m";
	private static final String GREEN = "\u001B[32m";

	public static SecretKey readyKeys(String passphrase) throws Exception {
		System.out.println("\nAttempting to load keys...");

		if (!Files.isDirectory(Paths.get(KEY_DIRECTORY))) {
			keyDirectoryGenerator();
		}

		Path vaultKeyPath = Paths.get(VAULT_KEY_FILE);

		if (!keysExist(vaultKeyPath)) {
			System.err.println(RED + "Key files not found." + RESET);
			System.out.println("Generating new keys...");
			generateKeyFiles(passphrase, vaultKeyPath);
		}

		return loadKeys(vaultKeyPath, passphrase);
	}

	private static void generateKeyFiles(String passphrase, Path vaultKeyPath) {
		try {
			byte[] salt = new byte[SALT_LENGTH];
			new SecureRandom().nextBytes(salt);

			SecretKey skeletonKey = Argon2KeyGenerator.deriveKey(passphrase, salt);
			SecretKey vaultKey = AESKeyGenerator.generateAESKey();

			saveKeys(vaultKey, vaultKeyPath, skeletonKey, salt);
			System.out.println(GREEN + "Keys generated and saved successfully." + RESET);
		} catch (Exception ex) {
			System.err.println(RED + "ERROR GENERATING KEYS: " + ex.getMessage() + RESET);
			System.out.println("Exiting program...\n");
			System.exit(15);
		}

	}

	private static void saveKeys(SecretKey vaultKey, Path vaultKeyPath, SecretKey skeletonKey, byte[] salt) throws Exception {
		try (FileOutputStream fos = new FileOutputStream(vaultKeyPath.toFile())) {
			byte[] vaultKeyEncoded = vaultKey.getEncoded();
			byte[] encryptedVaultKey = AES.encryptAES(vaultKeyEncoded, skeletonKey);
			fos.write(salt);
			fos.write(encryptedVaultKey);
		} catch (IOException e) {
			System.err.println(RED + "ERROR SAVING KEYS: " + e.getMessage() + RESET);
			throw e;
		} catch (GeneralSecurityException e) {
			System.err.println(RED + "SECURITY ERROR: " + e.getMessage() + RESET);
			throw e;
		}
	}

	private static SecretKey loadKeys(Path vaultKeyPath, String passphrase) throws Exception {
		try {
			byte[] fileContent = Files.readAllBytes(vaultKeyPath);
			byte[] salt = Arrays.copyOfRange(fileContent, 0, SALT_LENGTH);
			byte[] encryptedVaultKey = Arrays.copyOfRange(fileContent, SALT_LENGTH, fileContent.length);
			SecretKey skeletonKey = Argon2KeyGenerator.deriveKey(passphrase, salt);
			byte[] decryptedVaultKey = AES.decryptAES(encryptedVaultKey, skeletonKey);
			return new SecretKeySpec(decryptedVaultKey, "AES");
		} catch (Exception ex) {
			System.err.println(RED + "ERROR LOADING KEYS: " + ex.getMessage() + RESET);
			throw ex;
		}
	}

	private static void keyDirectoryGenerator() throws IOException {
		Files.createDirectories(Paths.get(KEY_DIRECTORY));
	}

	private static boolean keysExist(Path vaultKeyPath) {
		return Files.exists(vaultKeyPath);
	}
}
