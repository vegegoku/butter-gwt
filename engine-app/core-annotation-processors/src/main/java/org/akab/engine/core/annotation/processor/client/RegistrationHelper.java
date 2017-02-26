package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.annotations.processor.utils.ProcessorElement;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class RegistrationHelper {

    private ProcessorElement processorElement;
    private Map<String, List<Element>> elements;

    RegistrationHelper(Map<String, List<Element>> elements, ProcessorElement processorElement) {
        this.processorElement = processorElement;
        this.elements=elements;
    }

    boolean isThereItems(String annotation) {
        return elementsAsStream(annotation).count() > 0;
    }

    Stream<? extends Element> elementsAsStream(String annotation) {
        return elements.get(annotation).stream().filter(e -> e.getKind() == ElementKind.CLASS);
    }

    public boolean isImplementsInterface(Element e, Class<?> interfaceClass) {
        return processorElement.make(e).isImplementsInterface(interfaceClass);
    }

    public boolean isImplementsGenericInterface(Element e, Class<?> interfaceClass) {
        return processorElement.make(e).isImplementsGenericInterface(interfaceClass);
    }

    List<? extends Element> elementsAnnotatedWith(String annotation) {
        return elements.get(annotation);
    }

    public boolean isSubtypeOfGenericClass(Element e, Class<?> superType) {
        return processorElement.make(e).isSubtypeOfGenericClass(superType);
    }
}
