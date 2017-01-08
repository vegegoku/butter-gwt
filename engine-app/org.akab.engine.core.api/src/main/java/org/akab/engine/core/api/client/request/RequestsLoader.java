package org.akab.engine.core.api.client.request;

@FunctionalInterface
public interface RequestsLoader {
    void load(RequestsRepository repository);
}
