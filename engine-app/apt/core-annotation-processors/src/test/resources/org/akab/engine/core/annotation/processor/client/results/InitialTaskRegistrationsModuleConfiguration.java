package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.InitialTaskRegistry;
import org.akab.engine.core.annotation.processor.client.AnnotatedClassWithInitialTask;

public class InitialTaskRegistrationsModuleConfiguration implements ModuleConfiguration {

    @Override
    public void registerInitialTasks(InitialTaskRegistry registry) {
        registry.registerInitialTask(new AnnotatedClassWithInitialTask());
    }
}