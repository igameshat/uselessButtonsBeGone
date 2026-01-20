package com.swaphat.uselessButtonsBeGone.mixin;

import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.swaphat.uselessButtonsBeGone.modMenuSupport.isModMenuLoaded;

@Mixin(PauseScreen.class)
public abstract class RemoveEscScreenButtonsMixin extends Screen {

    protected RemoveEscScreenButtonsMixin(Component title) {
        super(title);
    }

    @Inject(method = "addFeedbackSubscreenAndCustomDialogButtons", at = @At("HEAD"), cancellable = true)
    private void OverrideAddFeedbackSubscreenAndCustomDialogButtons(CallbackInfo ci) {
        if(!isModMenuLoaded()) ci.cancel();
    }
    @Inject(method = "addFeedbackButtons", at = @At("HEAD"), cancellable = true)
    private static void OverrideAddFeedbackButtons(CallbackInfo ci) {
        if(!isModMenuLoaded()) ci.cancel();
    }
}
