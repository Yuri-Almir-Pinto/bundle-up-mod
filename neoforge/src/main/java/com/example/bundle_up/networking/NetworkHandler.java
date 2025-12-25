package com.example.bundle_up.networking;

import com.example.bundle_up.Constants;
import com.example.bundle_up.domain.BundleItemHandler;
import com.example.bundle_up.network.BundleC2SPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkHandler {
    @SubscribeEvent // on the mod event bus
    public static void register(final RegisterPayloadHandlersEvent event) {
        // Sets the current network version
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.commonToServer(
                BundleC2SPayload.TYPE,
                BundleC2SPayload.STREAM_CODEC,
                NetworkHandler::handleServer
        );
    }

    private static void handleServer(BundleC2SPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();

            var bundleUpContext = new BundleItemHandler.BundleUpContext(player);

            BundleItemHandler.HandleBundleUpPacket(bundleUpContext);
        });
    }
}
