package com.swaphat.uselessButtonsBeGone;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

public class ConfigScreen extends Screen {

    private final Screen parent;
    private final ConfigManager.ConfigVeriableStorage config;

    public ConfigScreen(Screen parent) {
        super(Component.literal("Useless Buttons Be Gone"));
        this.parent = parent;
        config = ConfigManager.getConfig();
    }

    @Override
    protected void init() {
        int padding = height / 5;
        int paddingTotal = 0;

        // Exit button at bottom
        this.addRenderableWidget(
                Button.builder(Component.literal("Exit and go back"), button -> this.minecraft.setScreen(parent))
                        .bounds(0, this.height - 20, this.width, 20)
                        .build()
        );

        // Buttons for toggling config options
        addToggleButton("Remove options screen telemetry button", config::isRemoveOptionsScreenButtonTelemetry, config::setRemoveOptionsScreenButtonTelemetry, paddingTotal);
        paddingTotal += padding;
        addToggleButton("Remove options screen credits and attributions button", config::isRemoveOptionsScreenButtonCreditsAndAttributions, config::setRemoveOptionsScreenButtonCreditsAndAttributions, paddingTotal);
        paddingTotal += padding;
        addToggleButton("Remove esc screen give feedback button", config::isRemoveEscScreenButtonGiveFeedBack, config::setRemoveEscScreenButtonGiveFeedBack, paddingTotal);
        paddingTotal += padding;
        addToggleButton("Remove esc screen report bugs button", config::isRemoveEscScreenButtonReportBugs, config::setRemoveEscScreenButtonReportBugs, paddingTotal);
        paddingTotal += padding;
        addToggleButton("Remove beacon screen X button", config::isRemoveBeaconScreenButtonX, config::setRemoveBeaconScreenButtonX, paddingTotal);
    }

    private void addToggleButton(String label, java.util.function.BooleanSupplier getter, java.util.function.Consumer<Boolean> setter, int y) {
        boolean isOn = getter.getAsBoolean();

        Component statusText = Component.literal(isOn ? "On" : "Off")
                .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(isOn ? 0x00FF00 : 0xFF0000)));

        Component buttonText = Component.empty()
                .append(statusText)
                .append(Component.literal(": " + label));

        this.addRenderableWidget(
                Button.builder(buttonText, button -> {
                            setter.accept(!getter.getAsBoolean());
                            ConfigManager.saveConfig();
                            this.minecraft.setScreen(new ConfigScreen(parent));
                        })
                        .bounds(0, y, this.width, 20)
                        .build()
        );
    }
}
