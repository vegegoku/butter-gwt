package org.akab.engine.core.annotation.processor.client;

import com.google.auto.service.AutoService;
import org.akab.engine.annotations.processor.utils.BaseProcessor;
import org.akab.engine.annotations.processor.utils.ProcessorElement;
import org.akab.engine.core.annotation.processor.client.registration.PathRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.registration.ContributionRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.registration.InitialTasksRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.registration.PresentersRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.registration.RequestsRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.registration.UiViewsRegistrationFactory;
import org.akab.engine.core.api.client.annotations.*;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@AutoService(Processor.class)
public class ClientModuleAnnotationProcessor extends BaseProcessor {
    private static final Logger LOGGER=Logger.getLogger(ClientModuleAnnotationProcessor.class.getName());

    private RoundEnvironment roundEnv;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        this.roundEnv = roundEnv;

        roundEnv.getElementsAnnotatedWith(ClientModule.class).stream()
                .filter(e -> validateElementKind(e, ElementKind.CLASS))
                .forEach(e -> generateModuleConfiguration(newProcessorElement(e)));
        return true;
    }

    private void generateModuleConfiguration(ProcessorElement element) {
        try (Writer sourceWriter = obtainSourceWriter(element.elementPackage(), element.getAnnotation(ClientModule.class).name() + ConfigurationSourceWriter.MODEL_CONFIGURATION)) {
            sourceWriter.write(createConfigurationWriter(element).write());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not generate classes : ", e);
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }

    private ConfigurationSourceWriter createConfigurationWriter(ProcessorElement element) {
        List<RegistrationFactory> registrationFactories = generateRegistrationFactories(element);
        ConfigurationSourceWriter.Builder builder = new ConfigurationSourceWriter.Builder()
                .withProcessorElement(element);
        registrationFactories.forEach(r -> builder.withElementRegistration(r.registration()));
        return builder.build();
    }

    private List<RegistrationFactory> generateRegistrationFactories(ProcessorElement element) {
        RegistrationHelper helper = new RegistrationHelper(roundEnv, element);
        return Arrays.asList(
                new PresentersRegistrationFactory(helper),
                new RequestsRegistrationFactory(helper),
                new UiViewsRegistrationFactory(helper),
                new InitialTasksRegistrationFactory(helper),
                new ContributionRegistrationFactory(helper),
                new PathRegistrationFactory(helper)
        );
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(ClientModule.class.getCanonicalName());
        annotations.add(Presenter.class.getCanonicalName());
        annotations.add(UiView.class.getCanonicalName());
        annotations.add(Request.class.getCanonicalName());
        annotations.add(InitialTask.class.getCanonicalName());
        annotations.add(Contribute.class.getCanonicalName());
        annotations.add(Path.class.getCanonicalName());
        annotations.add(PathParameter.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
