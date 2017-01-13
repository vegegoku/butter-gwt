package com.progressoft.security.login.client.presenters;

public interface Conclusion {

    @FunctionalInterface
    interface InvalidationStrategy{
        void invalidate(String errorMessage);
    }

    void raiseError(String fieldName, String errorMessage);

    boolean hasErrors();

    void invalidateFiled(String name, InvalidationStrategy strategy);
}
