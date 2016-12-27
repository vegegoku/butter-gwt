package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.annotations.processor.utils.ProcessorElement;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Stream;

public class RegistrationHelper {

    private RoundEnvironment roundEnv;
    private ProcessorElement processorElement;

    RegistrationHelper(RoundEnvironment roundEnv, ProcessorElement processorElement) {
        this.roundEnv = roundEnv;
        this.processorElement = processorElement;
    }

    boolean isThereItems(Class<? extends Annotation> annotation) {
        return elementsAsStream(annotation).count() > 0;
    }

    Stream<? extends Element> elementsAsStream(Class<? extends Annotation> annotation) {
        return roundEnv.getElementsAnnotatedWith(annotation).stream().filter(e -> e.getKind() == ElementKind.CLASS);
    }

    public boolean isImplementsInterface(Element e, Class<?> interfaceClass) {
        return processorElement.make(e).isImplementsInterface(interfaceClass);
    }

    public boolean isImplementsGenericInterface(Element e, Class<?> interfaceClass) {
        return processorElement.make(e).isImplementsGenericInterface(interfaceClass);
    }

    Set<? extends Element> elementsAnnotatedWith(Class<? extends Annotation> annotation) {
        return roundEnv.getElementsAnnotatedWith(annotation);
    }

    public boolean isSubtypeOfGenericClass(Element e, Class<?> superType) {
        return processorElement.make(e).isSubtypeOfGenericClass(superType);
    }
}
