package com.swaphat.uselessButtonsBeGone;

public class modMenuSupport {
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
}
