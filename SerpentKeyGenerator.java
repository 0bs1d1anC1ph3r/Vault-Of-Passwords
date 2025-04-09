package obs1d1anc1ph3r.vaultofpasswords.encryption.serpent;

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SerpentKeyGenerator {

	// Method to generate a serpent key
	public static SecretKey generateSerpentKey() throws NoSuchAlgorithmException {
		Security.addProvider(new BouncyCastleProvider());
		KeyGenerator keyGen = KeyGenerator.getInstance("Serpent");
		keyGen.init(256);
		return keyGen.generateKey();
	}

}
