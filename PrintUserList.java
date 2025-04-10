package obs1d1anc1ph3r.vaultofpasswords.userinterface.utils;

import java.io.IOException;
import java.util.List;
import obs1d1anc1ph3r.vaultofpasswords.passwords.utils.UserListManager;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.BRIGHT_RED;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.BRIGHT_YELLOW;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.RAINBOW_COLORS;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.RESET;

public class PrintUserList {

	private static final String ITALIC = "\u001B[3m";

	public static void PrintUserList(String service) throws IOException {
		List<String> services = UserListManager.loadUserList(service);
		System.out.println(BRIGHT_YELLOW + """
                     ----------------
                     /\\/\\/\\/\\/\\/\\/\\/\\
                     /|            |\\
                     \\| ___    ___ |/
                     /| ___\\  /___ |\\
                     \\||___|  |___||/
                     /|            |\\
                     \\|            |/
                     /|   [][][]   |\\
                     \\| []/----\\[] |/
                     /|            |\\
                     \\/\\/\\/\\/\\/\\/\\/\\/
                                    
                                    
                                    
                                    
                        USERNAMES
                     ----------------
                                    """ + RESET);

		for (int i = 0; i < services.size(); i++) {
			String color = RAINBOW_COLORS[i % RAINBOW_COLORS.length];
			String passwordFile = services.get(i);

			String[] splitFileContent = passwordFile.split("\\.");

			if (splitFileContent.length > 0) {
				String username = splitFileContent[0];
				System.out.println(color + username + RESET);
			} else {
				System.out.println(BRIGHT_RED + "No usernames found for the service" + RESET);
			}
		}

		System.out.println(BRIGHT_YELLOW + """
                                    
                     ----------------
                       |     |     |
                       |     |     |
                          |     |
                          |     |    
                       |           |
                     /\\/\\/\\/\\/\\/\\/\\/\\
                     /|====    ====|\\
                     \\| ___\\\\//___ |/
                     /||   |  |   ||\\
                     \\||__Q|  |Q__||/
                     /|            |\\
                     \\| []\\----/[] |/
                     /| [][][][][] |\\
                     \\| []/----\\[] |/
                     /|            |\\
                     \\/\\/\\/\\/\\/\\/\\/\\/"""
			+ ITALIC
			+ "\n    ᵤSᴱᵣₙₐₘᴱS"
			+ RESET
			+ BRIGHT_YELLOW
			+ "\n----------------" + RESET);

	}

}
