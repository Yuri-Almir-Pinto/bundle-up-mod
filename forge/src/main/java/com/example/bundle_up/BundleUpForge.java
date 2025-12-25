package com.example.bundle_up;

import com.example.bundle_up.networking.ForgePacketHandler;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class BundleUpForge {

    public BundleUpForge() {
        BundleUpCommon.init();
        ForgePacketHandler.init();
    }
}