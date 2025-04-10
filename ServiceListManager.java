package obs1d1anc1ph3r.vaultofpasswords.passwords.utils;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.BRIGHT_GREEN;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.BRIGHT_RED;
import static obs1d1anc1ph3r.vaultofpasswords.utils.ColorUtil.RESET;

public class ServiceListManager {

	private static final String SERVICE_LIST_PATH = ".PASSWORDS/service_list.csv";

	// Public entry method
	public static void updateServiceList(String service) throws IOException {
		service = service.toUpperCase();
		ensureServiceListFileExists();

		List<String> services = loadServiceList();
		if (!serviceExists(service, services)) {
			addService(service, services);
		} else {
			System.out.println(BRIGHT_RED + "Service already exists: " + service + RESET);
		}
	}

	public static List<String> loadServiceList() throws IOException {
		List<String> services = Files.readAllLines(Paths.get(SERVICE_LIST_PATH));
		Collections.sort(services);
		return services;
	}

	private static void ensureServiceListFileExists() throws IOException {
		Path serviceListPath = Paths.get(SERVICE_LIST_PATH);
		if (Files.notExists(serviceListPath)) {
			Files.createFile(serviceListPath);
			System.out.println(BRIGHT_GREEN + "Service list created: " + serviceListPath + RESET);
		}
	}

	private static boolean serviceExists(String service, List<String> sortedServices) {
		return Collections.binarySearch(sortedServices, service) >= 0;
	}

	private static void addService(String service, List<String> services) throws IOException {
		services.add(service);
		Collections.sort(services);
		Files.write(Paths.get(SERVICE_LIST_PATH), services, StandardOpenOption.TRUNCATE_EXISTING);
	}
}
