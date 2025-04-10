package obs1d1anc1ph3r.vaultofpasswords.encryption.aes;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESKeyGenerator {

	public static final int GCM_NONCE_LENGTH = 12;
	private static final SecureRandom secureRandom = new SecureRandom();

	public static byte[] generateNonce() {
		byte[] nonce = new byte[GCM_NONCE_LENGTH];
		secureRandom.nextBytes(nonce);
		return nonce;
	}

	// Method to generate an AES key with a specified key size
	public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		return keyGen.generateKey();
	}

}
