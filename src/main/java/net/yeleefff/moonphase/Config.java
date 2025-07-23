package net.yeleefff.moonphase;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = MoonPhase.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue CONSTANT_MOON_PHASE = BUILDER
            .comment("Controls whether a specific moon phase should repeat")
            .define("constantMoonPhase", false);
    private static final ForgeConfigSpec.ConfigValue<String> MOON_PHASE = BUILDER
            .comment("The moon phase to repeat. Accepts: full moon, waning gibbous, last quarter, waning crescent, new moon, waxing crescent, first quarter, waxing gibbous")
            .define("moonPhase", "full moon");

    static final ForgeConfigSpec SPEC = BUILDER.build();
    public static boolean constantMoonPhase;
    public static String moonPhase;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        constantMoonPhase = CONSTANT_MOON_PHASE.get();
        moonPhase = MOON_PHASE.get();
    }
}
