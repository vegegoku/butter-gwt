package com.progressoft.security.authentication.shared.configurations;

@FunctionalInterface
public interface AuthenticationConfiguration {
    String rootAuthenticationChain();
}
