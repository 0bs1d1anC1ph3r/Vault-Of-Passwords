package obs1d1anc1ph3r.vaultofpasswords.passwords;

import java.security.SecureRandom;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.BRIGHT_RED;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.RESET;

public class PasswordGenerator {

	private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	private static final String DIGITS = "0123456789";
	private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

	private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

	private static final SecureRandom secureRandom = new SecureRandom();

	public static String generateSecurePassword(int length) {
		if (length < 12) {
			throw new IllegalArgumentException(BRIGHT_RED + "Password length should be at least 12 characters." + RESET);
		}

		StringBuilder password = new StringBuilder(length);

		password.append(UPPERCASE.charAt(secureRandom.nextInt(UPPERCASE.length())));
		password.append(LOWERCASE.charAt(secureRandom.nextInt(LOWERCASE.length())));
		password.append(DIGITS.charAt(secureRandom.nextInt(DIGITS.length())));
		password.append(SPECIAL_CHARACTERS.charAt(secureRandom.nextInt(SPECIAL_CHARACTERS.length())));

		for (int i = 4; i < length; i++) {
			password.append(ALL_CHARACTERS.charAt(secureRandom.nextInt(ALL_CHARACTERS.length())));
		}

		return shuffleString(password.toString());
	}

	private static String shuffleString(String input) {
		char[] characters = input.toCharArray();
		for (int i = characters.length - 1; i > 0; i--) {
			int j = secureRandom.nextInt(i + 1);
			char temp = characters[i];
			characters[i] = characters[j];
			characters[j] = temp;
		}
		return new String(characters);
	}
}
