package com.yipeekiyaay;

import com.yipeekiyaay.event.KeyInputHandler;
import com.yipeekiyaay.network.BundleUpNetworking;
import net.fabricmc.api.ClientModInitializer;

public class BundleUpClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();

        BundleUpNetworking.registerS2CHandlers();
    }
}