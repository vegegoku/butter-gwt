package org.akab.engine.core.annotation.processor.server.multiMix;

import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;

@Handler
public class SecondHandler implements RequestHandler<SecondRequest, ServerResponse>{
    @Override
    public ServerResponse handleRequest(SecondRequest request) {
        return null;
    }

}
