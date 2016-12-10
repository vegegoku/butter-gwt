package org.akab.engine.core.api.shared.server;

import org.akab.engine.core.api.shared.request.ServerRequest;

public interface GlobalInterceptor<E extends ServerEntryPointContext> extends RequestInterceptor<ServerRequest, E> {
}
