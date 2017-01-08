package com.progressoft.security.authentication.server.configurations;

public interface AuthenticationConfigurationLoader {
    AuthenticationConfiguration load();

    class InvalidConfigurationProvidedException extends RuntimeException{
    }
}
