package com.progressoft.security.authentication.server.handlers;

import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.request.CompleteAuthenticationRequest;
import com.progressoft.security.authentication.shared.response.CompleteAuthenticationResponse;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;

@Handler
public class CompleteAuthenticationOnServerHandler implements RequestHandler<CompleteAuthenticationRequest, CompleteAuthenticationResponse> {
    @Override
    public CompleteAuthenticationResponse handleRequest(CompleteAuthenticationRequest request) {
        return new CompleteAuthenticationResponse(!ServerAuthenticationContext.holder().isEmpty());
    }
}
