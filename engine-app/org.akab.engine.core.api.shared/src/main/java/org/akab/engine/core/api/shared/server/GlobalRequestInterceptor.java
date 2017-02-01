package org.akab.engine.core.api.shared.server;

import org.akab.engine.core.api.shared.request.ServerRequest;

public interface GlobalRequestInterceptor<E extends ServerEntryPointContext> {
    void intercept(ServerRequest request, E context);
}
