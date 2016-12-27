package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.History.PathToRequestMapperRegistry;
import org.akab.engine.core.annotation.processor.client.SampleMapper;

public class PathAndCustomMapperRegistrationsModuleConfiguration implements ModuleConfiguration {

    @Override
    public void registerPathMappers(PathToRequestMapperRegistry registry) {
        registry.registerMapper("somePath", new SampleMapper());
    }
}