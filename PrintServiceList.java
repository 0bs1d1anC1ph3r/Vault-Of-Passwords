package obs1d1anc1ph3r.vaultofpasswords.userinterface.utils;

import java.io.IOException;
import java.util.List;
import static obs1d1anc1ph3r.vaultofpasswords.passwords.utils.ServiceListManager.loadServiceList;

import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.BRIGHT_GREEN;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.RAINBOW_COLORS;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.RESET;

public class PrintServiceList {

	private static final String ITALIC = "\u001B[3m";

	public static void PrintServiceList() throws IOException {
		List<String> services = loadServiceList();
		System.out.println(BRIGHT_GREEN + """
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
                                    
                                    
                                    
                                    
                         SERVICES
                     ----------------
                                    """ + RESET);

		for (int i = 0; i < services.size(); i++) {
			String color = RAINBOW_COLORS[i % RAINBOW_COLORS.length];
			System.out.println(color + services.get(i) + RESET);
		}

		System.out.println(BRIGHT_GREEN + """
                                    
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
			+ "\n    SᴱᴿⱽᴵᴄᴱS"
			+ RESET
			+ BRIGHT_GREEN
			+ "\n----------------" + RESET);

	}

}
