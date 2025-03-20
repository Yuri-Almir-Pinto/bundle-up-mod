package com.yipeekiyaay.network.payloads;

import com.yipeekiyaay.BundleUp;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record BundleItemS2CPayload() implements CustomPayload {
    public static final Identifier BUNDLE_ITEM_PAYLOAD_ID = Identifier.of(BundleUp.MOD_ID, "bundle_item_creative");
    public static final CustomPayload.Id<BundleItemS2CPayload> ID = new CustomPayload.Id<>(BUNDLE_ITEM_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, BundleItemS2CPayload> CODEC =
            PacketCodec.unit(new BundleItemS2CPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
