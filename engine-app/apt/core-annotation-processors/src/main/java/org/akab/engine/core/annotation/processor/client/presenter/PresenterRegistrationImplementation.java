package org.akab.engine.core.annotation.processor.client.presenter;

import org.akab.engine.core.annotation.processor.client.LazyRegistrationImplementation;
import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;

import javax.lang.model.element.Element;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PresenterRegistrationImplementation extends LazyRegistrationImplementation {

    public PresenterRegistrationImplementation(Map<Element, Element> presenters) {
        super(presenters);
    }

    @Override
    protected String writeRegistrationLine() {
        return "\tregistry.registerPresenter(new LazyPresenterLoader";
    }

    @Override
    protected String writeArguments(Map.Entry<Element, Element> entry) {
        return "" + getSimpleName(entry.getValue()) + ".class.getCanonicalName(), "
                + getSimpleName(entry.getKey()) + ".class.getCanonicalName()";
    }

    @Override
    protected String writeLazyImplementation(Map.Entry<Element, Element> entry) {
        return "\t\t\t@Override\n" +
                "\t\t\tprotected Presentable make() {\n" +
                "\t\t\t\treturn new " + getSimpleName(entry.getKey()) + "();\n\t" +
                "\t\t\t}\n";
    }

    @Override
    protected Collection<String> implementationImports() {
        Set<String> imports = new HashSet<>();
        imports.add("import " + LazyPresenterLoader.class.getCanonicalName() + ";\n");
        imports.add("import " + Presentable.class.getCanonicalName() + ";\n");
        return imports;
    }
}
