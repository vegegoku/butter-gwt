package com.progressoft.security.authentication.configuration;

import com.google.auto.service.AutoService;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfiguration;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfigurationLoader;

import static java.util.Objects.isNull;

@AutoService(AuthenticationConfigurationLoader.class)
public class PropertiesAuthenticationConfigurationLoader implements AuthenticationConfigurationLoader {

    private String path;
    private AuthenticationConfiguration configuration;

    public PropertiesAuthenticationConfigurationLoader() {
        this.path = PropertiesAuthenticationConfiguration.DEFAULT_CONFIG;
    }

    public PropertiesAuthenticationConfigurationLoader(String path) {
        this.path = path;
    }

    @Override
    public AuthenticationConfiguration load() {
        if(isNull(configuration))
            configuration=new PropertiesAuthenticationConfiguration(path);
        if(isNull(configuration.rootAuthenticationChain()) || configuration.rootAuthenticationChain().trim().isEmpty())
            throw new AuthenticationConfiguration.InvalidConfigurationProvidedException();
       return configuration;
    }

}