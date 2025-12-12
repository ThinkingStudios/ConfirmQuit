package org.thinkingstudio.rusurequit.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import org.thinkingstudio.rusurequit.client.RuSureQuit;

public final class RuSureQuitFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RuSureQuit.onInitClient();
    }
}
