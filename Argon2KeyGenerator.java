package obs1d1anc1ph3r.vaultofpasswords.encryption.argon2;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class Argon2KeyGenerator {

	private final Argon2 argon2 = Argon2Factory.create();
	private static final int IV_LENGTH = 16;

	// Method to derive a key from a password
	public SecretKey deriveKey(String password) throws Exception {
		String hash = argon2.hash(2, 65536, 1, password);
		byte[] keyBytes = hash.getBytes();
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] key = digest.digest(keyBytes);
		return new SecretKeySpec(key, "AES");
	}

	// Method to encrypt data
	public byte[] encrypt(byte[] data, SecretKey key) throws Exception {
		byte[] iv = new byte[IV_LENGTH];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(iv);
		IvParameterSpec ivParams = new IvParameterSpec(iv);

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);
		byte[] encrypted = cipher.doFinal(data);

		byte[] ivAndCipherText = new byte[iv.length + encrypted.length];
		System.arraycopy(iv, 0, ivAndCipherText, 0, iv.length);
		System.arraycopy(encrypted, 0, ivAndCipherText, iv.length, encrypted.length);

		return ivAndCipherText;
	}

	// Method to decrypt data
	public byte[] decrypt(byte[] ivAndCipherText, SecretKey key) throws Exception {
		byte[] iv = Arrays.copyOfRange(ivAndCipherText, 0, IV_LENGTH);
		byte[] encryptedData = Arrays.copyOfRange(ivAndCipherText, IV_LENGTH, ivAndCipherText.length);
		IvParameterSpec ivParams = new IvParameterSpec(iv);

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, ivParams);
		return cipher.doFinal(encryptedData);
	}
}
