package org.akab.engine.core.api.client.request;

public interface RequestState<C extends RequestStateContext> {
    void execute(C request);
}
