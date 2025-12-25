package com.example.bundle_up.domain;

import com.example.bundle_up.Constants;
import com.example.bundle_up.keymapping.KeyMapCommon;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.item.Items;

public class InputHandler {
    public static boolean handleBundleUpKey(KeyPressedEvent event) {
        if (event.keyCode() != KeyMapCommon.BUNDLE_UP.key()) return false;
        if (!(event.screen() instanceof AbstractContainerScreen<?> containerScreen)) return false;

        var cursorStack = containerScreen.getMenu().getCarried();

        return !cursorStack.isEmpty() && cursorStack.getItem() != Items.BUNDLE;
    }

    public record KeyPressedEvent(Screen screen, int keyCode) {}
}
