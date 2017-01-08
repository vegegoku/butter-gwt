package org.akab.engine.core.annotation.processor.client.uiview;

import org.akab.engine.core.annotation.processor.client.LazyRegistrationImplementation;
import org.akab.engine.core.api.client.mvp.view.LazyViewLoader;
import org.akab.engine.core.api.client.mvp.view.View;

import javax.lang.model.element.Element;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ViewRegistrationImplementation extends LazyRegistrationImplementation {

    public ViewRegistrationImplementation(Map<Element, Element> views) {
        super(views);
    }

    @Override
    protected String writeRegistrationLine() {
        return "\tregistry.registerView(new LazyViewLoader";
    }

    @Override
    protected String writeArguments(Map.Entry<Element, Element> entry) {
        return "" + getSimpleName(entry.getValue()) + ".class.getCanonicalName()";
    }

    @Override
    protected String writeLazyImplementation(Map.Entry<Element, Element> entry) {
        return "\t\t\t@Override\n" +
                "\t\t\tprotected View make() {\n" +
                "\t\t\t\treturn new " + getSimpleName(entry.getKey()) + "();\n" +
                "\t\t\t}\n";
    }

    @Override
    protected Collection<String> implementationImports() {
        Set<String> imports = new HashSet<>();
        imports.add("import " + LazyViewLoader.class.getCanonicalName() + ";\n");
        imports.add("import " + View.class.getCanonicalName() + ";\n");
        return imports;
    }
}
