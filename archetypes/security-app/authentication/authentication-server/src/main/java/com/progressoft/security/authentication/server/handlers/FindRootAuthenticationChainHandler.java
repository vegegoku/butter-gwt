package com.progressoft.security.authentication.server.handlers;

import com.progressoft.security.authentication.server.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.request.FindRootChainRequest;
import com.progressoft.security.authentication.shared.response.FindRootChainResponse;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

@Handler
public class FindRootAuthenticationChainHandler implements RequestHandler<FindRootChainRequest, FindRootChainResponse> {

    public static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(FindRootAuthenticationChainHandler.class);

    @Override
    public FindRootChainResponse handleRequest(FindRootChainRequest arguments) {
        return new FindRootChainResponse(rootAuthenticationChain());
    }

    private String rootAuthenticationChain() {
        return ServerAuthenticationContext.configuration().rootAuthenticationChain();
    }
}
