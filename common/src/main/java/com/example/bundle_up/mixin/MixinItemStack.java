package com.example.bundle_up.mixin;

import com.example.bundle_up.domain.Utilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import javax.annotation.Nullable;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {
    @Shadow
    public abstract net.minecraft.world.item.Item getItem();
    @Shadow
    public abstract boolean isEmpty();

    @Inject(method = "setTag", at = @At("RETURN"))
    private void onSetTag(@Nullable CompoundTag tag, CallbackInfo ci) {
        if (this.isEmpty() || this.getItem() != Items.BUNDLE) return;

        ItemStack self = (ItemStack) (Object) this;

        Utilities.emptyIfSpecialBundle(self);
    }
}