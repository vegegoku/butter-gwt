package com.progressoft.security.authentication.shared.extension;

@FunctionalInterface
public interface CompletedChainContext {

    Principal getPrincipal();
}
