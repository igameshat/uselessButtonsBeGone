package com.swaphat.uselessButtonsBeGone.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.swaphat.uselessButtonsBeGone.ConfigManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class Main {
    @Inject(at = @At("HEAD"), method = "run")
    private void init(CallbackInfo info) {
        // This code is injected into the start of Minecraft.run()V
        ConfigManager.init();

    }

    @Inject(at = @At("TAIL"), method = "run")
    private void end(CallbackInfo info) {
        // This code is injected into the end of Minecraft.run()V
        ConfigManager.saveConfig();
    }

}
