package com.example.bundle_up.mixin;

import com.example.bundle_up.Constants;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleContents.class)
public class MixinBundleContents {
    @Inject(method = "getWeight", at = @At("HEAD"), cancellable = true)
    private static void modifyItemWeight(ItemStack stack, CallbackInfoReturnable<Fraction> cir) {
        if (stack.has(DataComponents.BUNDLE_CONTENTS)) {
            return;
        }

        if (stack.getMaxStackSize() < 8) {
            cir.setReturnValue(Fraction.getFraction(1, 8));
        }
    }
}
