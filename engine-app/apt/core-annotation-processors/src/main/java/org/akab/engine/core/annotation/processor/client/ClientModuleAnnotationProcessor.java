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

@AutoService(Processor.class)
public class ClientModuleAnnotationProcessor extends BaseProcessor {

    private Set<Element> clientModules=new HashSet<>();
    private Map<String, List<Element>> elements=new HashMap<>();

    public ClientModuleAnnotationProcessor() {
        elements.put(Presenter.class.getCanonicalName(), new LinkedList<>());
        elements.put(UiView.class.getCanonicalName(), new LinkedList<>());
        elements.put(Request.class.getCanonicalName(), new LinkedList<>());
        elements.put(InitialTask.class.getCanonicalName(), new LinkedList<>());
        elements.put(Contribute.class.getCanonicalName(), new LinkedList<>());
        elements.put(Path.class.getCanonicalName(), new LinkedList<>());
        elements.put(PathParameter.class.getCanonicalName(), new LinkedList<>());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        elements.get(Presenter.class.getCanonicalName()).addAll(roundEnv.getElementsAnnotatedWith(Presenter.class));
        elements.get(UiView.class.getCanonicalName()).addAll(roundEnv.getElementsAnnotatedWith(UiView.class));
        elements.get(Request.class.getCanonicalName()).addAll(roundEnv.getElementsAnnotatedWith(Request.class));
        elements.get(InitialTask.class.getCanonicalName()).addAll(roundEnv.getElementsAnnotatedWith(InitialTask.class));
        elements.get(Contribute.class.getCanonicalName()).addAll(roundEnv.getElementsAnnotatedWith(Contribute.class));
        elements.get(Path.class.getCanonicalName()).addAll(roundEnv.getElementsAnnotatedWith(Path.class));
        elements.get(PathParameter.class.getCanonicalName()).addAll(roundEnv.getElementsAnnotatedWith(PathParameter.class));

        clientModules.addAll(roundEnv.getElementsAnnotatedWith(ClientModule.class));
        if(roundEnv.processingOver()) {
            clientModules.stream()
                    .filter(e -> validateElementKind(e, ElementKind.CLASS))
                    .forEach(e -> generateModuleConfiguration(newProcessorElement(e)));
        }

        return false;
    }

    private void generateModuleConfiguration(ProcessorElement element) {
        try (Writer sourceWriter = obtainSourceWriter(element.elementPackage(), element.getAnnotation(ClientModule.class).name() + ConfigurationSourceWriter.MODEL_CONFIGURATION)) {
            sourceWriter.write(createConfigurationWriter(element).write());
            sourceWriter.flush();
            sourceWriter.close();
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class ");
        }
    }

    private ConfigurationSourceWriter createConfigurationWriter(ProcessorElement element) {
        List<RegistrationFactory> registrationFactories = generateRegistrationFactories();
        ConfigurationSourceWriter.Builder builder = new ConfigurationSourceWriter.Builder()
                .withProcessorElement(element);
        registrationFactories.forEach(r -> builder.withElementRegistration(r.registration()));
        return builder.build();
    }

    private List<RegistrationFactory> generateRegistrationFactories() {
        RegistrationHelper helper = new RegistrationHelper(this.elements);
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
        annotations.add(AutoRequest.class.getCanonicalName());
        annotations.add(InjectContext.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
