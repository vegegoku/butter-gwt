package org.akab.rafa.hello.world.server;

import org.akab.engine.core.api.shared.server.RequestHandler;
import org.akab.rafa.response.HelloResponse;
import org.akab.rafa.request.HelloWordsArgs;

import java.util.logging.Logger;


public class HelloWorldHandler implements RequestHandler<HelloWordsArgs, HelloResponse> {
    private static final Logger LOGGER= Logger.getLogger(HelloWorldHandler.class.getName());
    @Override
    public HelloResponse handleRequest(HelloWordsArgs request) {
        LOGGER.info("message recieved from client : "+request.getMessage());
        return new HelloResponse("Server message");
    }
}
