package obs1d1anc1ph3r.vaultofpasswords.passwords.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.BRIGHT_RED;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.RESET;

public class UserListManager {

	private static final String PASSWORD_DIRECTORY_PATH = ".PASSWORDS/";

	public static void addToUserList(String service, String username) throws IOException {
		Path userListFile = Paths.get(PASSWORD_DIRECTORY_PATH + service + "/userlist.csv");

		if (!userListExists(service)) {
			generateUserList(userListFile);
		}

		List<String> usernames = Files.readAllLines(userListFile);

		Collections.sort(usernames);
		if (usernameExists(usernames, username + ".vault")) {
			System.out.println(BRIGHT_RED + "Username already exists for this service." + RESET);
			return;
		}

		usernames.add(username + ".vault");
		Collections.sort(usernames);
		Files.write(userListFile, usernames, StandardOpenOption.TRUNCATE_EXISTING);

	}

	public static List<String> loadUserList(String service) throws IOException {
		service = service.toUpperCase();
		if (!userListExists(service)) {
			return null;
		}

		Path userListFile = Paths.get(PASSWORD_DIRECTORY_PATH + service + "/userlist.csv");
		List<String> usernames = Files.readAllLines(userListFile);
		Collections.sort(usernames);
		return usernames;
	}

	private static boolean usernameExists(List<String> usernames, String username) {
		return Collections.binarySearch(usernames, username) >= 0;
	}

	private static boolean userListExists(String service) {
		Path userListFile = Paths.get(PASSWORD_DIRECTORY_PATH + service + "/userlist.csv");
		return Files.exists(userListFile);
	}

	private static void generateUserList(Path userListFile) throws IOException {
		Files.createFile(userListFile);
	}

}
