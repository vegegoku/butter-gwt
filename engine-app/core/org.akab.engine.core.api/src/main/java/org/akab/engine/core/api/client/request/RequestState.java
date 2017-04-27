package org.akab.engine.core.api.client.request;

@FunctionalInterface
public interface RequestState<C extends RequestStateContext> {
    void execute(C request);
}
