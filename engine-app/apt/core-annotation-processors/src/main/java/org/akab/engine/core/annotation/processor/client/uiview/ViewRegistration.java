package org.akab.engine.core.annotation.processor.client.uiview;

import org.akab.engine.core.annotation.processor.client.BaseElementRegistration;
import org.akab.engine.core.annotation.processor.client.RegistrationImplementation;
import org.akab.engine.core.api.client.mvp.ViewRegistry;

public class ViewRegistration extends BaseElementRegistration {

    public ViewRegistration(RegistrationImplementation implementation) {
        super(implementation);
    }

    @Override
    protected Class<?> argumentType() {
        return ViewRegistry.class;
    }

    @Override
    protected String methodName() {
        return "registerViews";
    }
}

