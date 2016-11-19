package org.akab.engine.core.api.client.History;


public interface PathToRequestMapperRegistry {

    void registerMapper(String path, RequestFromPath mapper);
}
