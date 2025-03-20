package com.yipeekiyaay.network;
import com.yipeekiyaay.network.handlers.BundleItemC2SHandler;
import com.yipeekiyaay.network.handlers.BundleItemS2CHandler;
import com.yipeekiyaay.network.payloads.BundleItemC2SPayload;
import com.yipeekiyaay.network.payloads.BundleItemS2CPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class BundleUpNetworking {

    public static void registerPackets() {
        PayloadTypeRegistry.playC2S().register(BundleItemC2SPayload.ID, BundleItemC2SPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(BundleItemS2CPayload.ID, BundleItemS2CPayload.CODEC);
    }

    public static void registerC2SHandlers() {
        ServerPlayNetworking.registerGlobalReceiver(BundleItemC2SPayload.ID, BundleItemC2SHandler::handle);
    }

    public static void registerS2CHandlers() {
        ClientPlayNetworking.registerGlobalReceiver(BundleItemS2CPayload.ID, BundleItemS2CHandler::handle);
    }
}
