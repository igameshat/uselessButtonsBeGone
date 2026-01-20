package com.swaphat.uselessButtonsBeGone.mixin;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.BeaconScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

;

@Mixin(BeaconScreen.class)
public class RemoveBeaconScreenButtonsMixin {

    // Remove the X button
    @Inject(method = "addBeaconButton", at = @At("HEAD"), cancellable = true)
    private <T extends AbstractWidget> void addBeaconButtonMethodMixin(T beaconButton, CallbackInfo ci) {
        if (beaconButton.getClass().getName().contains("BeaconCancelButton") && com.swaphat.uselessButtonsBeGone.ConfigManager.getConfig().isRemoveBeaconScreenButtonX()) {
            ci.cancel();
        }
    }
}
