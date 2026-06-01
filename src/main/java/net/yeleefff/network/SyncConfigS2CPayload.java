package net.yeleefff.network;

import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.yeleefff.Moonphase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.yeleefff.config.ConfigState;

public record SyncConfigS2CPayload(ConfigState configState) implements CustomPacketPayload{
    public static final CustomPacketPayload.Type<SyncConfigS2CPayload> ID = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Moonphase.MOD_ID, "sync_config"));
    public static final StreamCodec<FriendlyByteBuf, SyncConfigS2CPayload> CODEC = StreamCodec.composite(ConfigState.PACKET_CODEC, SyncConfigS2CPayload::configState, SyncConfigS2CPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
