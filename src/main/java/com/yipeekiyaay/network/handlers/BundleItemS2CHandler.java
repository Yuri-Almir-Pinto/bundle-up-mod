package com.yipeekiyaay.network.handlers;

import com.yipeekiyaay.helper.BundleHelper;
import com.yipeekiyaay.network.payloads.BundleItemS2CPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.sound.SoundEvents;

public class BundleItemS2CHandler {
    public static void handle(BundleItemS2CPayload payload, ClientPlayNetworking.Context context) {
        var bundled = BundleHelper.bundleItemInSlot(context.player());

        if (!bundled) return;

        context.player().playSound(
                SoundEvents.ITEM_BUNDLE_INSERT,
                0.8F,
                0.8F + context.player().getWorld().getRandom().nextFloat() * 0.4F);
    }
}
