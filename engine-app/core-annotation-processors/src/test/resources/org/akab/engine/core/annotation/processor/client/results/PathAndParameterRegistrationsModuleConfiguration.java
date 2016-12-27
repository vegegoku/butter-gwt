package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.History.PathToRequestMapperRegistry;
import org.akab.engine.core.api.client.request.Request;
import org.akab.engine.core.api.client.History.TokenizedPath;
import org.akab.engine.core.api.client.History.RequestFromPath;
import org.akab.engine.core.annotation.processor.client.AnnotatedClassWithPathAndPathParameter;

public class PathAndParameterRegistrationsModuleConfiguration implements ModuleConfiguration {

    @Override
    public void registerPathMappers(PathToRequestMapperRegistry registry) {
        registry.registerMapper("somePath", new RequestFromPath() {
            @Override
            public Request buildRequest(TokenizedPath path) {
                return new AnnotatedClassWithPathAndPathParameter(path.getParameter("value"));
            }
        });
    }
}