package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.annotations.processor.utils.ProcessorElement;
import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.annotations.ClientModule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ConfigurationSourceWriter {

    static final String MODEL_CONFIGURATION = "ModuleConfiguration";
    public static final String CLOSING_PARNTHIES = "}";

    private ProcessorElement element;
    private List<ElementRegistration> elementRegistrations;

    ConfigurationSourceWriter(ProcessorElement element, List<ElementRegistration> elementRegistrations) {
        this.element = element;
        this.elementRegistrations = elementRegistrations;
    }

    String write() {
        return writePackage() +
                writeImports() +
                writeClassHeader() +
                writeImplementations() +
                CLOSING_PARNTHIES;
    }

    private String writeImplementations() {
        return elementRegistrations.stream().map(ElementRegistration::registrationMethod).collect(Collectors.joining());
    }

    private String writePackage() {
        return "package " + element.elementPackage() + ";\n";
    }

    private String writeImports() {
        return "import " + ModuleConfiguration.class.getCanonicalName() + ";\n" +
                elementRegistrations.stream().map(ElementRegistration::imports).collect(Collectors.joining());
    }

    private String writeClassHeader() {
        return "public class " + element.getAnnotation(ClientModule.class).name() + MODEL_CONFIGURATION
                + " implements ModuleConfiguration " + "{\n";
    }



    static class Builder {
        private List<ElementRegistration> registrations = new ArrayList<>();
        private ProcessorElement element;

        Builder withProcessorElement(ProcessorElement element) {
            this.element = element;
            return this;
        }

        Builder withElementRegistration(ElementRegistration registration) {
            this.registrations.add(registration);
            return this;
        }

        ConfigurationSourceWriter build() {
            return new ConfigurationSourceWriter(element, registrations);
        }
    }
}
