package org.akab.engine.core.api.client.mvp.view;

@FunctionalInterface
public interface ViewsLoader {
    void load(ViewsRepository repository);
}
