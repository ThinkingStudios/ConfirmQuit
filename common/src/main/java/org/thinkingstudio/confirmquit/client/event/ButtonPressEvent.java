package org.thinkingstudio.confirmquit.client.event;

import org.thinkingstudio.confirmquit.client.event.base.api.event.Event;
import org.thinkingstudio.confirmquit.client.event.base.api.event.EventFactory;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.thinkingstudio.confirmquit.client.event.base.api.event.EventResult;

public class ButtonPressEvent {
    public static final Event<ButtonPress> BUTTON_PRESS = EventFactory.createArrayBacked(ButtonPress.class,
            (callbacks) -> (button) -> {
                for (ButtonPress callback : callbacks) {
                    if (callback.onPress(button) == EventResult.CANCEL) {
                        return EventResult.CANCEL;
                    }
                }
                return EventResult.PASS;
            });

    @FunctionalInterface
    public interface ButtonPress {
        EventResult onPress(ButtonWidget widget);
    }
}
