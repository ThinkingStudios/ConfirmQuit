package org.thinkingstudio.rusurequit.client.event;

import org.thinkingstudio.rusurequit.client.event.base.api.Event;
import org.thinkingstudio.rusurequit.client.event.base.api.EventFactory;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.thinkingstudio.rusurequit.client.event.base.api.EventResult;

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
