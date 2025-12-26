package com.swaphat.uselessButtonsBeGone.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.gui.screens.options.OptionsScreen;

import java.util.function.Supplier;


@Mixin(OptionsScreen.class)
public class removeOptionsScreenButtonsMixin {
    final String MOD_ID = "uselessButtonsBeGone";
    final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @WrapOperation(method = "init", at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/screens/options/OptionsScreen;openScreenButton(Lnet/minecraft/network/chat/Component;Ljava/util/function/Supplier;)Lnet/minecraft/client/gui/components/Button;"))
    private Button removeCredits(OptionsScreen instance, Component message, Supplier<Screen> screenToScreen, Operation<Button> original) {
        if (Component.translatable("options.telemetry").toString().equals(message.toString()) || Component.translatable("options.credits_and_attribution").toString().equals(message.toString())) {
            LOGGER.info("button has been removed by "+MOD_ID);
        } else {
            System.out.println(message);
            return original.call(instance, message, screenToScreen);
        }

        return new Button.Builder(Component.empty(), b -> {})
                .bounds(0, 0, 0, 0)
                .build();
    }
}
