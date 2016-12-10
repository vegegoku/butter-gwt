package org.akab.engine.core.server;


import org.akab.engine.core.api.shared.server.HandlersRepository;
import org.akab.engine.core.api.shared.server.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class InMemoryHandlersRepository implements HandlersRepository {

    private final Map<String , RequestHandler> handlers=new HashMap<>();

    @Override
    public void registerHandler(String request, RequestHandler handler) {
        if(handlers.containsKey(request))
            throw new RequestHandlerHaveAlreadyBeenRegistered("Request  : "+request);
        handlers.put(request, handler);
    }

    @Override
    public RequestHandler findHandler(String request) {
        if(handlers.containsKey(request))
            return handlers.get(request);
        throw new RequestHandlerNotFound("Request : "+request);
    }
}
