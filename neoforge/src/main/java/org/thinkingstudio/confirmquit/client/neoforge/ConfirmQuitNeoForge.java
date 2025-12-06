package org.thinkingstudio.confirmquit.client.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.thinkingstudio.confirmquit.client.ConfirmQuit;
import org.thinkingstudio.confirmquit.client.config.ConfigHelper;

@Mod(value = ConfirmQuit.MOD_ID, dist = Dist.CLIENT)
public final class ConfirmQuitNeoForge {
    public ConfirmQuitNeoForge(ModContainer modContainer) {
        if (FMLLoader.getDist().isClient()) {
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, (client, screen) -> ConfigHelper.getConfigScreen(screen));
            ConfirmQuit.onInitClient();
        }
    }
}
