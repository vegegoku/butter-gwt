package org.akab.engine.core.api.shared.server;

@FunctionalInterface
public interface HandlerRegistry {

    void registerHandler(String request, RequestHandler handler);

}
