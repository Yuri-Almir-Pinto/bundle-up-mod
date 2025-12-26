package com.example.bundle_up.mixin;

import com.example.bundle_up.domain.Utilities;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleItem.class)
public class MixinBundleItem {
    @Inject(method = "overrideStackedOnOther", at = @At("RETURN"))
    private void onOverrideStackedOnOther(ItemStack bundleStack, Slot slot, ClickAction action, Player player, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            Utilities.emptyIfSpecialBundle(bundleStack);
        }
    }

    @Inject(method = "overrideOtherStackedOnMe", at = @At("RETURN"))
    private void onOverrideOtherStackedOnMe(ItemStack bundleStack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            Utilities.emptyIfSpecialBundle(bundleStack);
        }
    }

    @Inject(method = "use", at = @At("RETURN"))
    private void onUse(Level level, Player player, InteractionHand usedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        InteractionResultHolder<ItemStack> result = cir.getReturnValue();

        if (result.getResult().consumesAction()) {
            Utilities.emptyIfSpecialBundle(result.getObject());
        }
    }

    @Inject(method = "getWeight", at = @At("RETURN"), cancellable = true)
    private static void getContentWeight(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (stack.isEmpty() || stack.getItem() == Items.BUNDLE) return;
        if (stack.getMaxStackSize() >= 8) return;

        cir.setReturnValue(8);
    }
}
