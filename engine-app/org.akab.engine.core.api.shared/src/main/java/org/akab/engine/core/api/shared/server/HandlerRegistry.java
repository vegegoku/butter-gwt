package org.akab.engine.core.api.shared.server;

import org.akab.engine.core.api.shared.request.ServerRequest;

public interface HandlerRegistry {

    void registerHandler(String request, RequestHandler handler);

}
