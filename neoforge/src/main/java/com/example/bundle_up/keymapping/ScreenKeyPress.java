package com.example.bundle_up.keymapping;

import com.example.bundle_up.Constants;
import com.example.bundle_up.domain.InputHandler;
import com.example.bundle_up.network.BundleC2SPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class ScreenKeyPress {
    @SubscribeEvent
    public static void onScreenKeyPressed(ScreenEvent.KeyPressed.Pre event) {
        var keyPressedEvent = new InputHandler.KeyPressedEvent(event.getScreen(), event.getKeyCode());
        boolean sendPacket = InputHandler.handleBundleUpKey(keyPressedEvent);

        if (!sendPacket) return;

        PacketDistributor.sendToServer(new BundleC2SPayload());
    }
}
