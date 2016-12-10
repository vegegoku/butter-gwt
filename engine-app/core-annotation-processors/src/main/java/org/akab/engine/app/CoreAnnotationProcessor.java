package org.akab.engine.app;

import com.google.auto.service.AutoService;
import org.akab.engine.annotations.processor.utils.BaseProcessor;
import org.akab.engine.annotations.processor.utils.ProcessorElement;
import org.akab.engine.app.presenter.PresenterRegistration;
import org.akab.engine.app.presenter.PresenterRegistrationImplementation;
import org.akab.engine.core.api.client.annotations.ClientModule;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AutoService(Processor.class)
public class CoreAnnotationProcessor extends BaseProcessor {

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
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }

    private ConfigurationSourceWriter createConfigurationWriter(ProcessorElement element) {
        return new ConfigurationSourceWriter.Builder()
                .withProcessorElement(element)
                .withElementRegistration(presenterRegistration()).build();
    }

    private ElementRegistration presenterRegistration() {
        if (!isTherePresenters())
            return NullElementRegistration.NULL_REGISTRATION;

        Map<Element, DeclaredType> presenters = elementDeclaredTypeMap();
        return new PresenterRegistration(new PresenterRegistrationImplementation(presenters));
    }

    private Map<Element, DeclaredType> elementDeclaredTypeMap() {
        if (presentersAsStream().anyMatch(e -> !isImplementsPresentable(e)))
            throw new RuntimeException("Invalid presenter");
        return presentersAsStream()
                .collect(Collectors.toMap(e -> e,
                        e -> (DeclaredType) ((TypeElement) e).getInterfaces().get(0)));
    }

    private boolean isImplementsPresentable(Element e) {
        return typeUtils.isAssignable(e.asType(), (TypeMirror) elementUtils.getTypeElement(Presentable.class.getCanonicalName()).asType());
    }

    private boolean isTherePresenters() {
        return presentersAsStream().count() > 0;
    }

    private Stream<? extends Element> presentersAsStream() {
        return roundEnv.getElementsAnnotatedWith(Presenter.class).stream().filter(e -> validateElementKind(e, ElementKind.CLASS));
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(ClientModule.class.getCanonicalName());
        annotations.add(Presenter.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
