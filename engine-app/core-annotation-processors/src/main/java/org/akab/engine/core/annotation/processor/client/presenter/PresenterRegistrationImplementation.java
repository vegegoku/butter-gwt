package org.akab.engine.core.annotation.processor.client.presenter;

import org.akab.engine.core.annotation.processor.client.MethodImplementation;
import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;

import javax.lang.model.element.Element;
import javax.lang.model.type.DeclaredType;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PresenterRegistrationImplementation implements MethodImplementation {

    private Map<Element, DeclaredType> presenters;

    public PresenterRegistrationImplementation(Map<Element, DeclaredType> presenters) {
        this.presenters = presenters;
    }

    @Override
    public String methodBody() {
        return presenters.entrySet().stream().map(entry ->
                writeRegistrationLine() + "(" + writeArguments(entry) + ") {\n" +
                        writeLazyImplementation(entry) +
                        writeEndMethod()).collect(Collectors.joining());
    }

    private String writeEndMethod() {
        return "\t\t});\n";
    }

    private String writeLazyImplementation(Map.Entry<Element, DeclaredType> entry) {
        return "\t\t\t@Override\n" +
                "\t\t\tprotected Presentable make() {\n" +
                "\t\t\t\treturn new " + getSimpleName(entry.getKey()) + "();\n" +
                "\t\t\t}\n";
    }

    private String writeArguments(Map.Entry<Element, DeclaredType> entry) {
        return "" + getSimpleName(entry.getValue().asElement()) + ".class.getCanonicalName(), "
                + getSimpleName(entry.getKey()) + ".class.getCanonicalName()";
    }

    private String writeRegistrationLine() {
        return "\tregistry.registerPresenter(new LazyPresenterLoader";
    }

    @Override
    public String imports() {
        Set<String> imports = new HashSet<>();
        imports.add("import " + LazyPresenterLoader.class.getCanonicalName() + ";\n");
        imports.add("import " + Presentable.class.getCanonicalName() + ";\n");
        presenters.entrySet().forEach(entry -> {
            imports.add("import " + entry.getKey() + ";\n");
            imports.add("import " + entry.getValue() + ";\n");
        });
        return imports.stream().map(i -> i.toString()).collect(Collectors.joining());
    }

    private String getSimpleName(Element e) {
        return e.getSimpleName().toString();
    }
}
