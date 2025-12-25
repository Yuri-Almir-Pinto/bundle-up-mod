package com.example.bundle_up;

import com.example.bundle_up.domain.BundleItemHandler;
import com.example.bundle_up.network.BundleC2SPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class BundleUpFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BundleUpCommon.init();

        PayloadTypeRegistry.playC2S().register(BundleC2SPayload.TYPE, BundleC2SPayload.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(BundleC2SPayload.TYPE, (payload, context) -> {
            var player = context.player();

            context.server().execute(() -> {
                var bundleUpContext = new BundleItemHandler.BundleUpContext(player);
                BundleItemHandler.HandleBundleUpPacket(bundleUpContext);
            });
        });
    }
}
