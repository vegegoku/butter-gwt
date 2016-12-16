package org.akab.engine.core.annotation.processor.client.presenter;

import org.akab.engine.core.annotation.processor.client.ElementRegistration;
import org.akab.engine.core.annotation.processor.client.MethodImplementation;
import org.akab.engine.core.annotation.processor.client.MethodWriter;
import org.akab.engine.core.api.client.mvp.PresenterRegistry;

import javax.lang.model.element.Modifier;

public class PresenterRegistration implements ElementRegistration {

    private MethodImplementation implementation;

    public PresenterRegistration(MethodImplementation implementation) {
        this.implementation = implementation;
    }

    @Override
    public String registrationMethod() {
        return "\t@Override\n" + new MethodWriter.Builder().modifier(Modifier.PUBLIC)
                .returnType("void")
                .name("registerPresenters")
                .argument("PresenterRegistry", "registry")
                .implementation(implementation)
                .build().writeMethod();
    }

    @Override
    public String imports() {
        StringBuilder builder = new StringBuilder();
        builder.append("import " + PresenterRegistry.class.getCanonicalName() + ";\n");
        builder.append(implementation.imports());
        return builder.toString();
    }
}
