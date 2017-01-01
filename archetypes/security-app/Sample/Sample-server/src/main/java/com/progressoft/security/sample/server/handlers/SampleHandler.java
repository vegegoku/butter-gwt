package com.progressoft.security.sample.server.handlers;

import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.sample.shared.response.SampleResponse;
import com.progressoft.security.sample.shared.request.SampleRequest;

import java.util.logging.Logger;

@Handler
public class SampleHandler implements RequestHandler<SampleRequest, SampleResponse> {
    private static final Logger LOGGER= Logger.getLogger(SampleHandler.class.getName());
    @Override
    public SampleResponse handleRequest(SampleRequest request) {
        LOGGER.info("message recieved from client : "+request.getMessage());
        return new SampleResponse("Server message");
    }
}
