package com.yipeekiyaay.network.handlers;

import com.yipeekiyaay.helper.BundleHelper;
import com.yipeekiyaay.network.payloads.BundleItemC2SPayload;
import com.yipeekiyaay.network.payloads.BundleItemS2CPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class BundleItemC2SHandler {
    public static void handle(BundleItemC2SPayload payload, ServerPlayNetworking.Context context) {
        var player = context.player();

        if (BundleHelper.shouldSendToClient(player)) {
            var responseSender = context.responseSender();
            responseSender.sendPacket(new BundleItemS2CPayload());
            return;
        }

        var bundled = BundleHelper.bundleItemInSlot(player);

        if (!bundled) return;

        player.playSoundToPlayer(
                SoundEvents.ITEM_BUNDLE_INSERT,
                SoundCategory.PLAYERS,
                0.8F,
                0.8F + player.getWorld().getRandom().nextFloat() * 0.4F);
    }
}
