package com.yipeekiyaay.helper;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BundleHelper {
    public static boolean bundleItemInSlot(PlayerEntity player) {
        var screenHandler = player.currentScreenHandler;
        var stack = screenHandler.getCursorStack();

        if (stack.isEmpty()) return false;
        if (!BundleContentsComponent.canBeBundled(stack)) return false;
        if (stack.getMaxCount() == 1) return false;

        var bundleStack = new ItemStack(Items.BUNDLE, 1);

        var bundleContents = bundleStack.get(DataComponentTypes.BUNDLE_CONTENTS);

        assert bundleContents != null;

        BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(bundleContents);
        builder.add(stack);
        bundleStack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());

        screenHandler.setCursorStack(bundleStack);

        return true;
    }

    public static boolean isCreativeScreen(PlayerEntity player) {
        var currentScreen = player.currentScreenHandler;

        return player.isCreative()
                && currentScreen.getCursorStack().isEmpty();
    }
}
