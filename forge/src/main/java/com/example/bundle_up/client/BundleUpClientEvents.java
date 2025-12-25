package com.example.bundle_up.client;

import com.example.bundle_up.Constants;
import com.example.bundle_up.domain.InputHandler;
import com.example.bundle_up.networking.ForgePacketHandler;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BundleUpClientEvents {

    @SubscribeEvent
    public static void onScreenInternalKeyPress(ScreenEvent.KeyPressed.Pre event) {
        if (!(event.getScreen() instanceof AbstractContainerScreen<?> containerScreen)) {
            return;
        }
        if (BundleUpForgeClient.BUNDLE_UP_KEY.matches(event.getKeyCode(), event.getScanCode())) {

            var keyPressedEvent = new InputHandler.KeyPressedEvent(containerScreen, event.getKeyCode());

            boolean shouldSend = InputHandler.handleBundleUpKey(keyPressedEvent);

            if (shouldSend) {
                ForgePacketHandler.INSTANCE.sendToServer(new ForgePacketHandler.BundleC2SPacket());

                event.setCanceled(true);
            }
        }
    }
}