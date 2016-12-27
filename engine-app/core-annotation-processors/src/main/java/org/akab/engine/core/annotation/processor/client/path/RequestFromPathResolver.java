package org.akab.engine.core.annotation.processor.client.path;

import com.sun.tools.javac.code.Type;
import org.akab.engine.core.api.client.History.RequestFromPath;
import org.akab.engine.core.api.client.History.TokenizedPath;
import org.akab.engine.core.api.client.annotations.Path;
import org.akab.engine.core.api.client.request.Request;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RequestFromPathResolver {

    private Map.Entry<Element, String> entry;

    RequestFromPathResolver(Map.Entry<Element, String> entry) {
        this.entry = entry;
    }

    String requestFromPathImplementation() {
        if (isMapperValuePresent())
            return customMapperImplementation();

        return anonymousImplementation();
    }

    public Set<String> imports() {
        if (isMapperValuePresent())
            return customMapperImports();

        return anonymousImports();
    }

    private boolean isMapperValuePresent() {
        return annotationMirror().getElementValues().keySet().stream().anyMatch(this::isMapperValue);
    }

    private String customMapperImplementation() {
        return "new " + mapperValue().asElement().getSimpleName().toString() + "()";
    }

    private Type.ClassType mapperValue() {
        return (Type.ClassType) annotationMirror().getElementValues().entrySet().stream().filter(e -> isMapperValue(e.getKey()))
                .findAny().get().getValue().getValue();
    }

    private AnnotationMirror annotationMirror() {
        return entry.getKey().getAnnotationMirrors()
                .stream().filter(a -> a.getAnnotationType().toString().equals(Path.class.getCanonicalName()))
                .findAny().get();
    }

    private boolean isMapperValue(ExecutableElement e) {
        return e.getSimpleName().toString().equals("mapper");
    }

    private String anonymousImplementation() {
        return "new RequestFromPath() {\n"
                + "\t\t\t@Override\n\t\t\tpublic Request buildRequest(TokenizedPath path) {\n"
                + "\t\t\t\treturn new " + entry.getKey().getSimpleName() + "(" + new PathParametersResolver(entry).parametersAsString() + ");\n"
                + "\t\t\t}\n"
                + "\t\t}";
    }

    private Set<String> customMapperImports() {
        Set<String> imports = new HashSet<>();
        imports.add("import " + mapperValue().asElement().getQualifiedName() + ";\n");
        return imports;
    }

    private Set<String> anonymousImports() {
        Set<String> imports = new HashSet<>();
        imports.addAll(pathParametersImports());
        imports.add("import " + Request.class.getCanonicalName() + ";\n");
        imports.add("import " + TokenizedPath.class.getCanonicalName() + ";\n");
        imports.add("import " + RequestFromPath.class.getCanonicalName() + ";\n");
        imports.add("import " + ((TypeElement) entry.getKey()).getQualifiedName() + ";\n");
        return imports;
    }

    private Collection<? extends String> pathParametersImports() {
        return new PathParametersResolver(entry).imports();
    }
}
