package org.thinkingstudio.rusurequit.client.neoforge;

import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import org.thinkingstudio.rusurequit.client.RuSureQuit;
import org.thinkingstudio.rusurequit.client.config.ConfigHelper;

@Mod(RuSureQuit.MOD_ID)
public final class RuSureQuitNeoForge {
    public RuSureQuitNeoForge() {
        if (FMLLoader.getDist().isClient()) {
            ModContainer modContainer = ModList.get().getModContainerById(RuSureQuit.MOD_ID).orElseThrow();
            modContainer.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (remoteVersion, isFromServer) -> true));
            modContainer.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, screen) -> ConfigHelper.getConfigScreen(screen)));
            RuSureQuit.onInitClient();
        }
    }
}
