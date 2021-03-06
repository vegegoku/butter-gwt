package com.progressoft.security.authentication.server;

import com.google.auto.service.AutoService;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfigurationLoader;
import org.akab.engine.core.api.shared.server.ServerAppEntryPoint;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

@AutoService(ServerAppEntryPoint.class)
public class AuthenticationModuleServerEntryPoint implements ServerAppEntryPoint{

    @Override
    public void onModulesLoaded() {
        Set<AuthenticationConfigurationLoader> loaders=new HashSet<>();
        ServiceLoader.load(AuthenticationConfigurationLoader.class).forEach(loaders::add);
        new AuthenticationConfigurator(loaders);
    }
}
