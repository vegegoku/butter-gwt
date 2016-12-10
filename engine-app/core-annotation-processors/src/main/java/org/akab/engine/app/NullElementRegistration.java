package org.akab.engine.app;

public class NullElementRegistration implements ElementRegistration {

    public static final NullElementRegistration NULL_REGISTRATION = new NullElementRegistration();

    private NullElementRegistration() {
    }

    @Override
    public String registrationMethod() {
        return "";
    }

    @Override
    public String imports() {
        return "";
    }
}
