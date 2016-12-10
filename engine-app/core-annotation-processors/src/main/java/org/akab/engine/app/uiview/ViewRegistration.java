package org.akab.engine.app.uiview;

import org.akab.engine.app.ElementRegistration;
import org.akab.engine.app.MethodImplementation;
import org.akab.engine.app.MethodWriter;
import org.akab.engine.core.api.client.mvp.ViewRegistry;

import javax.lang.model.element.Modifier;

public class ViewRegistration implements ElementRegistration {
    private MethodImplementation implementation;

    public ViewRegistration(MethodImplementation implementation) {
        this.implementation = implementation;
    }

    @Override
    public String registrationMethod() {
        return "\t@Override\n" + new MethodWriter.Builder().modifier(Modifier.PUBLIC)
                .returnType("void")
                .name("registerViews")
                .argument("ViewRegistry", "registry")
                .implementation(implementation)
                .build().toString();
    }

    @Override
    public String imports() {
        StringBuilder builder = new StringBuilder();
        builder.append("import " + ViewRegistry.class.getCanonicalName() + ";\n");
        builder.append(implementation.imports());
        return builder.toString();
    }
}

