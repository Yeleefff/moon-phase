package net.yeleefff;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.yeleefff.config.ConfigHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Moonphase implements ModInitializer {
	public static final String MOD_ID = "moonphase";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static String ACTUAL_MOON_PHASE;

	@Override
	public void onInitialize() {
		ConfigHandler.load(FabricLoader.getInstance().getConfigDir());

		ServerWorldEvents.LOAD.register((server, world) -> {
            try {
                ACTUAL_MOON_PHASE = fetchMoonData().get(4).getAsJsonObject().get("phase").getAsString();
            } catch (Exception e) {
                LOGGER.warn("Unable to fetch moon phase data", e);
            }
        });
	}

	public static int moonPhaseToIndex(String moonPhase) {
		if (moonPhase == null) return 0;

		return switch (moonPhase.toLowerCase().trim().replaceAll("\\s", "_")) {
			case "full_moon" -> 0;
			case "waning_gibbous" -> 1;
			case "last_quarter" -> 2;
			case "waning_crescent" -> 3;
			case "new_moon" -> 4;
			case "waxing_crescent" -> 5;
			case "first_quarter" -> 6;
			case "waxing_gibbous" -> 7;
			default -> {
				LOGGER.warn("No valid moon phase set; defaulting to full moon");
				yield 0;
			}
		};
	}

	public static JsonArray fetchMoonData() throws Exception {
		URL url = new URL("https://api.viewbits.com/v1/moonphase");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
			return JsonParser.parseReader(reader).getAsJsonArray();
		}
	}
}