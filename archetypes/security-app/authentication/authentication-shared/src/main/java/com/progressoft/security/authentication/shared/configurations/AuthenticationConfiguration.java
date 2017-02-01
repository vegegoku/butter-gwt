package com.progressoft.security.authentication.shared.configurations;

public interface AuthenticationConfiguration {
    String rootAuthenticationChain();

    String defaultTenant();

    class InvalidConfigurationProvidedException extends RuntimeException{
    }
}
