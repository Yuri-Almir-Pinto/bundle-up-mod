package com.example.bundle_up.domain;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Utilities {

    // NBT Keys
    private static final String TAG_ITEMS = "Items";
    private static final String TAG_MARKER = "bundle_up_marker";

    public static boolean markSpecialBundle(ItemStack stack) {
        if (stack.getItem() != Items.BUNDLE) return false;

        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean(TAG_MARKER, true);

        return true;
    }

    public static boolean isSpecialBundle(ItemStack stack) {
        if (stack.getItem() != Items.BUNDLE) return false;
        if (!stack.hasTag()) return false;

        return stack.getTag() != null && stack.getTag().contains(TAG_MARKER);
    }

    public static boolean isBundleEmpty(ItemStack stack) {
        if (stack.getItem() != Items.BUNDLE) throw new RuntimeException("Tried to check if a non-bundle item was empty!");
        if (!stack.hasTag()) return true;

        CompoundTag tag = stack.getTag();

        if (tag == null) return true;
        if (!tag.contains(TAG_ITEMS)) return true;

        return tag.getList(TAG_ITEMS, Tag.TAG_COMPOUND).isEmpty();
    }

    public static void emptyIfSpecialBundle(ItemStack bundle) {
        if (!Utilities.isBundleEmpty(bundle) || !Utilities.isSpecialBundle(bundle)) return;

        bundle.setCount(0);
    }

    public static ItemStack bundleStack(ItemStack stack) {
        ItemStack bundle = new ItemStack(Items.BUNDLE);

        if (!stack.getItem().canFitInsideContainerItems()) {
            return bundle;
        }

        CompoundTag bundleTag = bundle.getOrCreateTag();

        ListTag itemsList = new ListTag();

        itemsList.add(stack.save(new CompoundTag()));

        bundleTag.put(TAG_ITEMS, itemsList);

        stack.setCount(0);

        return bundle;
    }
}