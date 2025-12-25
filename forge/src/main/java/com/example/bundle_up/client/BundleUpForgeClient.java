package com.example.bundle_up.client;

import com.example.bundle_up.Constants;
import com.example.bundle_up.keymapping.KeyMapCommon;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BundleUpForgeClient {

    public static final KeyMapping BUNDLE_UP_KEY = new KeyMapping(
            KeyMapCommon.BUNDLE_UP.name(),
            KeyMapCommon.BUNDLE_UP.key(),
            KeyMapCommon.BUNDLE_UP.category()
    );

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(BUNDLE_UP_KEY);
    }
}