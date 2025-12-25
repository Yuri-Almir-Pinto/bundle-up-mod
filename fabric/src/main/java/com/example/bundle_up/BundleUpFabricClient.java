package com.example.bundle_up;

import com.example.bundle_up.domain.InputHandler;
import com.example.bundle_up.keymapping.KeyMapCommon;
import com.example.bundle_up.network.BundleC2SPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

public class BundleUpFabricClient implements ClientModInitializer {
    public static final KeyMapping BUNDLE_UP_KEY = new KeyMapping(
            KeyMapCommon.BUNDLE_UP.name(),
            KeyMapCommon.BUNDLE_UP.key(),
            KeyMapCommon.BUNDLE_UP.category()
    );

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(BUNDLE_UP_KEY);

        ScreenEvents.BEFORE_INIT.register((client, screen, width, height) -> {
            if (!(screen instanceof AbstractContainerScreen)) return;

            ScreenKeyboardEvents.beforeKeyPress(screen).register((_screen, key, scancode, modifiers) -> {
                var keyPressedEvent = new InputHandler.KeyPressedEvent(_screen, key);

                var sendPacket = InputHandler.handleBundleUpKey(keyPressedEvent);

                if (!sendPacket) return;

                ClientPlayNetworking.send(new BundleC2SPayload());
            });
        });
    }
}
