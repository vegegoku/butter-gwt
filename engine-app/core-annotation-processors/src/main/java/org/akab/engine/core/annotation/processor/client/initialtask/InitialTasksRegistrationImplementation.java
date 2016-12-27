package org.akab.engine.core.annotation.processor.client.initialtask;

import org.akab.engine.core.annotation.processor.client.RegistrationImplementation;

import javax.lang.model.element.Element;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InitialTasksRegistrationImplementation implements RegistrationImplementation {

    private Set<? extends Element> items;

    public InitialTasksRegistrationImplementation(Set<? extends Element> items) {
        this.items = items;
    }

    private String writeRegistrationLine() {
        return "\tregistry.registerInitialTask";
    }

    private String writeArguments(Element item) {
        return "new " + item.getSimpleName() + "()";
    }

    @Override
    public String methodBody() {
        return items.stream().map(item ->
                writeRegistrationLine() + "(" + writeArguments(item) + ");\n")
                .collect(Collectors.joining());
    }

    @Override
    public String imports() {
        Set<String> imports = new HashSet<>();
        items.forEach(item -> {
            imports.add("import " + item.asType().toString() + ";\n");
        });
        return imports.stream().map(String::toString).collect(Collectors.joining());
    }
}
