package org.thinkingstudio.rusurequit.client.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.thinkingstudio.rusurequit.client.RuSureQuit;
import org.thinkingstudio.rusurequit.client.config.ConfigHelper;

@Mod(value = RuSureQuit.MOD_ID, dist = Dist.CLIENT)
public final class RuSureQuitNeoForge {
    public RuSureQuitNeoForge(ModContainer modContainer) {
        if (FMLLoader.getCurrent().getDist().isClient()) {
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, (client, screen) -> ConfigHelper.getConfigScreen(screen));
            RuSureQuit.onInitClient();
        }
    }
}
