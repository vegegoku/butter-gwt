package org.akab.engine.core.api.client.mvp.presenter;

@FunctionalInterface
public interface PresentersLoader {
    void load(PresentersRepository repository);
}
