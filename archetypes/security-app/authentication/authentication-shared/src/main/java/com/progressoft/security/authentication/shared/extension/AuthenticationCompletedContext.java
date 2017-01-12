package com.progressoft.security.authentication.shared.extension;

import org.akab.engine.core.api.shared.extension.Context;
@FunctionalInterface
public interface AuthenticationCompletedContext extends Context {
    Principal getPrincipal();
}
