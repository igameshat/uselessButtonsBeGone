package com.swaphat.uselessButtonsBeGone;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {

    private final Screen parent;
    private final ConfigManager.ConfigVeriableStorage config;

    public ConfigScreen(Screen parent) {
        super(Component.literal("Useless Buttons Be Gone - Configuration"));
        this.parent = parent;
        this.config = ConfigManager.getConfig();
    }

    @Override
    protected void init() {
        int buttonWidth = 310;
        int buttonHeight = 20;
        int spacing = 24;
        int startX = (this.width - buttonWidth) / 2;
        int currentY = 40;

        int textWidth = this.font.width(this.title);
        this.addRenderableWidget(new StringWidget((this.width - textWidth) / 2, 15, textWidth, 9, this.title, this.font));

        // Config Toggle Buttons
        addToggleButton("Options: Telemetry Button", config::isRemoveOptionsScreenButtonTelemetry, config::setRemoveOptionsScreenButtonTelemetry, startX, currentY, buttonWidth, buttonHeight);
        currentY += spacing;

        addToggleButton("Options: Credits & Attributions Button", config::isRemoveOptionsScreenButtonCreditsAndAttributions, config::setRemoveOptionsScreenButtonCreditsAndAttributions, startX, currentY, buttonWidth, buttonHeight);
        currentY += spacing;

        addToggleButton("Pause: Give Feedback Button", config::isRemoveEscScreenButtonGiveFeedBack, config::setRemoveEscScreenButtonGiveFeedBack, startX, currentY, buttonWidth, buttonHeight);
        currentY += spacing;

        addToggleButton("Pause: Report Bugs Button", config::isRemoveEscScreenButtonReportBugs, config::setRemoveEscScreenButtonReportBugs, startX, currentY, buttonWidth, buttonHeight);
        currentY += spacing;

        addToggleButton("Beacon: Cancel (X) Button", config::isRemoveBeaconScreenButtonX, config::setRemoveBeaconScreenButtonX, startX, currentY, buttonWidth, buttonHeight);

        // Exit/Done button at the bottom
        this.addRenderableWidget(
                Button.builder(CommonComponents.GUI_DONE, button -> this.minecraft.setScreenAndShow(parent))
                        .bounds((this.width - 200) / 2, this.height - 30, 200, 20)
                        .build()
        );
    }

    private void addToggleButton(String label, java.util.function.BooleanSupplier getter, java.util.function.Consumer<Boolean> setter, int x, int y, int width, int height) {
        this.addRenderableWidget(
                CycleButton.onOffBuilder(getter.getAsBoolean())
                        .create(x, y, width, height, Component.literal(label), (button, value) -> {
                            setter.accept(value);
                            ConfigManager.saveConfig();
                        })
        );
    }
}