package org.akab.engine.core.annotation.processor.client.uiview;

import org.akab.engine.core.annotation.processor.client.MethodImplementation;
import org.akab.engine.core.api.client.mvp.view.LazyViewLoader;
import org.akab.engine.core.api.client.mvp.view.View;

import javax.lang.model.element.Element;
import javax.lang.model.type.DeclaredType;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ViewRegistrationImplementation implements MethodImplementation {

    private Map<Element, DeclaredType> views;

    public ViewRegistrationImplementation(Map<Element, DeclaredType> views) {
        this.views = views;
    }

    @Override
    public String methodBody() {
        return views.entrySet().stream().map(entry ->
                writeRegistrationLine() + "(" + writeArguments(entry) + ") {\n" +
                        writeLazyImplementation(entry) +
                        writeEndMethod()).collect(Collectors.joining());
    }

    private String writeEndMethod() {
        return "\t\t});\n";
    }

    private String writeLazyImplementation(Map.Entry<Element, DeclaredType> entry) {
        return "\t\t\t@Override\n" +
                "\t\t\tprotected View make() {\n" +
                "\t\t\t\treturn new " + getSimpleName(entry.getKey()) + "();\n" +
                "\t\t\t}\n";
    }

    private String writeArguments(Map.Entry<Element, DeclaredType> entry) {
        return "" + getSimpleName(entry.getValue().asElement()) + ".class.getCanonicalName()";
    }

    private String writeRegistrationLine() {
        return "\tregistry.registerView(new LazyViewLoader";
    }

    @Override
    public String imports() {
        Set<String> imports = new HashSet<>();
        imports.add("import " + LazyViewLoader.class.getCanonicalName() + ";\n");
        imports.add("import " + View.class.getCanonicalName() + ";\n");
        views.entrySet().forEach(entry -> {
            imports.add("import " + entry.getKey() + ";\n");
            imports.add("import " + entry.getValue() + ";\n");
        });
        return imports.stream().map(String::toString).collect(Collectors.joining());
    }

    private String getSimpleName(Element e) {
        return e.getSimpleName().toString();
    }
}
