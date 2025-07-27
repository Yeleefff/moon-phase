package net.yeleefff.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

public class ConfigState {
    public boolean constantMoonPhase;
    public String moonPhase;

    public static final Codec<ConfigState> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("constantMoonPhase").forGetter(ConfigState::isMoonPhaseConstant),
            Codec.STRING.fieldOf("moonPhase").forGetter(ConfigState::getMoonPhase)
            ).apply(instance, ConfigState::new));
    public static final PacketCodec<ByteBuf, ConfigState> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.BOOLEAN, ConfigState::isMoonPhaseConstant,
            PacketCodecs.STRING, ConfigState::getMoonPhase,
            ConfigState::new);

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
