package org.thinkingstudio.confirmquit.client.event;

import org.thinkingstudio.confirmquit.client.event.base.api.event.Event;
import org.thinkingstudio.confirmquit.client.event.base.api.event.EventFactory;
import org.thinkingstudio.confirmquit.client.event.base.api.event.EventResult;

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
