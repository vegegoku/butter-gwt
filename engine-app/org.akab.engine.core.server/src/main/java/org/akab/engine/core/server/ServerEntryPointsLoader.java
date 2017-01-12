package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.server.ServerAppEntryPoint;

import java.util.ServiceLoader;

public class ServerEntryPointsLoader {
    private ServerEntryPointsLoader() {
    }

    public static void runEntryPoints(){
        ServiceLoader.load(ServerAppEntryPoint.class).forEach(ServerAppEntryPoint::onModulesLoaded);
    }
}
