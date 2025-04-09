package obs1d1anc1ph3r.vaultofpasswords.encryption.serpent;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Serpent {

	// Method to encrypt data
	public byte[] encryptSerpent(byte[] data, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("Serpent/ECB/PKCS5Padding", "BC");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedData = cipher.doFinal(data);
		return encryptedData;
	}

	// Method to decrypt data
	public byte[] decryptSerpent(byte[] data, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("Serpent/ECB/PKCS5Padding", "BC");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedData = cipher.doFinal(data);
		return decryptedData;
	}

}
