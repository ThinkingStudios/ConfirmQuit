package org.thinkingstudio.confirmquit.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import org.thinkingstudio.confirmquit.client.ConfirmQuit;

public final class ConfirmQuitFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ConfirmQuit.onInitClient();
    }
}
