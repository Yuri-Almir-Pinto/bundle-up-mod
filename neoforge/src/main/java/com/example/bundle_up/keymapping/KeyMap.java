package com.example.bundle_up.keymapping;

import com.example.bundle_up.Constants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class KeyMap {
    public static final Lazy<KeyMapping> BUNDLE_UP = Lazy.of(() -> toKeyMapping(KeyMapCommon.BUNDLE_UP));

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(BUNDLE_UP.get());
    }

    public static KeyMapping toKeyMapping(KeyMapCommon.CommonKey commonKey) {
        return new KeyMapping(commonKey.name(), commonKey.key(), commonKey.category());
    }
}
