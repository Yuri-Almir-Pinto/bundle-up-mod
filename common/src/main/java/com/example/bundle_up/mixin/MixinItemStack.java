package com.example.bundle_up.mixin;

import com.example.bundle_up.domain.Utilities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void onInventoryTick(Level level, Entity entity, int slotId, boolean isSelected, CallbackInfo ci) {
        ItemStack self = (ItemStack) (Object) this;

        if (self.getItem() != Items.BUNDLE) return;

        Utilities.emptyIfSpecialBundle(self);
    }
}