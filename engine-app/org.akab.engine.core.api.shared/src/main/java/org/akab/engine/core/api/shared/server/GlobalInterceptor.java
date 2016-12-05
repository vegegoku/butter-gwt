package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.server.RequestInterceptor;
import org.akab.engine.core.api.shared.server.ServerEntryPointContext;

public interface GlobalInterceptor<E extends ServerEntryPointContext> extends RequestInterceptor<ServerRequest, E> {
}
