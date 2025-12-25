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

    // 2. LISTEN FOR INPUT IN GUI (Runs on Forge Bus)
    @SubscribeEvent
    public static void onScreenInternalKeyPress(ScreenEvent.KeyPressed.Pre event) {
        // Only care if we are in an inventory
        if (!(event.getScreen() instanceof AbstractContainerScreen<?> containerScreen)) {
            return;
        }

        // Check if the pressed key matches our bound key
        // We use matches() because consumeClick() fails in GUIs
        if (BundleUpForgeClient.BUNDLE_UP_KEY.matches(event.getKeyCode(), event.getScanCode())) {

            var keyPressedEvent = new InputHandler.KeyPressedEvent(containerScreen, event.getKeyCode());

            // Run common logic
            boolean shouldSend = InputHandler.handleBundleUpKey(keyPressedEvent);

            if (shouldSend) {
                // Send Packet using Forge Channel
                ForgePacketHandler.INSTANCE.sendToServer(new ForgePacketHandler.BundleC2SPacket());

                // Stop the key from doing anything else (like typing in the search bar)
                event.setCanceled(true);
            }
        }
    }
}