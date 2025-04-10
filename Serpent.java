package obs1d1anc1ph3r.vaultofpasswords.encryption.serpent;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Serpent {

	private static final int IV_LENGTH = 16;

	// Method to encrypt data
	public byte[] encryptSerpent(byte[] data, SecretKey key) throws Exception {
		byte[] iv = new byte[IV_LENGTH];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(iv);
		IvParameterSpec ivParams = new IvParameterSpec(iv);

		Cipher cipher = Cipher.getInstance("Serpent/CBC/PKCS5Padding", "BC");
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);
		byte[] encryptedData = cipher.doFinal(data);
		byte[] ivAndCipherText = new byte[iv.length + encryptedData.length];
		System.arraycopy(iv, 0, ivAndCipherText, 0, iv.length);
		System.arraycopy(encryptedData, 0, ivAndCipherText, iv.length, encryptedData.length);

		return ivAndCipherText;
	}

	// Method to decrypt data
	public byte[] decryptSerpent(byte[] ivAndCipherText, SecretKey key) throws Exception {
		byte[] iv = new byte[IV_LENGTH];
		System.arraycopy(ivAndCipherText, 0, iv, 0, IV_LENGTH);
		byte[] encryptedData = new byte[ivAndCipherText.length - IV_LENGTH];
		System.arraycopy(ivAndCipherText, IV_LENGTH, encryptedData, 0, encryptedData.length);
		IvParameterSpec ivParams = new IvParameterSpec(iv);

		Cipher cipher = Cipher.getInstance("Serpent/CBC/PKCS5Padding", "BC");
		cipher.init(Cipher.DECRYPT_MODE, key, ivParams);
		return cipher.doFinal(encryptedData);
	}
}
