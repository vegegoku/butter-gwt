package org.akab.engine.core.annotation.processor.client;

@FunctionalInterface
public interface RegistrationFactory {

    ElementRegistration registration();

    class InvalidDeclarationForAnnotationException extends RuntimeException{
        public InvalidDeclarationForAnnotationException(String message) {
            super(message);
        }
    }
}
