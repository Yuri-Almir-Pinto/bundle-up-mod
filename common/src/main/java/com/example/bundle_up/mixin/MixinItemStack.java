package com.example.bundle_up.mixin;

import com.example.bundle_up.domain.Utilities;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.jetbrains.annotations.Nullable;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {
    @Shadow
    public abstract net.minecraft.world.item.Item getItem();
    @Shadow
    public abstract void setCount(int count);

    @Inject(method = "set", at = @At("RETURN"))
    private void onSetComponent(DataComponentType<?> component, @Nullable Object value, CallbackInfoReturnable<Object> cir) {
        if (this.getItem() != Items.BUNDLE) return;
        if (component != DataComponents.BUNDLE_CONTENTS) return;

        ItemStack self = (ItemStack) (Object) this;

        if (Utilities.isSpecialBundle(self) && Utilities.isBundleEmpty(self)) {
            this.setCount(0);
        }
    }
}