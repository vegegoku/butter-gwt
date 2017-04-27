package org.akab.engine.core.api.client.request;


@FunctionalInterface
public interface RequestRouter<R extends Request> {
    void routeRequest(R request);
}
