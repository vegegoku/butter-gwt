package org.akab.engine.core.api.client.events;

public interface EventsBus<T>{

    interface RequestEvent<T>{
        T asEvent();
    }

    void publishEvent(RequestEvent<T> event);
}
