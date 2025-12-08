package org.thinkingstudio.rusurequit.client.config;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screen.Screen;

public class ConfigHelper {
    private static ModConfig modConfig;

    public static ModConfig getConfig() {
        if (modConfig == null) {
            modConfig = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        }

        return modConfig;
    }

    public static Screen getConfigScreen(Screen parent) {
        return AutoConfig.getConfigScreen(ModConfig.class, parent).get();
    }
}
