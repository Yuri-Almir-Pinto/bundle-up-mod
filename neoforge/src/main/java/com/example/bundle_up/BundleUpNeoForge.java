package com.example.bundle_up;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class BundleUpNeoForge {

    public BundleUpNeoForge(IEventBus eventBus) {
        BundleUpCommon.init();
    }
}