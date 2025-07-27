package net.yeleefff.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.yeleefff.Moonphase;
import net.yeleefff.config.ConfigState;

public record SyncConfigS2CPayload(ConfigState configState) implements CustomPayload {
    public static final CustomPayload.Id<SyncConfigS2CPayload> ID = new CustomPayload.Id<>(Identifier.of(Moonphase.MOD_ID, "sync_config"));
    public static final PacketCodec<PacketByteBuf, SyncConfigS2CPayload> CODEC = PacketCodec.tuple(ConfigState.PACKET_CODEC, SyncConfigS2CPayload::configState, SyncConfigS2CPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
