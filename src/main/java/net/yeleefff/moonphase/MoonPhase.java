package net.yeleefff.moonphase;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MoonPhase.MOD_ID)
public class MoonPhase {
    public static final String MOD_ID = "moonphase";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MoonPhase(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");

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
