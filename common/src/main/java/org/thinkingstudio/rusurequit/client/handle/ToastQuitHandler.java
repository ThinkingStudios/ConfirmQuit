package org.thinkingstudio.rusurequit.client.handle;

import org.thinkingstudio.rusurequit.client.config.ConfigHelper;
import org.thinkingstudio.rusurequit.client.event.base.api.EventResult;
import org.thinkingstudio.rusurequit.client.toast.QuitToast;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ToastQuitHandler {
    private final Text message;
    private State state = State.INACTIVE;
    private long startTime = 0;

    public ToastQuitHandler(Text message) {
        this.message = message;
    }

    public EventResult trigger() {
        long millis = System.currentTimeMillis();
        if (state == State.ACTIVE) {
            if (startTime + ConfigHelper.getConfig().toastConfirmDisplayTime < millis) {
                state = State.INACTIVE;
            }
        }

        if (state == State.INACTIVE) {
            startTime = millis;
            state = State.ACTIVE;
            MinecraftClient.getInstance().getToastManager().add(
                    new QuitToast(message, ConfigHelper.getConfig().toastConfirmDisplayTime)
            );
            return EventResult.CANCEL;
        }
        if (ConfigHelper.getConfig().toastConfirmDelayTime + startTime < millis && ConfigHelper.getConfig().toastConfirmDisplayTime + startTime > millis) {
            return EventResult.PASS;
        }

        return EventResult.CANCEL;
    }

    private enum State {
        ACTIVE,
        INACTIVE
    }
}
