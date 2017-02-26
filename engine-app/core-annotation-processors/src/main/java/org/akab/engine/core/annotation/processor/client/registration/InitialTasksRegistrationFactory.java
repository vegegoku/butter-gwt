package org.akab.engine.core.annotation.processor.client.registration;

import org.akab.engine.core.annotation.processor.client.ElementRegistration;
import org.akab.engine.core.annotation.processor.client.RegistrationHelper;
import org.akab.engine.core.annotation.processor.client.SingleArgumentRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.initialtask.InitialTasksRegistration;
import org.akab.engine.core.annotation.processor.client.initialtask.InitialTasksRegistrationImplementation;
import org.akab.engine.core.api.client.InitializeTask;
import org.akab.engine.core.api.client.annotations.InitialTask;

import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;

public class InitialTasksRegistrationFactory extends SingleArgumentRegistrationFactory {

    public InitialTasksRegistrationFactory(RegistrationHelper helper) {
        super(helper);
    }

    @Override
    protected ElementRegistration typeRegistration() {
        return new InitialTasksRegistration(new InitialTasksRegistrationImplementation(elements()));
    }

    @Override
    protected boolean isValid(Element e) {
        return helper.isImplementsGenericInterface(e, InitializeTask.class);
    }

    @Override
    protected String annotation() {
        return InitialTask.class.getCanonicalName();
    }
}
