package org.akab.engine.core.api.shared.server;

import org.akab.engine.core.api.shared.request.Response;
import org.akab.engine.core.api.shared.request.ServerArgs;

public interface RequestHandler<R extends ServerArgs, S extends Response> {
    R handleRequest(ServerArgs arguments);
}
