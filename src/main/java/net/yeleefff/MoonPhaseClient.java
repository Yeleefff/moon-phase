package net.yeleefff;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.yeleefff.config.ConfigHandler;
import net.yeleefff.network.SyncConfigS2CPayload;

public class MoonPhaseClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientConfigurationNetworking.registerGlobalReceiver(SyncConfigS2CPayload.ID, (payload, context) -> {
            ConfigHandler.save(payload.configState());
            ConfigHandler.load(FabricLoader.getInstance().getConfigDir());
        });
    }
}
