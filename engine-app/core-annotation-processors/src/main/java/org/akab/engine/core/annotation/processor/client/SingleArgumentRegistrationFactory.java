package org.akab.engine.core.annotation.processor.client;

import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public abstract class SingleArgumentRegistrationFactory implements RegistrationFactory {

    protected RegistrationHelper helper;

    public SingleArgumentRegistrationFactory(RegistrationHelper helper) {
        this.helper = helper;
    }

    @Override
    public ElementRegistration registration() {
        if (!helper.isThereItems(annotation()))
            return NullElementRegistration.NULL_REGISTRATION;

        return typeRegistration();
    }

    protected List<? extends Element> elements() {
        return helper.elementsAnnotatedWith(annotation());
    }

    private Stream<? extends Element> elementsAsStream() {
        return helper.elementsAsStream(annotation());
    }

    protected abstract ElementRegistration typeRegistration();

    protected abstract boolean isValid(Element e);

    protected abstract String annotation();


}
