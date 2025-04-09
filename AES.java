package obs1d1anc1ph3r.vaultofpasswords.encryption.aes;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AES {

	// Method to encrypt data
	public byte[] encryptAES(byte[] data, SecretKey key, IvParameterSpec iv) throws Exception {
		if (data == null || key == null || iv == null) {
			throw new IllegalArgumentException("Data, key, and IV must not be null");
		}

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);

		byte[] encrypted = cipher.doFinal(data);
		byte[] ivAndCipherText = new byte[iv.getIV().length + encrypted.length];
		System.arraycopy(iv.getIV(), 0, ivAndCipherText, 0, iv.getIV().length);
		System.arraycopy(encrypted, 0, ivAndCipherText, iv.getIV().length, encrypted.length);

		return ivAndCipherText;
	}

	// Method to decrypt data
	public byte[] decryptAES(String encryptedData, SecretKey key) throws Exception {
		if (encryptedData == null || key == null) {
			throw new IllegalArgumentException("Encrypted data and key must not be null");
		}

		byte[] decoded = Base64.getDecoder().decode(encryptedData);
		byte[] ivBytes = new byte[16];
		byte[] cipherText = new byte[decoded.length - ivBytes.length];

		System.arraycopy(decoded, 0, ivBytes, 0, ivBytes.length);
		System.arraycopy(decoded, ivBytes.length, cipherText, 0, cipherText.length);

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivBytes));
		byte[] decrypted = cipher.doFinal(cipherText);

		return decrypted;
	}
}
