package com.swaphat.uselessButtonsBeGone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {

    // Check if ModMenu is loaded
    public static final boolean MODMENU_LOADED;

    static {
        boolean loaded;
        try {
            Class.forName("com.terraformersmc.modmenu.ModMenu");
            loaded = true;
        } catch (ClassNotFoundException e) {
            loaded = false;
        }
        MODMENU_LOADED = loaded;
    }

    public static boolean isModMenuLoaded() {
        return MODMENU_LOADED;
    }

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
        private boolean removeOptionsScreenButtonTelemetry = true;
        private boolean removeOptionsScreenButtonCreditsAndAttributions = true;
        private boolean removeEscScreenButtonGiveFeedBack = true;
        private boolean removeEscScreenButtonReportBugs = true;
        private boolean removeBeaconScreenButtonX = true;

        public boolean isRemoveOptionsScreenButtonTelemetry() { return removeOptionsScreenButtonTelemetry; }
        public void setRemoveOptionsScreenButtonTelemetry(boolean val) { removeOptionsScreenButtonTelemetry = val; }

        public boolean isRemoveOptionsScreenButtonCreditsAndAttributions() { return removeOptionsScreenButtonCreditsAndAttributions; }
        public void setRemoveOptionsScreenButtonCreditsAndAttributions(boolean val) { removeOptionsScreenButtonCreditsAndAttributions = val; }

        public boolean isRemoveEscScreenButtonGiveFeedBack() { return removeEscScreenButtonGiveFeedBack; }
        public void setRemoveEscScreenButtonGiveFeedBack(boolean val) { removeEscScreenButtonGiveFeedBack = val; }

        public boolean isRemoveEscScreenButtonReportBugs() { return removeEscScreenButtonReportBugs; }
        public void setRemoveEscScreenButtonReportBugs(boolean val) { removeEscScreenButtonReportBugs = val; }

        public boolean isRemoveBeaconScreenButtonX() { return removeBeaconScreenButtonX; }
        public void setRemoveBeaconScreenButtonX(boolean val) { removeBeaconScreenButtonX = val; }
    }
}
