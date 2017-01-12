package com.progressoft.security.authentication.server;

import com.progressoft.security.authentication.shared.configurations.AuthenticationConfiguration;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfigurationLoader;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;

public class ServerAuthenticationContext {

    private static AuthenticationHolder authenticationHolder = new NullHolder();
    private static AuthenticationConfigurationLoader configurationLoader;

    private ServerAuthenticationContext(){
    }

    public static void reset() {
        authenticationHolder = new NullHolder();
    }
    public static void hold(AuthenticationHolder holder){
        authenticationHolder=holder;
    }

    public static AuthenticationHolder holder(){
        return authenticationHolder;
    }

    public static void withConfigurationLoader( AuthenticationConfigurationLoader loader){
        configurationLoader=loader;
    }

    public static AuthenticationConfiguration configuration() {
        return configurationLoader.load();
    }

    private static final class NullHolder implements AuthenticationHolder {

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public Principal getPrincipal() {
            return null;
        }
    }
}
