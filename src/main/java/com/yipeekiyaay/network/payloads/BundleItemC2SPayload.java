package com.yipeekiyaay.network.payloads;

import com.yipeekiyaay.BundleUp;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record BundleItemC2SPayload() implements CustomPayload {
    public static final Identifier BUNDLE_ITEM_PAYLOAD_ID = Identifier.of(BundleUp.MOD_ID, "bundle_item");
    public static final CustomPayload.Id<BundleItemC2SPayload> ID = new CustomPayload.Id<>(BUNDLE_ITEM_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, BundleItemC2SPayload> CODEC =
            PacketCodec.unit(new BundleItemC2SPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
