package org.akab.engine.core.api.client.history;

public interface PathToRequestMappersRepository {

    void registerMapper(String path, RequestFromPath mapper);
    RequestFromPath getMapper(String path);
}
