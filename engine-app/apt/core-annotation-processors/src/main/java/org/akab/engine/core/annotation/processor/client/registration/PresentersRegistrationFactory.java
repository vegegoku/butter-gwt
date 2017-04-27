package org.akab.engine.core.annotation.processor.client.registration;

import org.akab.engine.core.annotation.processor.client.ElementRegistration;
import org.akab.engine.core.annotation.processor.client.RegistrationHelper;
import org.akab.engine.core.annotation.processor.client.TwoArgumentsRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.presenter.PresenterRegistrationImplementation;
import org.akab.engine.core.annotation.processor.client.presenter.PresentersRegistration;
import org.akab.engine.core.api.client.annotations.Presenter;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;

public class PresentersRegistrationFactory extends TwoArgumentsRegistrationFactory {

    public PresentersRegistrationFactory(RegistrationHelper helper) {
        super(helper);
    }

    @Override
    protected ElementRegistration typeRegistration() {
        return new PresentersRegistration(new PresenterRegistrationImplementation(items()));
    }

    @Override
    protected Element targetType(Element e) {
        return ((DeclaredType) ((TypeElement) e).getInterfaces().get(0)).asElement();
    }

    @Override
    protected String annotation() {
        return Presenter.class.getCanonicalName();
    }

}
