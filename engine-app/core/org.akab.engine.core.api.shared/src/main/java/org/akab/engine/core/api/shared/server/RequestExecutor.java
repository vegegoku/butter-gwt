package org.akab.engine.core.api.shared.server;

import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.request.ServerRequest;

@FunctionalInterface
public interface RequestExecutor {
    ServerResponse executeRequest(ServerRequest arguments, ServerEntryPointContext context);
}
