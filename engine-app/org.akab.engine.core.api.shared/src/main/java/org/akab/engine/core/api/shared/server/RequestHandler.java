package org.akab.engine.core.api.shared.server;

import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.request.ServerRequest;

@FunctionalInterface
public interface RequestHandler<R extends ServerRequest, S extends ServerResponse> {
    S handleRequest(R arguments);
}
