package org.akab.engine.core.annotation.processor.server.singleHandler;

import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;

@Handler
public class HandlerImplementingRequestHandlerInterface implements RequestHandler<ServerRequest, ServerResponse>{
    @Override
    public ServerResponse handleRequest(ServerRequest arguments) {
        return null;
    }

}
