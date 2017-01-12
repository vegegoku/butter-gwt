package com.progressoft.security.authentication.shared.configurations;

public interface AuthenticationConfiguration {
    String rootAuthenticationChain();

    String defaultTenant();
}
