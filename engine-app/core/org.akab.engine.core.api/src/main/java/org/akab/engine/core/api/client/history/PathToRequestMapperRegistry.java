package org.akab.engine.core.api.client.history;

@FunctionalInterface
public interface PathToRequestMapperRegistry {

    void registerMapper(String path, RequestFromPath mapper);
}
