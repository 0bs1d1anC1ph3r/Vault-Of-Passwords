package obs1d1anc1ph3r.vaultofpasswords.encryption.argon2;

import com.password4j.Argon2Function;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Argon2;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Argon2KeyGenerator {

	public static SecretKey deriveKey(String password, byte[] salt) throws Exception {
		int iterations = 6;
		int memory = 65536;
		int parallelism = 1;
		int hashLength = 32;

		Argon2Function function = Argon2Function.getInstance(iterations, memory, parallelism, hashLength, Argon2.ID);

		Hash hash = Password.hash(password)
			.addSalt(salt)
			.with(function);

		String fullResult = hash.getResult();

		String[] parts = fullResult.split("\\$");
		if (parts.length < 5) {
			throw new IllegalStateException("Unexpected hash format: " + fullResult);
		}
		String base64Hash = parts[parts.length - 1];
		byte[] keyBytes = Base64.getDecoder().decode(base64Hash);

		return new SecretKeySpec(keyBytes, "AES");
	}
}
