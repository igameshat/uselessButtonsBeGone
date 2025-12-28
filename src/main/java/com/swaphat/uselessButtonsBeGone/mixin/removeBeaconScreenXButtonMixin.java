package com.swaphat.uselessButtonsBeGone.mixin;

import net.minecraft.client.gui.components.AbstractWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.screens.inventory.BeaconScreen;

@Mixin(BeaconScreen.class)
public class removeBeaconScreenXButtonMixin {

    @Inject(method = "addBeaconButton", at = @At("HEAD"), cancellable = true)
    private <T extends AbstractWidget> void addBeaconButtonMethodMixin(T beaconButton, CallbackInfo ci) {
        if (beaconButton.getClass().getName().contains("BeaconCancelButton")) {
            ci.cancel();
        }
    }
}
