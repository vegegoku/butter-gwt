package com.progressoft.security.app.layout.server.handlers;

import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.app.layout.shared.response.LayoutResponse;
import com.progressoft.security.app.layout.shared.request.LayoutRequest;

import java.util.logging.Logger;

@Handler
public class LayoutHandler implements RequestHandler<LayoutRequest, LayoutResponse> {
    private static final Logger LOGGER= Logger.getLogger(LayoutHandler.class.getName());
    @Override
    public LayoutResponse handleRequest(LayoutRequest request) {
        LOGGER.info("message recieved from client : "+request.getMessage());
        return new LayoutResponse("Server message");
    }
}
