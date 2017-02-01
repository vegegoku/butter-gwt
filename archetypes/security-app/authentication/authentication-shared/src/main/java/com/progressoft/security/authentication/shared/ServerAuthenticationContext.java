package com.progressoft.security.authentication.shared;

import com.progressoft.security.authentication.shared.configurations.AuthenticationConfiguration;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfigurationLoader;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;

public class ServerAuthenticationContext {

    private static AuthenticationConfigurationLoader configurationLoader;

    private ServerAuthenticationContext(){
    }

    public static void withConfigurationLoader(AuthenticationConfigurationLoader loader){
        configurationLoader=loader;
    }

    public static AuthenticationConfiguration configuration() {
        return configurationLoader.load();
    }


}
