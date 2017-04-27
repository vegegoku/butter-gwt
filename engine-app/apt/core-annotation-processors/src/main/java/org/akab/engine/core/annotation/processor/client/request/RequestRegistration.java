package org.akab.engine.core.annotation.processor.client.request;

import org.akab.engine.core.annotation.processor.client.BaseElementRegistration;
import org.akab.engine.core.annotation.processor.client.RegistrationImplementation;
import org.akab.engine.core.api.client.request.RequestRegistry;

public class RequestRegistration extends BaseElementRegistration {

    public RequestRegistration(RegistrationImplementation implementation) {
        super(implementation);
    }

    @Override
    protected Class<?> argumentType() {
        return RequestRegistry.class;
    }

    @Override
    protected String methodName() {
        return "registerRequests";
    }
}
