package org.akab.engine.core.annotation.processor.server.multiMix;

import org.akab.engine.core.annotation.processor.server.multiMix.FirstRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;

@Handler
public class FirstHandler implements RequestHandler<FirstRequest, ServerResponse>{
    @Override
    public ServerResponse handleRequest(FirstRequest request) {
        return null;
    }

}
