package org.akab.engine.core.api.client;

@FunctionalInterface
public interface InitialTaskRegistry {
    void registerInitialTask(InitializeTask task);
}
