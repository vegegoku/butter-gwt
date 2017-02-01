package com.progressoft.security.authentication.server.shared;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;

import static java.util.Objects.*;

public class UserSessionContext {

    private static final ThreadLocal<AuthenticationHolder> contextHolder = new ThreadLocal<>();

    private UserSessionContext() {}

    public static void clear() {
        contextHolder.remove();
    }

    public static AuthenticationHolder get() {
        if (contextHolder.get() == null)
            contextHolder.set(createEmptyContext());
        return contextHolder.get();
    }

    public static void setContext(AuthenticationHolder context) {
        if(isNull(context))
            throw new InvalidContextProvidedException();
        contextHolder.set(context);
    }

    private static AuthenticationHolder createEmptyContext() {
        return new NullHolder();
    }

    private static class NullHolder implements AuthenticationHolder{
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public Principal getPrincipal() {
            return null;
        }

        @Override
        public void setProperty(String key, Object value) {
            //should not add any thing
        }

        @Override
        public <T> T getProperty(String key) {
            return null;
        }
    }

    private static class InvalidContextProvidedException extends RuntimeException {
    }
}
