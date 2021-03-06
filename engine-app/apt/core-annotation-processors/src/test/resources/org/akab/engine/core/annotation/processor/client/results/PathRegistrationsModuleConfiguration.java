package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.history.PathToRequestMapperRegistry;
import org.akab.engine.core.api.client.request.Request;
import org.akab.engine.core.api.client.history.RequestFromPath;
import org.akab.engine.core.annotation.processor.client.AnnotatedClassWithPathAndNoParamters;
import org.akab.engine.core.api.client.history.TokenizedPath;

public class PathRegistrationsModuleConfiguration implements ModuleConfiguration {

    @Override
    public void registerPathMappers(PathToRequestMapperRegistry registry) {
        registry.registerMapper("somePath", new RequestFromPath() {
            @Override
            public Request buildRequest(TokenizedPath path) {
                return new AnnotatedClassWithPathAndNoParamters();
            }
        });
    }
}