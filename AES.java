package obs1d1anc1ph3r.vaultofpasswords.encryption.aes;

import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class AES {

	private static final int GCM_IV_LENGTH = 12;
	private static final int GCM_TAG_LENGTH = 128;

	// Method to encrypt data
	public static byte[] encryptAES(byte[] data, SecretKey key) throws Exception {
		byte[] iv = new byte[GCM_IV_LENGTH];
		SecureRandom random = new SecureRandom();
		random.nextBytes(iv);

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
		cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);

		byte[] cipherText = cipher.doFinal(data);
		byte[] result = new byte[iv.length + cipherText.length];
		System.arraycopy(iv, 0, result, 0, iv.length);
		System.arraycopy(cipherText, 0, result, iv.length, cipherText.length);

		return result;
	}

	// Method to decrypt data
	public static byte[] decryptAES(byte[] encryptedData, SecretKey key) throws Exception {
		if (encryptedData.length < GCM_IV_LENGTH) {
			throw new IllegalArgumentException("Invalid data length");
		}

		byte[] iv = Arrays.copyOfRange(encryptedData, 0, GCM_IV_LENGTH);
		byte[] cipherText = Arrays.copyOfRange(encryptedData, GCM_IV_LENGTH, encryptedData.length);

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
		cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);

		return cipher.doFinal(cipherText);
	}

}
