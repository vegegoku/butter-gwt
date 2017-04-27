package org.akab.engine.core.api.shared.server;

import org.akab.engine.core.api.shared.request.ServerRequest;

@FunctionalInterface
public interface RequestInterceptor<R extends ServerRequest, C extends ServerEntryPointContext> {
    void intercept(R request, C context);
}
