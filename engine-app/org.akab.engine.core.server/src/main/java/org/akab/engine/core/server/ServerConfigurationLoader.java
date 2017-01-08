package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.server.HandlersRepository;
import org.akab.engine.core.api.shared.server.InterceptorsRepository;
import org.akab.engine.core.api.shared.server.ServerApp;
import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;

import java.util.ServiceLoader;

public class ServerConfigurationLoader {

    public void loadModules(){
        HandlersRepository handlersRepository=new InMemoryHandlersRepository();
        InterceptorsRepository interceptorsRepository=new InMemoryInterceptorsRepository();
        ServerApp serverApp= makeServerApp(handlersRepository, interceptorsRepository);

        ServiceLoader<ServerModuleConfiguration> loader=ServiceLoader.load(ServerModuleConfiguration.class);
        loader.forEach(serverApp::configureModule);
    }

    private ServerApp makeServerApp(HandlersRepository handlersRepository,
                                    InterceptorsRepository interceptorsRepository) {
        return new ServerApp.ServerAppBuilder().handlersRepository(handlersRepository).interceptorsRepository(interceptorsRepository).executor(new DefaultRequestExecutor(handlersRepository, interceptorsRepository)).build();
    }

}
