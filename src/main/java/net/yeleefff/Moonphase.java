package net.yeleefff;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.*;
import net.fabricmc.loader.api.FabricLoader;
import net.yeleefff.config.ConfigHandler;
import net.yeleefff.network.SyncConfigS2CPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Moonphase implements ModInitializer {
	public static final String MOD_ID = "moonphase";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ConfigHandler.load(FabricLoader.getInstance().getConfigDir());
		PayloadTypeRegistry.configurationS2C().register(SyncConfigS2CPayload.ID, SyncConfigS2CPayload.CODEC);

		ServerConfigurationConnectionEvents.CONFIGURE.register((handler, server) -> {
			if (ServerConfigurationNetworking.canSend(handler, SyncConfigS2CPayload.ID)) {
				ConfigHandler.load(FabricLoader.getInstance().getConfigDir());
				handler.send(ServerPlayNetworking.createS2CPacket(new SyncConfigS2CPayload(ConfigHandler.getState())));
			}
		});
	}

	public static int moonPhaseToIndex(String moonPhase) {
		if (moonPhase == null){
			LOGGER.warn("moonPhase is null; defaulting to full moon");
			return 0;
		}

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
}