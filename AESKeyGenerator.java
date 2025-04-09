package obs1d1anc1ph3r.vaultofpasswords.encryption.aes;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AESKeyGenerator {

	// Method to generate a AES key
	public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		return keyGen.generateKey();
	}

	// Method to generate IVs
	public static IvParameterSpec generateIV() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

}
