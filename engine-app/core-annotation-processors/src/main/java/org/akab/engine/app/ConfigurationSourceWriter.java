package org.akab.engine.app;

import org.akab.engine.annotations.processor.utils.ProcessorElement;
import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.annotations.ClientModule;

class ConfigurationSourceWriter {

    static final String MODEL_CONFIGURATION = "ModuleConfiguration";

    private ProcessorElement element;

    ConfigurationSourceWriter(ProcessorElement element) {
        this.element = element;
    }

    String write() {
        return writePackage() +
                writeImports() +
                writeClassHeader() +
                writeEndClass();
    }

    private String writePackage() {
        return "package " + element.elementPackage() + ";\n";
    }

    private String writeImports() {
        return "import " + ModuleConfiguration.class.getCanonicalName() + ";\n";
    }

    private String writeClassHeader() {
        return "public class " + element.getAnnotation(ClientModule.class).name() + MODEL_CONFIGURATION
                + " implements ModuleConfiguration " + "{\n";
    }

    private String writeEndClass() {
        return "}";
    }
}
