package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.History.PathToRequestMapperRegistry;
import org.akab.engine.core.annotation.processor.client.AnnotatedClassWithPathAndParameterWithConverter;
import org.akab.engine.core.api.client.request.Request;
import org.akab.engine.core.annotation.processor.client.BigDecimalConverter;
import org.akab.engine.core.api.client.History.TokenizedPath;
import org.akab.engine.core.api.client.History.RequestFromPath;

public class PathAndParameterWithConverterRegistrationsModuleConfiguration implements ModuleConfiguration {

    @Override
    public void registerPathMappers(PathToRequestMapperRegistry registry) {
        registry.registerMapper("somePath", new RequestFromPath() {
            @Override
            public Request buildRequest(TokenizedPath path) {
                return new AnnotatedClassWithPathAndParameterWithConverter(
                        new BigDecimalConverter().convert(path.getParameter("value")));
            }
        });
    }
}