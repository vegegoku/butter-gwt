package org.akab.engine.core.api.shared.server;

public final class ServerModuleConfigurator {

    public void configureModule(ServerModuleConfiguration configuration){
        ServerApp.make().configureModule(configuration);
    }

}
