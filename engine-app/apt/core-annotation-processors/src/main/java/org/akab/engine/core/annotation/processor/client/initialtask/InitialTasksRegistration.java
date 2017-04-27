package org.akab.engine.core.annotation.processor.client.initialtask;

import org.akab.engine.core.annotation.processor.client.BaseElementRegistration;
import org.akab.engine.core.annotation.processor.client.RegistrationImplementation;
import org.akab.engine.core.api.client.InitialTaskRegistry;

public class InitialTasksRegistration extends BaseElementRegistration {

    public InitialTasksRegistration(RegistrationImplementation implementation) {
        super(implementation);
    }

    @Override
    protected Class<?> argumentType() {
        return InitialTaskRegistry.class;
    }

    @Override
    protected String methodName() {
        return "registerInitialTasks";
    }
}
