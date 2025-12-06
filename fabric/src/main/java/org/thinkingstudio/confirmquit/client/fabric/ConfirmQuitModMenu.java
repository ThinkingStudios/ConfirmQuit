package org.thinkingstudio.confirmquit.client.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import org.thinkingstudio.confirmquit.client.config.ConfigHelper;

public class ConfirmQuitModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ConfigHelper::getConfigScreen;
    }
}
