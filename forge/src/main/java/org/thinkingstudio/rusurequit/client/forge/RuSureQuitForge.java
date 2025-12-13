package org.thinkingstudio.rusurequit.client.forge;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import org.thinkingstudio.rusurequit.client.RuSureQuit;
import org.thinkingstudio.rusurequit.client.config.ConfigHelper;

@Mod(RuSureQuit.MOD_ID)
public final class RuSureQuitForge {
    public RuSureQuitForge() {
        if (FMLLoader.getDist().isClient()) {
            ModContainer modContainer = ModList.get().getModContainerById(RuSureQuit.MOD_ID).orElseThrow();
            modContainer.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(IExtensionPoint.DisplayTest.IGNORESERVERONLY, (remoteVersion, isFromServer) -> true));
            modContainer.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, screen) -> ConfigHelper.getConfigScreen(screen)));
            RuSureQuit.onInitClient();
        }
    }
}
