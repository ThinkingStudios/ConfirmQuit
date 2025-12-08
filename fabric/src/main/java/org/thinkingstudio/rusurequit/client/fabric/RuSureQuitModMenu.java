package org.thinkingstudio.rusurequit.client.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import org.thinkingstudio.rusurequit.client.config.ConfigHelper;

public class RuSureQuitModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ConfigHelper::getConfigScreen;
    }
}
