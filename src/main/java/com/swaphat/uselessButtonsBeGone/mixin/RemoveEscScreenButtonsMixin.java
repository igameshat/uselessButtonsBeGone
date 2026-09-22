package com.swaphat.uselessButtonsBeGone.mixin;

import com.swaphat.uselessButtonsBeGone.ConfigManager;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.PauseScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(PauseScreen.class)
public abstract class RemoveEscScreenButtonsMixin {

    @Redirect(
            method = "createPauseMenu",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/layouts/LinearLayout;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;",
                    ordinal = 0
            )
    )
    private LayoutElement handleReportBugsButton(LinearLayout instance, LayoutElement child) {
        if (ConfigManager.getConfig().isRemoveEscScreenButtonReportBugs()) {
            return child;
        }
        return instance.addChild(child);
    }

    @Redirect(
            method = "createPauseMenu",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/layouts/LinearLayout;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;",
                    ordinal = 1
            )
    )
    private LayoutElement handleFeedbackButton(LinearLayout instance, LayoutElement child) {
        if (ConfigManager.getConfig().isRemoveEscScreenButtonGiveFeedBack()) {
            return child;
        }
        return instance.addChild(child);
    }
}