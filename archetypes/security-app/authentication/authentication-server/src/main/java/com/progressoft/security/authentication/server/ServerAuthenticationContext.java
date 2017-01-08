package com.progressoft.security.authentication.server;

import com.progressoft.security.authentication.server.configurations.AuthenticationConfiguration;
import com.progressoft.security.authentication.server.configurations.AuthenticationConfigurationLoader;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;

public class ServerAuthenticationContext {

    public static AuthenticationHolder authenticationHolder = new NullHolder();
    public static AuthenticationConfigurationLoader configurationLoader= new NullAuthenticationConfigurationLoader();

    public static void reset(){
        authenticationHolder=new NullHolder();
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

    private static final class NullAuthenticationConfigurationLoader implements AuthenticationConfigurationLoader{
        @Override
        public AuthenticationConfiguration load() {
            return new AuthenticationConfiguration() {
                @Override
                public String rootAuthenticationChain() {
                    return "";
                }
            };
        }
    }
}
