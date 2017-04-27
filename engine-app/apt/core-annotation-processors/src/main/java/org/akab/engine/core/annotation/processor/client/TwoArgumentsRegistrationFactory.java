package org.akab.engine.core.annotation.processor.client;

import javax.lang.model.element.Element;
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

    private Stream<? extends Element> elementsAsStream(String annotationName) {
        return helper.elementsAsStream(annotationName);
    }

    protected Map<Element, Element> items() {

        Map<Element, Element> map = new LinkedHashMap<>();
        elementsAsStream(annotation()).forEach(e -> map.put(e, targetType(e)));
        return map;
    }

    protected abstract Element targetType(Element e);

    protected abstract String annotation();


}
