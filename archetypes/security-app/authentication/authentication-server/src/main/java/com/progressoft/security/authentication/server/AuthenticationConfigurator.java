package com.progressoft.security.authentication.server;

import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfigurationLoader;

import java.util.Objects;
import java.util.Set;

public class AuthenticationConfigurator {
    public AuthenticationConfigurator(Set<AuthenticationConfigurationLoader> loaders) {
        if(Objects.isNull(loaders) || loaders.isEmpty())
            throw new InvalidConfigurationLoaders();
        if(loaders.size()>1)
            throw new MoreThanOneAuthenticationLoaderFound();
        registerConfigurationLoader(loaders.stream().findFirst().get());
    }

    private void registerConfigurationLoader(AuthenticationConfigurationLoader loader) {
        ServerAuthenticationContext.withConfigurationLoader(loader);
    }

    public class InvalidConfigurationLoaders extends RuntimeException{
    }

    public class MoreThanOneAuthenticationLoaderFound extends RuntimeException{
    }
}
