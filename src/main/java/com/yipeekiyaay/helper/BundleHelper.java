package com.yipeekiyaay.helper;

import com.yipeekiyaay.BundleUp;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BundleHelper {
    public static boolean bundleItemInSlot(PlayerEntity player) {
        var screenHandler = player.currentScreenHandler;
        var stack = screenHandler.getCursorStack();

        if (!canBeBundled(stack)) return false;
        if (stack.getMaxCount() == 1) return false;

        var bundleStack = new ItemStack(Items.BUNDLE, 1);

        var bundleContents = bundleStack.get(DataComponentTypes.BUNDLE_CONTENTS);

        assert bundleContents != null;

        BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(bundleContents);
        builder.add(stack);
        bundleStack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());

        bundleStack.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, comp -> comp.apply(currentNbt -> {
            currentNbt.putBoolean(BundleUp.FRAGILE_BUNDLE_NBT, true);
        }));

        screenHandler.setCursorStack(bundleStack);

        return true;
    }

    public static boolean canBeBundled(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem().canBeNested();
    }

    public static boolean shouldSendToClient(PlayerEntity player) {
        var currentScreen = player.currentScreenHandler;

        return player.isInCreativeMode()
                && currentScreen.getCursorStack().isEmpty();
    }
}
