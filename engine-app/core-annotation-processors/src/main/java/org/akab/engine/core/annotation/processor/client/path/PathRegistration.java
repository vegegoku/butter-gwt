package org.akab.engine.core.annotation.processor.client.path;

import org.akab.engine.core.annotation.processor.client.BaseElementRegistration;
import org.akab.engine.core.annotation.processor.client.ElementRegistration;
import org.akab.engine.core.annotation.processor.client.RegistrationImplementation;
import org.akab.engine.core.api.client.History.PathToRequestMapperRegistry;

public class PathRegistration extends BaseElementRegistration {
    public PathRegistration(RegistrationImplementation implementation) {
        super(implementation);
    }

    @Override
    protected Class<?> argumentType() {
        return PathToRequestMapperRegistry.class;
    }

    @Override
    protected String methodName() {
        return "registerPathMappers";
    }
}
