package org.akab.engine.core.annotation.processor.client;

import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class TwoArgumentsRegistrationFactory implements RegistrationFactory {

    protected RegistrationHelper helper;

    public TwoArgumentsRegistrationFactory(RegistrationHelper helper) {
        this.helper = helper;
    }

    @Override
    public ElementRegistration registration() {
        if (!helper.isThereItems(annotation()))
            return NullElementRegistration.NULL_REGISTRATION;

        return typeRegistration();
    }

    protected abstract ElementRegistration typeRegistration();

    private Stream<? extends Element> elementsAsStream(Class<? extends Annotation> annotation) {
        return helper.elementsAsStream(annotation);
    }

    protected Map<Element, Element> items() {
        if (elementsAsStream(annotation()).anyMatch(e -> !isValid(e)))
            throw new InvalidDeclarationForAnnotationException("Invalid declaration for annotation " + annotation().getSimpleName());

        Map<Element, Element> map = new LinkedHashMap<>();
        elementsAsStream(annotation()).forEach(e -> map.put(e, targetType(e)));
        return map;
    }

    protected boolean isImplementInterface(Element e, Class<?> interfaceClass) {
        return helper.isImplementsInterface(e, interfaceClass);
    }

    protected abstract Element targetType(Element e);

    protected abstract boolean isValid(Element e);

    protected abstract Class<? extends Annotation> annotation();


}
