package net.yeleefff.moonphase;

import net.neoforged.neoforge.common.ModConfigSpec;


public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue CONSTANT_MOON_PHASE = BUILDER
            .comment("Controls whether a specific moon phase should repeat")
            .define("constantMoonPhase", false);
    public static final ModConfigSpec.ConfigValue<String> MOON_PHASE = BUILDER
            .comment("The moon phase to repeat. Accepts: full moon, waning gibbous, last quarter, waning crescent, new moon, waxing crescent, first quarter, or waxing gibbous")
            .define("moonPhase", "full moon");

    static final ModConfigSpec SPEC = BUILDER.build();
}
