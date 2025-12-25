package com.example.bundle_up.domain;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.CustomData;

public class Utilities {
    public static boolean markSpecialBundle(ItemStack stack) {
        if (stack.getItem() != Items.BUNDLE) return false;

        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, existing ->
            existing.update(tag -> tag.putBoolean("bundle_up_marker", true))
        );

        return true;
    }

    public static boolean isSpecialBundle(ItemStack stack) {
        if (stack.getItem() != Items.BUNDLE) return false;

        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        return data != null && data.contains("bundle_up_marker");
    }

    public static boolean isBundleEmpty(ItemStack stack) {
        if (stack.getItem() != Items.BUNDLE) throw new RuntimeException("Tried to check if a non-bundle item was empty!");

        var contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);

        return contents.isEmpty();
    }

    public static ItemStack bundleStack(ItemStack stack) {
        var bundle = Items.BUNDLE.getDefaultInstance();

        BundleContents contents = bundle.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);

        BundleContents.Mutable mutable = new BundleContents.Mutable(contents);

        mutable.tryInsert(stack);

        bundle.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());

        return bundle;
    }
}
