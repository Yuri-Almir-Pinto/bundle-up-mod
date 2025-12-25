package com.example.bundle_up.network;

import com.example.bundle_up.Constants;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record BundleC2SPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<BundleC2SPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "bundle-c2s"));

    public static final StreamCodec<ByteBuf, BundleC2SPayload> STREAM_CODEC = StreamCodec.unit(new BundleC2SPayload());

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
