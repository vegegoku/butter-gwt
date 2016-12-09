package org.akab.rafa.hello.world.server;

import org.akab.engine.core.api.shared.server.RequestHandler;
import org.akab.rafa.HelloResponse;
import org.akab.rafa.HelloWordsArgs;


public class HelloWorldHandler implements RequestHandler<HelloWordsArgs, HelloResponse> {
    @Override
    public HelloResponse handleRequest(HelloWordsArgs request) {
        System.out.println(request.getMessage());
        return new HelloResponse("Server message");
    }
}
