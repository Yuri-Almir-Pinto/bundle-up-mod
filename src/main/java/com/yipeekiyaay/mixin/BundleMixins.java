package com.yipeekiyaay.mixin;

import com.yipeekiyaay.BundleUp;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BundleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleItem.class)
public class BundleMixins {
	@Inject(at = @At("RETURN"), method = "onStackClicked")
	public void onStackClicked(ItemStack stack,
							   Slot slot,
							   ClickType clickType,
							   PlayerEntity player,
							   CallbackInfoReturnable<ActionResult> cir) {
		voidBundleIfEmpty(stack);
	}

	@Inject(at = @At("RETURN"), method = "onClicked")
	public void onClicked(ItemStack stack,
						  ItemStack otherStack,
						  Slot slot,
						  ClickType clickType,
						  PlayerEntity player,
						  StackReference cursorStackReference,
						  CallbackInfoReturnable<ActionResult> cir) {
		voidBundleIfEmpty(stack);
	}

	@Inject(at = @At("TAIL"), method = "dropContentsOnUse")
	public void dropContentsOnUse(World world,
								  PlayerEntity player,
								  ItemStack stack,
								  CallbackInfo cir) {
		voidBundleIfEmpty(stack);
	}

	@Unique
	private void voidBundleIfEmpty(ItemStack stack) {
		var bundleContents = stack.get(DataComponentTypes.BUNDLE_CONTENTS);

		if (bundleContents == null) return;

		stack.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, comp -> comp.apply(currentNbt -> {
			var isFragile = currentNbt.getBoolean(BundleUp.FRAGILE_BUNDLE_NBT);

			if (isFragile && bundleContents.isEmpty()) {
				stack.setCount(0);
			}
		}));
	}
}