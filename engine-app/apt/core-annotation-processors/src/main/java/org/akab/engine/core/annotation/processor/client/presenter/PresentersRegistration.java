package org.akab.engine.core.annotation.processor.client.presenter;

import org.akab.engine.core.annotation.processor.client.BaseElementRegistration;
import org.akab.engine.core.annotation.processor.client.RegistrationImplementation;
import org.akab.engine.core.api.client.mvp.PresenterRegistry;

public class PresentersRegistration extends BaseElementRegistration {

    public PresentersRegistration(RegistrationImplementation implementation) {
        super(implementation);
    }

    @Override
    protected Class<?> argumentType() {
        return PresenterRegistry.class;
    }

    @Override
    protected String methodName() {
        return "registerPresenters";
    }
}
