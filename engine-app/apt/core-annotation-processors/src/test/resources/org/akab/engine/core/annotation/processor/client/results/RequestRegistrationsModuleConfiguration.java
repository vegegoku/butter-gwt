package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.request.RequestRegistry;
import org.akab.engine.core.annotation.processor.client.AnnotatedClassWithRequest;
import org.akab.engine.core.annotation.processor.client.PresenterInterface;

public class RequestRegistrationsModuleConfiguration implements ModuleConfiguration {
    @Override
    public void registerRequests(RequestRegistry registry) {
        registry.registerRequest(AnnotatedClassWithRequest.class.getCanonicalName(),
                PresenterInterface.class.getCanonicalName());
    }
}