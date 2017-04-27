package org.akab.engine.core.annotation.processor.client;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RegistrationHelper {

    private Map<String, List<Element>> elements;

    RegistrationHelper(Map<String, List<Element>> elements) {
        this.elements=elements;
    }

    boolean isThereItems(String annotation) {
        return elementsAsStream(annotation).count() > 0;
    }

    Stream<? extends Element> elementsAsStream(String annotation) {
        return elements.get(annotation).stream().filter(e -> e.getKind() == ElementKind.CLASS);
    }

    List<? extends Element> elementsAnnotatedWith(String annotation) {
        return elements.get(annotation);
    }

}
