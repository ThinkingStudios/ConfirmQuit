package org.thinkingstudio.confirmquit.client.event;

import org.thinkingstudio.confirmquit.client.event.base.api.Event;
import org.thinkingstudio.confirmquit.client.event.base.api.EventFactory;
import org.thinkingstudio.confirmquit.client.event.base.api.EventResult;

public class ClientScheduleStopEvent {
    public static final Event<ClientScheduleStop> CLIENT_SCHEDULE_STOP = EventFactory.createArrayBacked(ClientScheduleStop.class,
            (callbacks) -> () -> {
                for (ClientScheduleStop callback : callbacks) {
                    if (callback.onScheduleStop() == EventResult.CANCEL) {
                        return EventResult.CANCEL;
                    }
                }
                return EventResult.PASS;
            });

    @FunctionalInterface
    public interface ClientScheduleStop {
        EventResult onScheduleStop();
    }
}
