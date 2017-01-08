package com.progressoft.security.authentication.server.handlers;

import com.progressoft.security.authentication.shared.request.FindRootChainRequest;
import com.progressoft.security.authentication.shared.response.FindRootChainResponse;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Handler
public class FindRootAuthenticationChainHandler implements RequestHandler<FindRootChainRequest, FindRootChainResponse> {

    public static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(FindRootAuthenticationChainHandler.class);

    @Override
    public FindRootChainResponse handleRequest(FindRootChainRequest arguments) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream("authentication-config.properties")) {
            props.load(resourceStream);
        } catch (IOException e) {
            LOGGER.error("Could not find root authentication chain", e);
        }

        return new FindRootChainResponse(props.getProperty("root.chain"));
    }
}
