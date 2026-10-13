package com.swaphat.uselessButtonsBeGone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static ConfigVeriableStorage config;
    private static File configFile;

    public static void init() {
        File configDir = new File("config");
        if (!configDir.exists()) configDir.mkdirs();
        configFile = new File(configDir, "useless_buttons_config.json");
        loadConfig();
    }

    public static ConfigVeriableStorage getConfig() {
        if (config == null) loadConfig();
        return config;
    }

    public static void loadConfig() {
        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                config = GSON.fromJson(reader, ConfigVeriableStorage.class);
            } catch (IOException e) {
                e.printStackTrace();
                config = new ConfigVeriableStorage();
            }
        } else {
            config = new ConfigVeriableStorage();
            saveConfig();
        }
    }

    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(configFile)) {
            GSON.toJson(config, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Instance-based ConfigVeriableStorage class
    public static class ConfigVeriableStorage {
        private boolean OptionsScreenButtonTelemetry = true;
        private boolean OptionsScreenButtonCreditsAndAttributions = true;
        private boolean EscScreenButtonGiveFeedBack = true;
        private boolean EscScreenButtonReportBugs = true;
        private boolean BeaconScreenButtonX = true;
        private boolean EscScreenButtonFriends = true;
        private boolean EscScreenButtonSocialInteractions = true;

        public boolean isRemoveOptionsScreenButtonTelemetry() { return OptionsScreenButtonTelemetry; }
        public void setRemoveOptionsScreenButtonTelemetry(boolean val) { OptionsScreenButtonTelemetry = val; }

        public boolean isRemoveOptionsScreenButtonCreditsAndAttributions() { return OptionsScreenButtonCreditsAndAttributions; }
        public void setRemoveOptionsScreenButtonCreditsAndAttributions(boolean val) { OptionsScreenButtonCreditsAndAttributions = val; }

        public boolean isRemoveEscScreenButtonGiveFeedBack() { return EscScreenButtonGiveFeedBack; }
        public void setRemoveEscScreenButtonGiveFeedBack(boolean val) { EscScreenButtonGiveFeedBack = val; }

        public boolean isRemoveEscScreenButtonReportBugs() { return EscScreenButtonReportBugs; }
        public void setRemoveEscScreenButtonReportBugs(boolean val) { EscScreenButtonReportBugs = val; }

        public boolean isRemoveBeaconScreenButtonX() { return BeaconScreenButtonX; }
        public void setRemoveBeaconScreenButtonX(boolean val) { BeaconScreenButtonX = val; }

        public boolean isRemoveEscScreenButtonSocialInteractions() {
            return EscScreenButtonSocialInteractions;
        }

        public void setRemoveEscScreenButtonSocialInteractions(boolean EscScreenButtonSocialInteractions) {
            this.EscScreenButtonSocialInteractions = EscScreenButtonSocialInteractions;
        }

        public boolean isRemoveEscScreenButtonFriends() {
            return EscScreenButtonFriends;
        }

        public void setRemoveEscScreenButtonFriends(boolean EscScreenButtonFriends) {
            this.EscScreenButtonFriends = EscScreenButtonFriends;
        }
    }
}
