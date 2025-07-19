package net.yeleefff.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class ConfigState {
    public boolean constantMoonPhase;
    public String moonPhase;

    public static final Codec<ConfigState> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("constantMoonPhase").forGetter(ConfigState::isMoonPhaseConstant),
            Codec.STRING.fieldOf("moonPhase").forGetter(ConfigState::getMoonPhase)
            ).apply(instance, ConfigState::new));

    public ConfigState(boolean constantMoonPhase, String moonPhase) {
        this.constantMoonPhase = constantMoonPhase;
        this.moonPhase = moonPhase;
    }

    public boolean isMoonPhaseConstant() {
        return this.constantMoonPhase;
    }

    public String getMoonPhase() {
        return this.moonPhase;
    }
}
