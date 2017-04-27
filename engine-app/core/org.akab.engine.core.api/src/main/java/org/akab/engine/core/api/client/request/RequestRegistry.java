package org.akab.engine.core.api.client.request;

@FunctionalInterface
public interface RequestRegistry{
    void registerRequest(String requestName, String presenterName);
}
