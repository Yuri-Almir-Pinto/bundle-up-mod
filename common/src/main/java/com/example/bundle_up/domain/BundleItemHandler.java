package com.example.bundle_up.domain;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public class BundleItemHandler {
    public static void HandleBundleUpPacket(BundleUpContext context) {
        var player = context.player();
        var screen = player.containerMenu;

        if (screen == null) return;

        var cursorStack = screen.getCarried();

        if (cursorStack.isEmpty() || cursorStack.getItem() == Items.BUNDLE) return;

        var bundle = Utilities.bundleStack(cursorStack);

        Utilities.markSpecialBundle(bundle);

        screen.setCarried(bundle);

        cursorStack.setCount(0);

        context.player().level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.BUNDLE_INSERT,
                SoundSource.PLAYERS,
                1.0f,
                1.0f
        );
    }

    public record BundleUpContext(Player player) {}
}
