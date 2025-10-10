package net.yeleefff.moonphase;

import org.slf4j.Logger;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import org.slf4j.LoggerFactory;

@Mod(MoonPhase.MODID)
public class MoonPhase {
    public static final String MODID = "moonphase";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public MoonPhase(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }

    public static int moonPhaseToIndex(String moonPhase) {
        if (moonPhase == null) {
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
