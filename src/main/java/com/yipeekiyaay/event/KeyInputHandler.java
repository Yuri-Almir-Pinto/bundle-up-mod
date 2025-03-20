package com.yipeekiyaay.event;

import com.yipeekiyaay.network.payloads.BundleItemC2SPayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_BUNDLE_UP = "key.category.bundle-up.bundle-up";
    public static final String KEY_BUNDLE_ITEM_INVENTORY = "key.bundle-up.bundle-item-inventory";

    public static KeyBinding bundleItemInventoryKey;


    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            var actualKey = KeyBindingHelper.getBoundKeyOf(bundleItemInventoryKey);

            var isScreenWithSlots = client.currentScreen instanceof HandledScreen<?>;

            if (!bundleItemInventoryKey.isPressed()
                    && InputUtil.isKeyPressed(client.getWindow().getHandle(), actualKey.getCode())) {

                if (client.currentScreen != null && !isScreenWithSlots) return;

                var bundleItemPayload = new BundleItemC2SPayload();

                ClientPlayNetworking.send(bundleItemPayload);
            }
        });
    }

    public static void register() {
        bundleItemInventoryKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_BUNDLE_ITEM_INVENTORY,
                GLFW.GLFW_KEY_G,
                KEY_CATEGORY_BUNDLE_UP
        ));

        registerKeyInputs();
    }
}
