package org.akab.engine.app;

import org.akab.engine.annotations.processor.utils.BaseProcessor;
import org.akab.engine.annotations.processor.utils.ProcessorElement;
import org.akab.engine.core.api.client.annotations.ClientModule;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

public class CoreAnnotationProcessor extends BaseProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(ClientModule.class).stream()
                .filter(e -> validateElementKind(e, ElementKind.CLASS))
                .forEach(e -> generateModuleConfiguration(newProcessorElement(e)));
        return true;
    }

    private void generateModuleConfiguration(ProcessorElement element) {
        try (Writer sourceWriter = obtainSourceWriter(element.elementPackage(), element.getAnnotation(ClientModule.class).name() + ConfigurationSourceWriter.MODEL_CONFIGURATION)) {
            sourceWriter.write(new ConfigurationSourceWriter(element).write());
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(ClientModule.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
