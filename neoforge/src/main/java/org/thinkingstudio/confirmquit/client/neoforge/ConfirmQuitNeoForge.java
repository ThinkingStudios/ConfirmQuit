package org.thinkingstudio.confirmquit.client.neoforge;

import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import org.thinkingstudio.confirmquit.client.ConfirmQuit;
import org.thinkingstudio.confirmquit.client.config.ConfigHelper;

@Mod(ConfirmQuit.MOD_ID)
public final class ConfirmQuitNeoForge {
    public ConfirmQuitNeoForge(ModContainer modContainer) {
        if (FMLLoader.getDist().isClient()) {
            modContainer.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (remoteVersion, isFromServer) -> true));
            modContainer.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, screen) -> ConfigHelper.getConfigScreen(screen)));
            ConfirmQuit.onInitClient();
        }
    }
}
