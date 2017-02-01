package com.progressoft.security.authentication.shared.configurations;

@FunctionalInterface
public interface AuthenticationConfigurationLoader {
    AuthenticationConfiguration load();
}
