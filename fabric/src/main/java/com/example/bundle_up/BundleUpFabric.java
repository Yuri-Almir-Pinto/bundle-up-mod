package com.example.bundle_up;

import com.example.bundle_up.domain.BundleItemHandler;
import com.example.bundle_up.network.BundleC2SPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class BundleUpFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BundleUpCommon.init();

        ServerPlayNetworking.registerGlobalReceiver(BundleC2SPayload.ID, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                var bundleUpContext = new BundleItemHandler.BundleUpContext(player);
                BundleItemHandler.HandleBundleUpPacket(bundleUpContext);
            });
        });
    }
}
