package org.akab.engine.core.api.client.History;

public interface PathToRequestMappersRepository {

    void registerMapper(String path, RequestFromPath mapper);
    RequestFromPath getMapper(String path);
}
