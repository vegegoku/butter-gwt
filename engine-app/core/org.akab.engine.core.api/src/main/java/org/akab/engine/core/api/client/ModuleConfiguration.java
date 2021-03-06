package org.akab.engine.core.api.client;

import org.akab.engine.core.api.client.history.PathToRequestMapperRegistry;
import org.akab.engine.core.api.client.extension.ContributionsRegistry;
import org.akab.engine.core.api.client.mvp.PresenterRegistry;
import org.akab.engine.core.api.client.request.RequestRegistry;
import org.akab.engine.core.api.client.mvp.ViewRegistry;

public interface ModuleConfiguration {

    default void registerPresenters(PresenterRegistry registry) {
    }

    default void registerRequests(RequestRegistry registry) {
    }

    default void registerViews(ViewRegistry registry) {
    }

    default void registerContributions(ContributionsRegistry registry) {
    }

    default void registerInitialTasks(InitialTaskRegistry registry) {
    }

    default void registerPathMappers(PathToRequestMapperRegistry registry) {
    }
}
